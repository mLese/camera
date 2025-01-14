package com.example.android.camera2video

import android.content.Context
import android.graphics.drawable.shapes.Shape
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_capture_button_test.view.*
import kotlinx.android.synthetic.main.record_button.view.*

class RecordButton: ConstraintLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    var limit = 20
    val maxProgress = 10000
    val interval = maxProgress / ((limit * 1000) / 30)
    val progressUpdateHandler = Handler()
    var progressRunning = false

    init {
        inflate(context, R.layout.record_button, this)

        progressBar.setOnClickListener { view ->
            if (progressRunning) {
                progressRunning = false
                progressBar.background = view.context.getDrawable(R.drawable.circular_progress_background)
                progressBar.progress = -1
            } else {
                progressRunning = true
                progressBar.background = view.context.getDrawable(R.drawable.circular_progress_background_recording)
                handler.post(progressRunnable)
            }
        }
    }

    private val progressRunnable: Runnable = object: Runnable {
        override fun run() {
            progressBar.progress = progressBar.progress + interval
            if (progressBar.progress > maxProgress) progressRunning = false
            if (progressRunning) {
                progressUpdateHandler.postDelayed(this, 30)
            } else {
                progressBar.progress = 0
            }
        }
    }

}