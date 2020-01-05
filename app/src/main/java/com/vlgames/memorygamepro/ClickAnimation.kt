package com.vlgames.memorygamepro

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.RequiresApi
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

//object basic for function view
object ClickEffect {
    private val randomColor:Int
        get() {
            val rnd = Random()
            val transparent = 100
            return Color.argb(transparent, 56 + rnd.nextInt(700), 56 + rnd.nextInt(700),
                56 + rnd.nextInt(700))
        }
    private val handle = Handler()
    class AxisPoint {
        internal var x:Int = 0
        internal var y:Int = 0
        companion object {
            internal fun getCenter(v: View): AxisPoint {
                val point = AxisPoint()
                point.x = v.x.toInt() + v.width / 2
                point.y = v.y.toInt() + v.height / 2
                return point
            }
        }
    }
//      private fun setRandomColor(d: Drawable) {
//        val rnd = Random()
//        val transparent = 100
//        val color = Color.argb(transparent, 56 + rnd.nextInt(700), 56 + rnd.nextInt(700),
//            56 + rnd.nextInt(700))
//        d.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.LIGHTEN)
//    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    fun animNova(context: Context, v: ImageButton) {
        val effectSize = v.width * 2 / 3
        val color = randomColor // get a random color
        val p = AxisPoint.getCenter(v)
        val iv = ImageView(context)
        (v.parent as ViewGroup).addView(iv) // add View to parent
        val gd = GradientDrawable()
        gd.setColor(color)
        gd.shape = GradientDrawable.OVAL
        gd.setStroke(8, color * 7 / 10)
        iv.background = gd
        iv.layoutParams.height = effectSize
        iv.layoutParams.width = effectSize
        iv.x = (p.x - effectSize / 2).toFloat()
        iv.y = (p.y - effectSize / 2).toFloat()
        v.bringToFront() // bring original view to front
        val ip = DecelerateInterpolator()
        iv.animate().scaleX(25f).scaleY(25f).setDuration(1000).alpha(0f).interpolator = ip
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun animRipple(context:Context, v:View) {
        animRing(context, v, 1500)
        animRing(context, v, 1500)
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun animRing(context:Context, v:View, duration:Int) {
        val effectSize = v.width * 2 / 3
        val color = randomColor // get a random color
        val p = AxisPoint.getCenter(v)
        val iv = ImageView(context)
        (v.parent as ViewGroup).addView(iv) // add View to parent
        val gd = GradientDrawable()
        gd.shape = GradientDrawable.RECTANGLE
        gd.setStroke(6, color)
        iv.background = gd
        iv.layoutParams.height = effectSize
        iv.layoutParams.width = effectSize
        iv.x = (p.x - effectSize / 2).toFloat()
        iv.y = (p.y - effectSize / 2).toFloat()
        v.bringToFront() // bring original view to front
        val ip = DecelerateInterpolator()
        iv.animate().scaleX(6.5f).scaleY(6.5f).setDuration(duration.toLong()).alpha(0f).interpolator = ip
    }

//      @SuppressLint("WrongConstant")
//      @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
//      fun animParticalNova(context:Context, v:View, particles:Int) {
//        val radious = v.width * 6 / 5
//        val ballSize = v.width / 8
//        val color = randomColor // get a random color
//        val p = AxisPoint.getCenter(v)
//        val points = ArrayList<AxisPoint>()
//        for (i in 0 until particles)
//        {
//            val point = AxisPoint()
//            point.x = (cos(Math.PI * 2.0 * i.toDouble() / particles) * radious).toInt()
//            point.y = (sin(Math.PI * 2.0 * i.toDouble() / particles) * radious).toInt()
//            points.add(point)
//            val iv = ImageView(context)
//            (v.parent as ViewGroup).addView(iv) // add View to parent
//            val gd = GradientDrawable()
//            gd.setColor(color)
//            gd.shape = GradientDrawable.SWEEP_GRADIENT
//            iv.background = gd
//            iv.layoutParams.height = ballSize
//            iv.layoutParams.width = ballSize
//            iv.x = (p.x - ballSize / 2).toFloat()
//            iv.y = (p.y - ballSize / 2).toFloat()
//            v.bringToFront() // bring original view to front
//            val ip = LinearOutSlowInInterpolator()
//            iv.animate().translationXBy(point.x.toFloat()).translationYBy(point.y.toFloat()).scaleX(1.5f).scaleY(1.5f).setDuration(1000).alpha(0f)
//                .interpolator = ip
//        }
//    }

//    @SuppressLint("WrongConstant")
//    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
//    fun animExplosion(context:Context, v:View) {
//        animParticalNova(context, v, 18)
//        val particles = 9
//        val radious = v.width * 8 / 5
//        val ballSize = v.width / 6
//        val color = randomColor // get a random color
//        val p = AxisPoint.getCenter(v)
//        val points = ArrayList<AxisPoint>()
//        for (i in 0 until particles)
//        {
//            val point = AxisPoint()
//            point.x = (cos(Math.PI * 2.0 * i.toDouble() / particles) * radious).toInt()
//            point.y = (sin(Math.PI * 2.0 * i.toDouble() / particles) * radious).toInt()
//            points.add(point)
//            val iv = ImageView(context)
//            (v.parent as ViewGroup).addView(iv) // add View to parent
//            val gd = GradientDrawable()
//            gd.setColor(color)
//            gd.shape = GradientDrawable.RECTANGLE
//            iv.background = gd
//            iv.layoutParams.height = ballSize
//            iv.layoutParams.width = ballSize
//            iv.x = (p.x - ballSize / 2).toFloat()
//            iv.y = (p.y - ballSize / 2).toFloat()
//            v.bringToFront() // bring original view to front
//            val ip = DecelerateInterpolator()
//            iv.animate().translationXBy(point.x.toFloat()).translationYBy(point.y.toFloat()).scaleX(2f).scaleY(2f).setDuration(400).alpha(0f)
//                .interpolator = ip
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun animHelixNova(context:Context, v:ImageButton, particles:Int) {
        val radious = (v.width * 3)
        val angle = Math.PI * 2 / 3 // total spinning angle (rad)
        val duration = 600
        val ballSize = v.width / 6
        val points = ArrayList<ImageView>()
        for (i in 0 until particles)
        {
            val iv = ImageView(context)
            (v.parent as ViewGroup).addView(iv) // add View to parent
            val gd = GradientDrawable()
            gd.setColor(randomColor)
            gd.shape = GradientDrawable.OVAL
            iv.background = gd
            iv.layoutParams.height = ballSize
            iv.layoutParams.width = ballSize
            points.add(iv)
        }
        v.bringToFront() // bring original view to front
        val start = System.currentTimeMillis()
        Thread(object:Runnable {
            public override fun run() {
                val t = (System.currentTimeMillis() - start).toInt()
                val progress = (t * 1.0) / duration
                for (i in points.indices)
                {
                    val iv = points[i]
                    val x = (AxisPoint.getCenter(v).x - iv.width / 2 + ((cos(angle * progress + Math.PI * 2.0 * i.toDouble() / points.size)
                            * radious.toDouble() * progress) / 2))
                    val y = (AxisPoint.getCenter(v).y - iv.height / 2 + ((sin(angle * progress + Math.PI * 2.0 * i.toDouble() / points.size)
                            * radious.toDouble() * progress) / 2))
                    iv.x = x.toFloat()
                    iv.y = y.toFloat()
                    iv.scaleX = 2.toFloat()
                    iv.scaleY = 2.toFloat()
                    iv.alpha = (1 - progress).toFloat()
                }
                if (progress < 1)
                    handle.postDelayed(this, 10)
                else
                    for (i in points.indices)
                    {
                        points[i].visibility = View.GONE
                    }
            }
        }).start()
    }
    fun animJumping(v:View) {
        val duration = 600
        val start = System.currentTimeMillis()
        val origin = v.y
        Thread(object:Runnable {
           override fun run() {
                val t = (System.currentTimeMillis() - start).toInt()
                val progress = (t * 1.0) / duration
                val y = abs(
                    (v.height
                            * sin(3.0 * Math.PI * progress)
                            * (1 - progress))
                ).toFloat()
                v.y = origin - y
                Timber.d("/$y$progress")
                if (progress < 1)
                    handle.postDelayed(this, 20)
                else
                    v.y = origin
            }
        }).start()
    }
    fun animShaking(v: View ) {

        val duration = 1000
        val start = System.currentTimeMillis()
        val origin = v.y
        val rotateAngle = (Math.PI).toFloat() * 8
        val waveTimes = 5f
        Thread(object:Runnable {
            override fun run() {
                val t = (System.currentTimeMillis() - start).toInt()
                val progress = (t * 1.0) / duration
                val y = abs(
                    (v.height
                            * sin(Math.PI * progress)
                            * (1 - progress))
                ).toFloat()
                v.y = origin - y
                v.rotation = ((rotateAngle * waveTimes * progress) % rotateAngle - 0.5 * rotateAngle).toFloat()
                v.rotation = (45 * sin(progress * Math.PI * 6.0)).toFloat()
                Timber.d("/$y$progress")
                if (progress < 1)
                    handle.postDelayed(this, 10)
                else
                {
                    v.y = origin
                    v.rotation = 0f
                }
            }
        }).start()
    }
}

