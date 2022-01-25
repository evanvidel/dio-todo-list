package com.franco.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.franco.todolist.R
import com.franco.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}