package com.xiaopo.flying.sticker

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.IntRange

/**
 * @author wupanjie
 */
open class DrawableSticker(private var drawable: Drawable) : Sticker() {
    private val realBounds: Rect

    init {
        realBounds = Rect(0, 0, width, height)
    }

    override fun getDrawable(): Drawable {
        return drawable
    }

    override fun setDrawable(textDrawable: Drawable): DrawableSticker {
        this.drawable = textDrawable
        return this
    }

    override fun draw(canvas: Canvas) {
        canvas.save()
        canvas.concat(matrix)
        drawable.bounds = realBounds
        drawable.draw(canvas)
        canvas.restore()
    }

    override fun setAlpha(@IntRange(from = 0, to = 255) alpha: Int): DrawableSticker {
        drawable.alpha = alpha
        return this
    }

    override fun getWidth(): Int {
        return drawable.intrinsicWidth
    }

    override fun getHeight(): Int {
        return drawable.intrinsicHeight
    }

    override fun release() {
        super.release()
        /**
         * todo
         * null 처리 주석
         */
//        if (drawable != null) {
//            drawable = null
//        }
    }
}