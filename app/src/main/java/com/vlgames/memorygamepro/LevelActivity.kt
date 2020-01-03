package com.vlgames.memorygamepro

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.vlgames.memorygamepro.R.drawable.*
import kotlinx.android.synthetic.main.activity_level.*
import java.util.*
import kotlin.concurrent.timerTask

class LevelActivity : AppCompatActivity() {


    private var arrayImage: MutableList<Int> = mutableListOf()
    lateinit var arrayButtons: Array<Button>
    private val cardBack = hidden_card
    var clicked = 0
    var turnOver = false
    var lastClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        arrayImage = mutableListOf(
            bike_1, bike_2, bike_3, bike_4, bike_5,
            bike_6, bike_7, bike_8, bike_1, bike_2, bike_3, bike_4, bike_5, bike_6, bike_7, bike_8
        )

        arrayButtons = arrayOf(
            button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button10, button11, button12, button13, button14, button15, button16
        )



        arrayImage.shuffle()
        for (i in 0..15) {
            clicked = 0
            arrayButtons[i].tag
            arrayButtons[i].textSize = 0.0F
            arrayButtons[i].setText(arrayImage[i])
            arrayButtons[i].setOnClickListener {
                if (clicked == 2) {
                    turnOver = true
                    if (arrayButtons[i].toString() == arrayButtons[lastClicked].toString()) {
                        arrayButtons[i].isClickable = false
                        arrayButtons[lastClicked].isClickable = false
                        turnOver = false
                    }
                } else {
                    arrayButtons[i].setBackgroundResource(arrayImage[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++
                    if (clicked == 2) {
                        timerTurnOver(arrayButtons[i], arrayButtons[lastClicked])
                    }
                }
                Log.d("Clicked", arrayImage[i].toString())
                Log.d("Clicked0", clicked.toString())
                Log.d("Clicked1", arrayImage[lastClicked].toString())


            }
        }
    }
//        for (i in 0..15) {
//            arrayButtons[i].textSize = 0.0F
//            arrayButtons[i].setText(image[i])
//            arrayButtons[i].setOnClickListener {
//                if (clicked == 0) {
//                    lastClicked = i
//                }
//                if (clicked == 2) {
//                    if (arrayButtons[i].text == arrayButtons[lastClicked].text) {
//                        arrayButtons[i].isClickable = false
//                        arrayButtons[lastClicked].isClickable = false
//                        turnOver = false
//                    } else {
//                        timerTurnOver(arrayButtons[i], arrayButtons[lastClicked])
//                        clicked = 0
//                        clicked++
//                    }
//
//                } else {
//                    arrayButtons[i].setBackgroundResource(image[i])
//                }
//
//                Log.d("Clicked", clicked.toString())
//            }
//        }
//    }

    private fun timerTurnOver(i: View, lastClicked: View) {
        Timer().schedule(timerTask {
            flipOverCards(i, lastClicked as TextView)
        }, 1000)
    }

    private fun flipOverCards(i: View, lastClicked: View) {
        i.setBackgroundResource(cardBack)
        lastClicked.setBackgroundResource(cardBack)
        clicked = 0
    }


}