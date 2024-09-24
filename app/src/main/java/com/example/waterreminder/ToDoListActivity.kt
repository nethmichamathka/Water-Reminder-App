package com.example.waterreminder

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.waterreminder.models.Task
import org.json.JSONArray
import org.json.JSONObject

class TodoListActivity : AppCompatActivity() {

    private val taskList = mutableListOf<Task>()
    private lateinit var taskContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_note)

        taskContainer = findViewById(R.id.task_input)
        val addTaskButton: Button = findViewById(R.id.add_task_button)

        // Load the saved task list from SharedPreferences
        loadTasks()

        // Rebuild the task views for each saved task
        for (task in taskList) {
            addTaskView(task)
        }

        // Add new task button click
        addTaskButton.setOnClickListener {
            val taskText = "New Task" // Replace with user input
            if (!TextUtils.isEmpty(taskText)) {
                addTask(taskText)
            }
        }
    }

    private fun addTask(taskText: String) {
        val task = Task(taskText, false)
        taskList.add(task)
        saveTasks() // Save the updated task list to SharedPreferences

        // Add a new task view to the UI
        addTaskView(task)
    }

    private fun addTaskView(task: Task) {
        // Create a new TextView for the task
        val taskView = LayoutInflater.from(this).inflate(R.layout.activity_todo_list, null)
        val taskTextView: TextView = taskView.findViewById(R.id.task_container)
        taskTextView.text = task.text

        // Add the task view to the container
        taskContainer.addView(taskView)
    }

    private fun saveTasks() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("todoListPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Convert taskList to JSON array manually
        val jsonArray = JSONArray()
        for (task in taskList) {
            val jsonObject = JSONObject()
            jsonObject.put("text", task.text)
            jsonObject.put("completed", task.completed)
            jsonArray.put(jsonObject)
        }

        // Save the JSON array as a string
        editor.putString("taskList", jsonArray.toString())
        editor.apply() // Save asynchronously

        // Now trigger the widget update
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(
            ComponentName(this, TaskListWidget::class.java)
        )
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_task_list)
        TaskListWidget.updateAppWidget(this, appWidgetManager, appWidgetIds[0]) // Updating the first widget
    }

    private fun loadTasks() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("todoListPrefs", Context.MODE_PRIVATE)
        //shared prefferences
        val jsonString = sharedPreferences.getString("taskList", null)

        if (jsonString != null) {
            val jsonArray = JSONArray(jsonString)
            taskList.clear()
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val taskText = jsonObject.getString("text")
                val completed = jsonObject.getBoolean("completed")
                taskList.add(Task(taskText, completed))
            }
        }
    }
}
