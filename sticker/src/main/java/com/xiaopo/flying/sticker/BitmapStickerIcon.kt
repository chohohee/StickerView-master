package com.xiaopo.flying.sticker

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.annotation.IntDef
import android.view.MotionEvent
import com.xiaopo.flying.sticker.icon.StickerIconEvent
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * @author wupanjie
 */
class BitmapStickerIcon(drawable: Drawable?, @Gravity gravity: Int) : DrawableSticker(
    drawable!!
), StickerIconEvent {
    @IntDef(LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTOM)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Gravity

    var x = 0f
    var y = 0f

    @get:Gravity
    @Gravity
    var position = LEFT_TOP
    var iconEvent: StickerIconEvent? = null

    init {
        position = gravity
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }

    override fun onActionDown(stickerView: StickerView, event: MotionEvent) {
        if (iconEvent != null) {
            iconEvent!!.onActionDown(stickerView, event)
        }
    }

    override fun onActionMove(stickerView: StickerView, event: MotionEvent) {
        if (iconEvent != null) {
            iconEvent!!.onActionMove(stickerView, event)
        }
    }

    override fun onActionUp(stickerView: StickerView, event: MotionEvent) {
        if (iconEvent != null) {
            iconEvent!!.onActionUp(stickerView, event)
        }
    }

    companion object {
        const val LEFT_TOP = 0
        const val RIGHT_TOP = 1
        const val LEFT_BOTTOM = 2
        const val RIGHT_BOTOM = 3
    }
}