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
    private val taskList: MutableList<Task>, // Make it mutable to allow updates
    private val onEditClick: (Task, Int) -> Unit,
    private val onDeleteClick: (Task, Int) -> Unit // Pass position for deletion
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_todo_list, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task, position)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTextView: TextView = itemView.findViewById(R.id.task_container)
        private val editTaskButton: Button = itemView.findViewById(R.id.edit_task_button)
        private val deleteTaskButton: Button = itemView.findViewById(R.id.delete_task_button)

        fun bind(task: Task, position: Int) {
            taskTextView.text = task.text

            // Edit button click
            editTaskButton.setOnClickListener {
                onEditClick(task, position)
            }

            // Delete button click
            deleteTaskButton.setOnClickListener {
                onDeleteClick(task, position)
            }
        }
    }
}
