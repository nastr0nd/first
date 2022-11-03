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
        var numbers = mutableListOf(1..30)
        var random = numbers.random()

        val bottomSheetFragment = BottomSheetFragment()
        binding.btnShow.setOnClickListener{
            bottomSheetFragment.show(supportFragmentManager, "BottomSheetDialog")
        }
    }
}