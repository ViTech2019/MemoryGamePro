package com.vlgames.memorygamepro



import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.vlgames.memorygamepro.R.drawable.*
import com.vlgames.memorygamepro.ClickEffect.animShaking


class GridAdapter(c: Context, private var mContext: Context) : BaseAdapter() {

    var imageBtn: ImageButton? = null
    private lateinit var viewFinish: TextView

    private val pieces: Array<Int>
    private var imageViews: ArrayList<ImageView>? = null
    private var pieceUp = -1


    private val mThumbIds = arrayOf(
        blue_monsters, green_monsters, yellow_monsters, red_monsters, orange_monsters,
        purple_monsters, blue_monsters2, green_monsters2, blue_monsters, green_monsters
    )

    private fun createImageViews() {
        imageViews = ArrayList()
        for (position in 0 until count) {
            val imageView = ImageView(mContext)
            imageView.layoutParams = AbsListView.LayoutParams(250, 250)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setPadding(5, 5, 5, 5)
            imageView.setImageResource(alien_hidden_card)
            imageViews!!.add(imageView)
            installClick(position)
        }
    }

    init {
        mContext = c
        val ipieces: MutableList<Int> = arrayListOf()
        for (i in 0..9) {
            ipieces.add(i)
            ipieces.add(i)
        }
        ipieces.shuffle()
        pieces = ipieces.toTypedArray()
        createImageViews()
    }

    override fun getCount(): Int {
        return 20 //mThumbIds.length;
    }

    override fun getItem(position: Int): Any {
        return imageViews!![position]
    }

    override fun getItemId(position: Int): Long {
        return pieces[position].toLong()
    }
    private fun show(position: Int) {
        val img: ImageView = imageViews!![position]
        val piece = pieces[position]
        img.setImageResource(mThumbIds[piece])
        // references to our images
    }

    // create a new ImageView for each item referenced by the Adapter
    @Synchronized
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return imageViews!![position]
    }

    fun installClick(position: Int) { // final int pos = position;
        val self = this
        Log.d("ImageAdapter", "click *$position")
        val imageView: ImageView = imageViews!![position]
        imageView.setOnClickListener { v ->
            val pos = imageViews!!.indexOf(v as ImageView?)
            Log.d("ImageAdapter", "click!$pos")
            show(pos)

            pieceUp = if (pieceUp == -1 || pieceUp == pos) { // first click
                pos
            } else { // second click
                if (pieces[pos] == pieces[pieceUp] ) { // ok, it's equal
                    Toast.makeText(mContext, "good!", Toast.LENGTH_LONG).show()
//                    imageBtn?.findViewById<ImageButton>(R.id.view_image_animation1)
//                    imageBtn?.visibility = View.VISIBLE//enable visible viewImage
//                    imageBtn?.setBackgroundResource(position)//view image animation is button onClick
                    animShaking(v)   // jump animation button
//                    animShaking(v.findViewWithTag(pieceUp))// jump animation button
//                    animNova(mContext, imageBtn!!) // imageButton animation
                    removeClick(pos)
                    removeClick(pieceUp)

                    // remove click handler
                } else { // try again
                    val aux = intArrayOf(pieceUp, pos)
                    val update = SleepHide(self, aux)
                    val mHandler = Handler()
                    mHandler.postDelayed(update, 350)
                }
                -1
            }
        }
    }

//    fun FinishedWin(view: View) {
//        val viewLinear = view.findViewById<View>(R.id.linear_text_finish1) //*** go to fun ***//
//        view.back_to_next_btn1.setBackgroundResource(next)//replays imageButton
//        viewLinear.visibility = View.VISIBLE//view linear
//        viewFinish.text = "You WIN!!!"
//        animJumping(viewFinish) //jump animation
//        animJumping(view.back_to_next_btn1)//jump animation
//    }

    fun removeClick(position: Int) {
        val aux: ImageView = imageViews!![position]
        aux.setOnClickListener(null)
    }

    fun hide(position: Int) {
        val img: ImageView = imageViews!![position]
        pieces[position]
        img.setImageResource(alien_hidden_card)
    }

}





