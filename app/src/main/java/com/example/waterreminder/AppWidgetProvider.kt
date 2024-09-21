package com.example.waterreminder

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.example.waterreminder.models.Task

class TaskWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val views = RemoteViews(context.packageName, R.layout.widget_task_list)

        // Here you would retrieve the upcoming tasks and update the widget
        val tasks = getUpcomingTasks(context) // Implement this method to fetch your tasks

        // Example: update the widget with the task list
        val taskListText = tasks.joinToString("\n") { it.text } // Assuming Task has a 'text' property
        views.setTextViewText(R.id.widget_title, "Upcoming Tasks")
        views.setTextViewText(R.id.widget_task_list, taskListText)

        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    private fun getUpcomingTasks(context: Context): List<Task> {
        val sharedPreferences = context.getSharedPreferences("TaskPreferences", Context.MODE_PRIVATE)
        val taskList = mutableListOf<Task>()

        // Example of retrieving tasks from Shared Preferences
        val tasksString = sharedPreferences.getString("tasks", "")
        tasksString?.let {
            // Split the string by commas and create Task objects
            val tasks = it.split(",").map { taskText ->
                Task(taskText, false) // Assuming all tasks are initially not completed
            }
            taskList.addAll(tasks)
        }

        return taskList
    }

}
