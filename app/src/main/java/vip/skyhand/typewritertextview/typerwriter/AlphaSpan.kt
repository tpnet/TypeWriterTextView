package vip.skyhand.typewritertextview.typerwriter

import android.text.TextPaint
import android.text.style.CharacterStyle
import vip.skyhand.typewritertextview.typerwriter.BaseSpan

open class AlphaSpan(override var mAlpha: Float) : CharacterStyle() , BaseSpan {

    override fun updateDrawState(tp: TextPaint) {
        tp.color = getColor(tp.color)
    }

}
