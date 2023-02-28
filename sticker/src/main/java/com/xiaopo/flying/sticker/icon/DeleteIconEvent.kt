package com.xiaopo.flying.sticker.icon

import android.view.MotionEvent
import com.xiaopo.flying.sticker.StickerView

class DeleteIconEvent : StickerIconEvent {
    override fun onActionDown(stickerView: StickerView, event: MotionEvent) {}
    override fun onActionMove(stickerView: StickerView, event: MotionEvent) {}
    override fun onActionUp(stickerView: StickerView, event: MotionEvent) {
        stickerView.removeCurrentSticker()
    }
}