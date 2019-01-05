package vip.skyhand.typewritertextview.typerwriter

import android.support.annotation.ColorInt
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import vip.skyhand.typewritertextview.typerwriter.BaseSpan

class AlphaColorSpan(@ColorInt val mColor: Int, override var mAlpha: Float = 0F) : ForegroundColorSpan(mColor),
    BaseSpan {

    override fun updateDrawState(tp: TextPaint) {
        tp.color = getColor(getForegroundColor())
    }

}
