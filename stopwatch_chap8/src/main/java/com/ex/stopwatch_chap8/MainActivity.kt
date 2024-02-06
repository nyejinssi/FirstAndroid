package com.ex.stopwatch_chap8

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ex.stopwatch_chap8.databinding.ActivityMainBinding
import com.ex.stopwatch_chap8.ui.theme.ExampleTheme

class MainActivity : ComponentActivity() {

    var initTime = 0L //시작시간 저장
    var pauseTime = 0L //멈추는 시간 저장

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener{
            binding.chronometer.base = SystemClock.elapsedRealtime()+pauseTime
            binding.chronometer.start()
            binding.stopButton.isEnabled = true
            binding.startButton.isEnabled = false
        }
        binding.stopButton.setOnClickListener{
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
            binding.startButton.isEnabled = true
        }

        binding.resetButton.setOnClickListener{
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK ){
            if(System.currentTimeMillis()- initTime>3000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!!!",
                    Toast.LENGTH_SHORT).show()
                initTime=System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}
