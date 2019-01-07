package vip.skyhand.typerwriter

interface BaseSpan{

    var mAlpha: Float

    fun setAlpha(alpha: Float) {
        mAlpha = Math.max(Math.min(alpha, 1.0f), 0.0f)
    }

    fun getColor(color: Int): Int{
        return (0xFF * mAlpha).toInt() shl 24 or (color and 0x00FFFFFF)
    }
}