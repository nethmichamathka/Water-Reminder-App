package com.example.waterreminder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.waterreminder.R
import com.example.waterreminder.models.Task

class TaskAdapter(
    private val taskList: MutableList<Task>,
    private val onEditClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTextView: TextView = view.findViewById(R.id.task_text_view)
        val editTaskButton: Button = view.findViewById(R.id.edit_task_button)
        val deleteTaskButton: Button = view.findViewById(R.id.delete_task_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_todo_list, parent, false)  // Inflate the task_item layout
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskTextView.text = task.taskText

        holder.editTaskButton.setOnClickListener {
            onEditClick(task)
        }

        holder.deleteTaskButton.setOnClickListener {
            onDeleteClick(task)
        }
    }

    override fun getItemCount(): Int = taskList.size
}
