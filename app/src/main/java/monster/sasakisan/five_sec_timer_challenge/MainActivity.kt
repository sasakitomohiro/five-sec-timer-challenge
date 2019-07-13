package monster.sasakisan.five_sec_timer_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import monster.sasakisan.five_sec_timer_challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.timer.text = "0.0"
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                time += 0.1
                binding.timer.text = String.format("%1\$.1f", time)

                handler.postDelayed(this, 100)
            }
        }

        binding.startButton.setOnClickListener {
            binding.startButton.visibility = View.GONE
            binding.stopButton.visibility = View.VISIBLE
            binding.resultText.text = ""
            time = 0.0
            handler.post(runnable)
        }

        binding.stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            binding.resultText.text = if (time < 4.5 || time > 5.5) "失敗" else "成功"
            binding.stopButton.visibility = View.GONE
            binding.startButton.visibility = View.VISIBLE
        }
    }
}
