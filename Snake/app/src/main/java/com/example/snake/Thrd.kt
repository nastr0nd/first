package com.example.snake

import android.widget.LinearLayout

object Thrd {

    var nextMove:() -> Unit = {}
    var isPlay = true
    private val thread: Thread
    init {
        thread = Thread(
            Runnable {
            while (true) {
                Thread.sleep(600)
                if (isPlay) {
                    nextMove()
                }
            }
        })
        thread.start()
    }

    fun startGame() {
        isPlay = true
    }
}