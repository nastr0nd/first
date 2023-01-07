package com.example.snake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.snake.Thrd.isPlay
import com.example.snake.Thrd.startGame
import com.example.snake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val tail = mutableListOf<PartOfTail>()
    private var currentDirection: Direction = Direction.DOWN
    private val star by lazy {
        ImageView(this)
    }
    private val head by lazy {
        ImageView(this)
    }
    lateinit var container: FrameLayout
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        container = findViewById(R.id.container)
        head.layoutParams = FrameLayout.LayoutParams(100, 100)
        head.background = ContextCompat.getDrawable(this, R.drawable.head)
        startGame()
        generateStar()
        Thrd.nextMove = {move(Direction.DOWN)}
        binding.imArrowUp.setOnClickListener {Thrd.nextMove = {wrongDirection(Direction.UP, Direction.DOWN)}}
        binding.imArrowRight.setOnClickListener {Thrd.nextMove = {wrongDirection(Direction.RIGHT, Direction.LEFT)}}
        binding.imArrowDown.setOnClickListener {Thrd.nextMove = {wrongDirection(Direction.DOWN, Direction.UP)}}
        binding.imArrowLeft.setOnClickListener {Thrd.nextMove = {wrongDirection(Direction.LEFT, Direction.RIGHT)}}

    }
    private fun wrongDirection (rightDirection: Direction, reversedDirection: Direction) {
        if (currentDirection == reversedDirection) {
            move(currentDirection)
        } else {
            move(rightDirection)
        }
    }

    private fun generateStar() {
            star.layoutParams = FrameLayout.LayoutParams(100, 100)
            star.setImageResource(R.drawable.ic_star)
            (star.layoutParams as FrameLayout.LayoutParams).topMargin = (0 until 10).random() * 100
            (star.layoutParams as FrameLayout.LayoutParams).leftMargin = (0 until 10).random() * 100
            runOnUiThread {
                container.removeView(star)
                container.addView(star)
            }
    }

    private fun devour(){
        if(star.top == head.top && star.left == head.left) {
            generateStar()
            addPartOfTail(head.top, head.left)
        }
    }

    private fun addPartOfTail(top: Int, left: Int) {
        val tailPart = drawPartOfTail(top, left)
        tail.add(PartOfTail(top, left, tailPart))
    }

    private fun drawPartOfTail(top: Int, left: Int): ImageView {
        val tailImage = ImageView(this)
        tailImage.setImageResource(R.drawable.ic_star)
        tailImage.setBackgroundColor(ContextCompat.getColor(this, androidx.appcompat.R.color.material_blue_grey_800))
        tailImage.layoutParams = FrameLayout.LayoutParams(100, 100)
        (tailImage.layoutParams as FrameLayout.LayoutParams).topMargin = top
        (tailImage.layoutParams as FrameLayout.LayoutParams).leftMargin = left

        container.addView(tailImage)
        return tailImage
    }

    fun move(direction: Direction) {
        when(direction){
            Direction.UP -> (head.layoutParams as FrameLayout.LayoutParams).topMargin -= 100
            Direction.RIGHT -> (head.layoutParams as FrameLayout.LayoutParams).leftMargin += 100
            Direction.DOWN -> (head.layoutParams as FrameLayout.LayoutParams).topMargin += 100
            Direction.LEFT -> (head.layoutParams as FrameLayout.LayoutParams).leftMargin -= 100
        }
        currentDirection = direction
        runOnUiThread {
            if (crash()) {
                isPlay = false
                this.recreate()
                return@runOnUiThread
            }
            makeTailMove()
            devour()
            container.removeView(head)
            container.addView(head)
        }
    }

    private fun crash():Boolean {
        for (tailPart in tail) {
            if (tailPart.left == head.left && tailPart.top == head.top) {
                return true
            }
        }
            if (head.top < 0
                || head.left < 0
                || head.top > 1100
                || head.left > 1000) {
                return true
            }
            return false
    }

    private fun makeTailMove() {
        var tempTailPart: PartOfTail? = null
        for (index in 0 until tail.size) {
            val tailPart = tail[index]
            container.removeView(tailPart.imageView)
            if (index == 0) {
                tempTailPart = tailPart
                tail[index] = PartOfTail(head.top, head.left, drawPartOfTail(head.top, head.left))
            } else {
                val anotherTempPartOfTail = tail[index]
                tempTailPart?.let {
                    tail[index] = PartOfTail(it.top, it.left, drawPartOfTail(it.top, it.left))
                }
                tempTailPart = anotherTempPartOfTail
            }
        }
    }
}
enum class Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT
}