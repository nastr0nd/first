package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.util.rangeTo
import com.example.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun onClickResult(view:View){
        numbersSize -= 1
        if (numbersSize < 0) {
            binding.res.text = "Please, Stop!"
        } else binding.res.text = myFunc()
    }
    val numbers = (1..30).toMutableList()
    var numbersSize = numbers.size

    fun myFunc():String {
        val random = numbers.random()
        numbers.remove(random)
        return "$random"
    }
}