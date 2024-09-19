package com.example.waterreminder

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waterreminder.adapters.TaskAdapter
import com.example.waterreminder.models.Task

class DialogAddNoteActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskRecyclerView: RecyclerView
    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_note)

        val taskInput: EditText = findViewById(R.id.task_input)
        val addTaskButton: Button = findViewById(R.id.add_task_button)
        taskRecyclerView = findViewById(R.id.task_list_recycler_view)

        taskAdapter = TaskAdapter(taskList,
            onEditClick = { task ->
                // Handle edit task action here
                // For example, show a dialog to edit the task text
            },
            onDeleteClick = { task ->
                // Handle delete task action here
                taskList.remove(task)
                taskAdapter.notifyDataSetChanged()
            }
        )

        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = taskAdapter

        addTaskButton.setOnClickListener {
            val taskText = taskInput.text.toString()
            if (!TextUtils.isEmpty(taskText)) {
                val task = Task(taskText, false)
                taskList.add(task)
                taskAdapter.notifyDataSetChanged()
                taskInput.text.clear() // Clear input after adding task
            }
        }
    }
}
