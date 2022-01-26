package com.franco.todolist.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.franco.todolist.databinding.ActivityAddTaskBinding
import com.franco.todolist.datasource.TaskDataSource
import com.franco.todolist.extensions.format
import com.franco.todolist.extensions.text
import com.franco.todolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListener()
    }

    private fun insertListener() {
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }
        binding.tilHour.editText?.setOnClickListener {
            val timerPicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timerPicker.addOnPositiveButtonClickListener {
                val minute = if (timerPicker.minute in 0..9) "0${timerPicker.minute}" else timerPicker.minute
                val hour = if (timerPicker.hour in 0..9) "0${timerPicker.hour}" else timerPicker.hour

                binding.tilHour.text = "$hour:$minute"
            }
            timerPicker.show(supportFragmentManager, null)
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
        binding.btnNewTask.setOnClickListener {
            val task = Task(
                title = binding.tilTitle.text,
                date = binding.tilDate.text,
                hour = binding.tilHour.text,
                id = intent.getIntExtra(TASK_ID,0)
            )
            TaskDataSource.insertTask(task)

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
    companion object {
        const val TASK_ID = "task_id"
    }
}