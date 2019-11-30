package monster.sasakisan.five_sec_timer_challenge

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import monster.sasakisan.five_sec_timer_challenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding

    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = bindView()

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
            binding.startButton.isVisible = false
            binding.stopButton.isVisible = true
            binding.resultText.text = ""
            time = 0.0
            handler.post(runnable)
        }

        binding.stopButton.setOnClickListener {
            handler.removeCallbacks(runnable)
            binding.resultText.text =
                if (time < 4.5 || time > 5.5) getString(R.string.failed) else getString(R.string.success)
            binding.stopButton.isVisible = false
            binding.startButton.isVisible = true
        }
    }
}

fun <T : ViewDataBinding> ComponentActivity.bindView(): T =
    DataBindingUtil.bind(getContentView())!!

private fun Activity.getContentView(): View =
    findViewById<ViewGroup>(android.R.id.content)[0]
