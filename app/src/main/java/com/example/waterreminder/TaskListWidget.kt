package com.example.waterreminder

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.content.SharedPreferences
import org.json.JSONArray

class TaskListWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
            val views = RemoteViews(context.packageName, R.layout.widget_task_list)

            // Load the task list from SharedPreferences
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("todoListPrefs", Context.MODE_PRIVATE)
            val jsonString = sharedPreferences.getString("taskList", null)

            val taskListText = StringBuilder()

            if (jsonString != null) {
                val jsonArray = JSONArray(jsonString)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val taskText = jsonObject.getString("text")
                    taskListText.append("- $taskText\n")
                }
            } else {
                taskListText.append("No tasks available") // Fallback if no data is found
            }

            // Set the task list in the widget
            views.setTextViewText(R.id.widget_task_list, taskListText.toString())

            // Update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

}
