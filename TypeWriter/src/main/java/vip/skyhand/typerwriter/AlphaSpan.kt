package vip.skyhand.typerwriter

import android.text.TextPaint
import android.text.style.CharacterStyle

/**
 * 带透明度设置的span
 */
open class AlphaSpan(override var mAlpha: Float) : CharacterStyle() , BaseSpan {

    override fun updateDrawState(tp: TextPaint) {
        tp.color = getColor(tp.color)
    }

}
