package com.vlgames.memorygamepro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vlgames.memorygamepro.R.drawable.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image: MutableList<Int> = mutableListOf(
            bike_1, bike_2, bike_3, bike_4, bike_5, bike_6,
            bike_7, bike_8, bike_1, bike_2, bike_3, bike_4, bike_5, bike_6,
            bike_7, bike_8
        )

        val buttons = arrayOf(
            button1, button2, button3, button4, button5, button6, button7, button8,
            button9, button10, button11, button12, button13, button14, button15, button16
        )

        val cardBack = hidden_card
        var clicked = 0
        var turnOver = false
        var lastClicked = -1

        image.shuffle()
        for (i in 0..15) {
//            buttons[i].setBackgroundResource(bike_1)
            buttons[i].text = "cardBack"
            buttons[i].textSize = 0.0F
            buttons[i].setOnClickListener {
                if (buttons[i].text == "cardBack" && !turnOver) {
                    buttons[i].setBackgroundResource(image[i])
                    buttons[i].setText(image[i])
                    if (clicked == 0) {
                        lastClicked = i
                    }
                    clicked++

                } else if (buttons[i].text !in "cardBack") {
                    buttons[i].setBackgroundResource(cardBack)
                    buttons[i].text = "cardBack"
                    clicked--
                }


                if (clicked == 2) {
                    turnOver = true
                    if (buttons[i].text == buttons[lastClicked].text) {
                        buttons[i].isClickable = false
                        buttons[lastClicked].isClickable = false
                        turnOver = false
                        clicked = 0
                    }
                } else if (clicked == 0) {
                    turnOver = false
                }
            }
        }
    }
}
