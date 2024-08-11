package com.piyush.threadsncoroutinesapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val counterTextView: TextView = findViewById(R.id.counterTextView)
        val updateButton: Button = findViewById(R.id.updateButton)

        Log.d(TAG, "${Thread.currentThread().name}")

        updateButton.setOnClickListener {
            counter++
            counterTextView.text = counter.toString()

            Log.d(TAG, "${Thread.currentThread().name}")

        }

        val executeTaskButton: Button = findViewById(R.id.executeTasksButton)
        executeTaskButton.setOnClickListener {
            Log.d(TAG, "Heavy task function executed in thread ${Thread.currentThread().name}")

//            thread {
//                doAction()
//            }
//            doAction()

            CoroutineScope(Dispatchers.IO).launch {
                Log.d(TAG, "Coroutine executed in thread ${Thread.currentThread().name}")
                doAction()
            }
        }

    }

    private fun executeLongRunningTask() {

        for (i in 1..100000000000L) {

      }
    }

    fun doAction() {
        executeLongRunningTask()
    }
}