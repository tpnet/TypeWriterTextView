package vip.skyhand.typerwriter

import android.text.TextPaint
import android.text.style.AbsoluteSizeSpan


/**
 * 带透明度设置文字大小的span
 */
class AlphaSizeSpan(val mSize: Int, override var mAlpha: Float = 0F) : AbsoluteSizeSpan(mSize), BaseSpan {

    override fun updateDrawState(tp: TextPaint) {
        super.updateDrawState(tp)
        tp.color = getColor(tp.color)
    }

}
