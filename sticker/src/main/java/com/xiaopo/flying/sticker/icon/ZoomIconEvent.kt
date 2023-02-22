package com.xiaopo.flying.sticker.icon

import android.view.MotionEvent
import com.xiaopo.flying.sticker.StickerView

/**
 * @author wupanjie
 */
class ZoomIconEvent : StickerIconEvent {
    override fun onActionDown(stickerView: StickerView, event: MotionEvent) {}
    override fun onActionMove(stickerView: StickerView, event: MotionEvent) {
        stickerView.zoomAndRotateCurrentSticker(event)
    }

    override fun onActionUp(stickerView: StickerView, event: MotionEvent) {
        if (stickerView.onStickerOperationListener != null) {
            stickerView.onStickerOperationListener!!
                .onStickerZoomFinished(stickerView.currentSticker!!)
        }
    }
}