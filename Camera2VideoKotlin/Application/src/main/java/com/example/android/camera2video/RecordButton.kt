package com.example.android.camera2video

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet

class RecordButton: ConstraintLayout {
    constructor(context: Context): this(context, null)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    init {
        inflate(context, R.layout.record_button, this)
    }
}