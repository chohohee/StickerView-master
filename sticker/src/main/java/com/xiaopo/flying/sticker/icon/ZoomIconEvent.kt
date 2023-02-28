package com.xiaopo.flying.sticker.icon

import android.view.MotionEvent
import com.xiaopo.flying.sticker.StickerView

class ZoomIconEvent : StickerIconEvent {
    override fun onActionDown(stickerView: StickerView, event: MotionEvent) {}
    override fun onActionMove(stickerView: StickerView, event: MotionEvent) {
        stickerView.zoomAndRotateCurrentSticker(event)
    }
    override fun onActionUp(stickerView: StickerView, event: MotionEvent) {
        stickerView.currentSticker?.let { currentSticker ->
            stickerView.onStickerOperationListener?.onStickerZoomFinished(currentSticker)
        }
    }
}