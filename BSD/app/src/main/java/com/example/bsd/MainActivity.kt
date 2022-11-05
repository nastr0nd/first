package com.example.bsd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.bsd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val vmodel: VModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vmodel.message.observe(this, {

        })
        val numbers = (1..30).toMutableList()
        var numbersSize = numbers.size
        val bottomSheetFragment = BottomSheetFragment()
        binding.btnShow.setOnClickListener{
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")
            numbersSize -=1
            if (numbersSize < 0) {
            vmodel.message.value = "Please, Stop!"
        } else vmodel.message.value = Numb.myFunc()
        }

    }
}
class Numb {
    companion object {
        val numbers = (1..30).toMutableList()
        fun myFunc():String {
            val random = numbers.random()
            numbers.remove(random)
            return "$random"
        }
    }
}