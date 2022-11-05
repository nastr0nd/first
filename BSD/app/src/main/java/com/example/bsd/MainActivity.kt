package com.example.bsd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bsd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val numbers = (1..30).toMutableList()
        var numbersSize = numbers.size
        val bottomSheetFragment = BottomSheetFragment()
        binding.btnShow.setOnClickListener{
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")

        }

        fun myFunc():String {
            val random = numbers.random()
            numbers.remove(random)
            return "$random"
        }
    }
}