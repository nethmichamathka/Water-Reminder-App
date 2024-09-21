package com.example.waterreminder

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.waterreminder.models.Task

class TodoListActivity : AppCompatActivity() {

    private val taskList = mutableListOf<Task>()
    private lateinit var taskContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_note)

        taskContainer = findViewById(R.id.task_input)
        val addTaskButton: Button = findViewById(R.id.add_task_button)

        // Add initial tasks
        addTask("Task 1")
        addTask("Task 2")
        addTask("Task 3")

        // Add new task button click
        addTaskButton.setOnClickListener {
            // Show a simple dialog or input to add a new task
            val taskText = "New Task" // Replace with user input
            if (!TextUtils.isEmpty(taskText)) {
                addTask(taskText)
            }
        }
    }

    private fun addTask(taskText: String) {
        val task = Task(taskText, false)
        taskList.add(task)

        // Save the updated task list to SharedPreferences
        saveTasks()

        // Create a new TextView for the task
        val taskView = LayoutInflater.from(this).inflate(R.layout.activity_todo_list, null)
        val taskTextView: TextView = taskView.findViewById(R.id.task_container) // Ensure you have the correct ID here
        taskTextView.text = task.text

        // Add the task view to the container
        taskContainer.addView(taskView)
    }

    private fun saveTasks() {
        TODO("Not yet implemented")
    }

}
