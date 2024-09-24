package com.example.waterreminder

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ClockActivity : AppCompatActivity() {

    private lateinit var alarmTimeTextViews: List<TextView>
    private lateinit var alarmSwitches: List<Switch>
    private lateinit var alarmTimes: MutableList<Calendar>

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

        alarmTimes = MutableList(alarmSwitches.size) { Calendar.getInstance() }

        alarmSwitches.forEachIndexed { index, switchView ->
            switchView.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
                if (isChecked) {
                    // Optionally, you can auto-open time picker here
                } else {
                    cancelAlarm(index)
                }
            }
        }

        // Set up the button
        val setAlarmsButton: Button = findViewById(R.id.set_alarms_button)
        setAlarmsButton.setOnClickListener {
            showTimePickerDialog()
        }

        setupInitialStates()
    }

    private fun showTimePickerDialog() {
        alarmSwitches.forEachIndexed { index, switchView ->
            if (switchView.isChecked) {
                val calendar = Calendar.getInstance()
                val timePicker = TimePickerDialog(this, { _, hourOfDay, minute ->
                    setAlarm(index, hourOfDay, minute)
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                timePicker.show()
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    private fun setAlarm(alarmIndex: Int, hour: Int, minute: Int) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, alarmIndex, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (before(Calendar.getInstance())) {
                add(Calendar.DAY_OF_YEAR, 1) // Schedule for the next day if time has already passed
            }
        }

        // Set exact alarm
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        alarmTimes[alarmIndex] = calendar // Save the alarm time
        alarmTimeTextViews[alarmIndex].text = String.format("%02d:%02d", hour, minute)

        Toast.makeText(this, "Alarm set for ${hour % 12} ${if (hour < 12) "AM" else "PM"}", Toast.LENGTH_SHORT).show()
        saveAlarmState(alarmIndex, true)
    }

    private fun cancelAlarm(alarmIndex: Int) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this, alarmIndex, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)

        alarmTimeTextViews[alarmIndex].text = "" // Clear the displayed time
        Toast.makeText(this, "Alarm cancelled", Toast.LENGTH_SHORT).show()
        saveAlarmState(alarmIndex, false)
    }

    private fun setupInitialStates() {
        alarmSwitches.forEachIndexed { index, switchView ->
            switchView.isChecked = getSavedAlarmState(index)
            if (switchView.isChecked) {
                // Load and display previously saved time
                val alarmTime = alarmTimes[index]
                alarmTimeTextViews[index].text = String.format("%02d:%02d", alarmTime.get(Calendar.HOUR_OF_DAY), alarmTime.get(Calendar.MINUTE))
            }
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
