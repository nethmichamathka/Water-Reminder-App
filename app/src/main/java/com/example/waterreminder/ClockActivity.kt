package com.example.waterreminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class ClockActivity : AppCompatActivity() {

    private lateinit var alarmTimeTextViews: List<TextView>
    private lateinit var alarmSwitches: List<Switch>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)

        alarmTimeTextViews = listOf(
            findViewById(R.id.alarm_time_1),
            findViewById(R.id.alarm_time_2),
            findViewById(R.id.alarm_time_3),
            findViewById(R.id.alarm_time_4),
            findViewById(R.id.alarm_time_5)
        )

        alarmSwitches = listOf(
            findViewById(R.id.alarm_switch_1),
            findViewById(R.id.alarm_switch_2),
            findViewById(R.id.alarm_switch_3),
            findViewById(R.id.alarm_switch_4),
            findViewById(R.id.alarm_switch_5)
        )

        alarmSwitches.forEachIndexed { index, switchView ->
            switchView.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
                handleAlarmSwitchChange(index, isChecked)
            }
        }

        setupInitialStates()
    }

    private fun handleAlarmSwitchChange(alarmIndex: Int, isChecked: Boolean) {
        if (isChecked) {
            setAlarm(alarmIndex)
        } else {
            cancelAlarm(alarmIndex)
        }
    }

    private fun setAlarm(alarmIndex: Int) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, alarmIndex, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Example: Set to 9:00 AM
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }

        // Set exact alarm
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        Toast.makeText(this, "Notification set for 9:00 AM", Toast.LENGTH_SHORT).show()
        saveAlarmState(alarmIndex, true)
    }

    private fun cancelAlarm(alarmIndex: Int) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, alarmIndex, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)

        Toast.makeText(this, "Notification cancelled", Toast.LENGTH_SHORT).show()
        saveAlarmState(alarmIndex, false)
    }

    private fun setupInitialStates() {
        alarmSwitches.forEachIndexed { index, switchView ->
            switchView.isChecked = getSavedAlarmState(index)
        }
    }

    private fun saveAlarmState(alarmIndex: Int, isEnabled: Boolean) {
        val sharedPreferences = getSharedPreferences("AlarmPreferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("Alarm_$alarmIndex", isEnabled).apply()
    }

    private fun getSavedAlarmState(alarmIndex: Int): Boolean {
        val sharedPreferences = getSharedPreferences("AlarmPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("Alarm_$alarmIndex", false)
    }
}
