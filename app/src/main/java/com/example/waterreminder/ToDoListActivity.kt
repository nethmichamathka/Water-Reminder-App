package com.example.waterreminder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.waterreminder.adapters.TaskAdapter
import com.example.waterreminder.models.Task

class TodoListActivity : AppCompatActivity() {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var taskRecyclerView: RecyclerView
    private val taskList = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_list)

        // Initialize RecyclerView
        taskRecyclerView = findViewById(R.id.task_list_recycler_view)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)

        // Add 3 tasks to the task list
        taskList.add(Task("Task 1", false))
        taskList.add(Task("Task 2", false))
        taskList.add(Task("Task 3", false))

        // Initialize the TaskAdapter and set it to the RecyclerView
        taskAdapter = TaskAdapter(taskList, onEditClick = { task ->
            // Handle edit task action here
            // For example, navigate to an edit screen or open a dialog
        }, onDeleteClick = { task ->
            // Handle delete task action here
            taskList.remove(task)
            taskAdapter.notifyDataSetChanged() // Notify adapter that the data has changed
        })
        taskRecyclerView.adapter = taskAdapter
    }
}
