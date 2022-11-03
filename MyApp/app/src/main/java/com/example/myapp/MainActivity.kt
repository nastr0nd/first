package com.example.myapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun onClickResult(view:View){
        binding.res.text = myFunc()
    }
    val numbers = mutableListOf(1, 2, 3, 4, 5)
    var numbersSize = numbers.size

    fun myFunc():String {
        val random = numbers.random()
        numbers.remove(random)
        numbersSize -= 1
        var result = random.toString()
        if (numbersSize < 1) {
            result = "Please, Stop!"
        }
        return "$result"
    }
}