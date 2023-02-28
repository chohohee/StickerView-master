package com.xiaopo.flying.sticker.sticker

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.annotation.IntDef
import android.view.MotionEvent
import com.xiaopo.flying.sticker.StickerView
import com.xiaopo.flying.sticker.icon.StickerIconEvent

class  BitmapStickerIcon(drawable: Drawable, @Gravity gravity: Int) : DrawableSticker(drawable),
    StickerIconEvent {

    @IntDef(LEFT_TOP, RIGHT_TOP, LEFT_BOTTOM, RIGHT_BOTTOM)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Gravity

    private val iconRadius: Float = DEFAULT_ICON_RADIUS
    var x = 0f
    var y = 0f

    @get:Gravity
    @Gravity
    var position = LEFT_TOP
    var iconEvent: StickerIconEvent? = null

    init {
        position = gravity
    }

    fun draw(canvas: Canvas, paint: Paint?) {
        canvas.drawCircle(x, y, iconRadius, paint!!)
        super.draw(canvas)
    }

    fun getIconRadius(): Float = iconRadius

    override fun onActionDown(stickerView: StickerView, event: MotionEvent) {
        iconEvent?.onActionDown(stickerView, event)
    }

    override fun onActionMove(stickerView: StickerView, event: MotionEvent) {
        iconEvent?.onActionMove(stickerView, event)
    }

    override fun onActionUp(stickerView: StickerView, event: MotionEvent) {
        iconEvent?.onActionUp(stickerView, event)
    }

    companion object {
        const val DEFAULT_ICON_RADIUS = 30f // 아이콘 반지름

        const val LEFT_TOP = 0
        const val RIGHT_TOP = 1
        const val LEFT_BOTTOM = 2
        const val RIGHT_BOTTOM = 3
    }
}