package com.vlgames.memorygamepro

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.*
import com.vlgames.memorygamepro.ClickEffect.animJumping
import kotlinx.android.synthetic.main.activity_level.*





class LevelActivity: Activity() {

    private lateinit var viewFinish: TextView
    private lateinit var textView: TextView
    var clicked = 0

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        gameTime()//function timer end text you Lose time out
        buildPieces()

        back_to_next_btn1.setOnClickListener {
            val intent = Intent(this, LevelActivity::class.java) //back to mainActivity
            startActivity(intent) // back to reset games
            finish()
        }
    }

    private fun buildPieces() {
        val gridview = findViewById<GridView>(R.id.gridview)
        gridview.adapter = GridAdapter(this,this)
    }




    private fun gameTime() {
        textView = findViewById(R.id.text_timer_view1)
        viewFinish= findViewById(R.id.text_view_finish1)

        val seconds = 80
        object: CountDownTimer((seconds * 1000).toLong(), 1000) { //format timer to seconds
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished:Long) {
                textView.text = "" + (millisUntilFinished / 1000).toInt()

            }
            override fun onFinish() {
                val viewLinear = findViewById<View>(R.id.linear_text_finish1)//***go tu fun***//
                viewLinear.visibility = View.VISIBLE//view linear
                viewFinish.text = "You Lose, try again"
                animJumping(viewFinish)//jump animation
                animJumping(back_to_next_btn1)//jump animation end back to restart game
                clicked = 0
            }
        }.start()
    }


}
