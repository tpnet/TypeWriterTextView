package vip.skyhand.typerwriter

import android.support.annotation.ColorInt
import android.text.TextPaint
import android.text.style.ForegroundColorSpan

/**
 * 带透明度设置文字颜色的span
 */
class AlphaColorSpan(@ColorInt val mColor: Int, override var mAlpha: Float = 0F) : ForegroundColorSpan(mColor), BaseSpan {

    override fun updateDrawState(tp: TextPaint) {
        tp.color = getColor(getForegroundColor())
    }

}
