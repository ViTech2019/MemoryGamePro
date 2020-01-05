package com.vlgames.memorygamepro

import android.util.Log
import com.vlgames.memorygamepro.GridAdapter

internal class SleepHide(
    private val adapter: GridAdapter,
    private val positions: IntArray
) :
    Runnable {
    override fun run() {
        Log.d("SleepHide", "run!")
        adapter.hide(positions[0])
        adapter.hide(positions[1])
        adapter.installClick(positions[0])
        adapter.installClick(positions[1])
    }

    init {
        Log.d("SleepHide", "click!")
        adapter.removeClick(positions[0])
        adapter.removeClick(positions[1])
    }
}