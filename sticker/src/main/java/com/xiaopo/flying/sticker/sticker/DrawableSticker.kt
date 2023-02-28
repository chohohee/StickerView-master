package com.xiaopo.flying.sticker.sticker

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.IntRange

open class DrawableSticker(private var drawable: Drawable) : Sticker() {
    private val realBounds: Rect

    init {
        realBounds = Rect(0, 0, width, height)
    }

    override fun getDrawable(): Drawable = drawable

    override fun setDrawable(textDrawable: Drawable) {
        this.drawable = textDrawable
    }

    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.concat(matrix)
        drawable.bounds = realBounds
        drawable.draw(canvas)
        canvas.restore()
    }

    override fun getWidth(): Int = drawable.intrinsicWidth

    override fun getHeight(): Int = drawable.intrinsicHeight
}