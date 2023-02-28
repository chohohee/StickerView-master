package com.xiaopo.flying.sticker.icon

import android.view.MotionEvent
import com.xiaopo.flying.sticker.StickerView

interface StickerIconEvent {
    fun onActionDown(stickerView: StickerView, event: MotionEvent)
    fun onActionMove(stickerView: StickerView, event: MotionEvent)
    fun onActionUp(stickerView: StickerView, event: MotionEvent)
}