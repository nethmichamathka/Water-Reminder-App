package com.example.waterreminder

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
            onEditClick = { task, position ->
                showEditTaskDialog(task, position)
            },
            onDeleteClick = { task, position ->
                taskList.removeAt(position) // Remove the task at the given position
                taskAdapter.notifyItemRemoved(position) // Notify the adapter
            }
        )

        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = taskAdapter

        addTaskButton.setOnClickListener {
            val taskText = taskInput.text.toString()
            if (!TextUtils.isEmpty(taskText)) {
                val task = Task(taskText, false)
                taskList.add(task)
                taskAdapter.notifyDataSetChanged() // Notify the adapter for data change
                taskInput.text.clear() // Clear input after adding task
            }
        }
    }

    private fun showEditTaskDialog(task: Task, position: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Task")

        val input = EditText(this)
        input.setText(task.text) // Prepopulate the input with the current task text
        builder.setView(input)

        builder.setPositiveButton("Save") { dialog, _ ->
            val updatedTaskText = input.text.toString()
            if (!TextUtils.isEmpty(updatedTaskText)) {
                task.text = updatedTaskText // Update the task text
                taskAdapter.notifyItemChanged(position) // Notify the adapter to update the specific item
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}
