package com.example.seven_mouth_timer_hm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _timerState = MutableLiveData<TimerState>()
    val timerState: LiveData<TimerState> get() = _timerState

    fun startTimer(seconds: Long) {
        viewModelScope.launch {
            for (second in seconds downTo 0) {
                updateTimerState(second)
                delay(1000)
            }
            updateTimerState(0, true)
        }
}


    private fun updateTimerState(timeRemaining: Long, isFinished: Boolean = false) {
        val state = TimerState(timeRemaining, isFinished)
        _timerState.postValue(state)
    }

}