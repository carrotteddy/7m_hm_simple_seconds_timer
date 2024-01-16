package com.example.seven_mouth_timer_hm

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.seven_mouth_timer_hm.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            val seconds = binding.minutesEditText.text.toString().toLong()
            startTimer(seconds = seconds)
        }

        viewModel.timerState.observe(this, Observer { state ->
            binding.tvTime.text = state.timeRemaining.toString()
            if (state.isFinished) {
                binding.tvTime.text = "finish"
            }
        })
    }

    private fun startTimer(seconds: Long) {
        viewModel.startTimer(seconds)
    }

}



