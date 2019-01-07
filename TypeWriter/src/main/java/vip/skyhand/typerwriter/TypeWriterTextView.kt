package vip.skyhand.typerwriter

import android.content.Context
import android.graphics.Canvas
import android.support.annotation.ColorInt
import android.support.v4.view.ViewCompat
import android.support.v7.widget.AppCompatTextView
import android.text.SpannableString
import android.text.Spanned
import android.util.AttributeSet
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.widget.TextView

/**
 * 打字效果的Textview，可以设置文字颜色和大小
 * @author SkyHand
 * @url https://github.com/tpnet/TypeWriterTextView
 */
class TypeWriterTextView(context: Context, attrs: AttributeSet? = null): AppCompatTextView (context,attrs){

    //透明的插值器
    private var mInterpolator: Interpolator = LinearInterpolator()
    private var mStart: Long = 0
    //每个字的时长
    private var mDurationPerLetter: Int = 30

    private var mFadeSpanText: SpannableString = SpannableString("")
    private var mText: String = ""

    private var mListener: WriteStatusListener? = null

    //设置文字颜色的位置
    private var mColor = 0
    private var mColorStartList: MutableList<Int>? = null
    private var mColorEndList: MutableList<Int>? = null

    //设置文字大小的位置
    private var mSize = 0
    private var mSizeStartList: MutableList<Int>? = null
    private var mSizeEndList: MutableList<Int>? = null

    //是否在打字中
    var isAnimating = false
        private set(value) {
            field = value
            if(value){
                mListener?.onWriteStart(this)
            }else{
                mListener?.onWriteFinish(this)
            }
        }


    fun setInterpolator(interpolator: Interpolator) {
        mInterpolator = interpolator
    }

    fun setDurationPerLetter(durationPerLetter: Int) {
        mDurationPerLetter = durationPerLetter
    }


    fun setText(text: String,isClearColorSize: Boolean = true) {

        val length = text.length
        if (length == 0) return

        if(isClearColorSize){
            clearList()
        }

        mText = text
        mFadeSpanText = SpannableString(text)
        val letters = mFadeSpanText.getSpans(0, mFadeSpanText.length, BaseSpan::class.java)
        for (letter in letters) {
            mFadeSpanText.removeSpan(letter)
        }


        if(mColorStartList?.isNotEmpty() == true && mColorStartList?.size == mColorEndList?.size && mColor != 0){
            mColorStartList?.forEachIndexed{ index,bean ->
                mFadeSpanText.setSpan(AlphaColorSpan(mColor), bean, mColorEndList!![index], Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            }
        }

        if(mSizeStartList?.isNotEmpty() == true && mSizeStartList?.size == mSizeEndList?.size && mSize != 0){
            mSizeStartList?.forEachIndexed{ index,bean ->
                mFadeSpanText.setSpan(AlphaSizeSpan(mSize), bean, mSizeEndList!![index], Spanned.SPAN_INCLUSIVE_INCLUSIVE)
            }
        }


        for (i in 0 until length) {
            mFadeSpanText.setSpan(AlphaSpan(0f), i, i + 1, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        }

        super.setText(mFadeSpanText, TextView.BufferType.SPANNABLE)

        mStart = AnimationUtils.currentAnimationTimeMillis()
        isAnimating = true
        ViewCompat.postInvalidateOnAnimation(this)
    }

    /**
     * 可以设置部分文字颜色
     * @param text 全部文字
     * @param mStartList 开始的位置
     * @param mEndList 结束的位置
     */
    fun setTextWithColor(text: String,@ColorInt color: Int,mStartList: MutableList<Int>,mEndList: MutableList<Int>){
        clearList()
        mColor = color
        mColorStartList = mStartList
        mColorEndList = mEndList
        setText(text,false)
    }


    /**
     * 可以设置部分文字大小
     * @param text 全部文字
     * @param mStartList 开始的位置
     * @param mEndList 结束的位置
     */
    fun setTextWithSize(text: String,size: Int,mStartList: MutableList<Int>,mEndList: MutableList<Int>){
        clearList()
        mSize = size
        mSizeStartList = mStartList
        mSizeEndList = mEndList
        setText(text,false)
    }

    /**
     * 同时可以设置部分颜色和大小
     * @param text 全部文字
     * @param color 颜色
     * @param size 大小,单位px
     * @param mStartList 开始的位置
     * @param mEndList 结束的位置
     */
    fun setTextWithColorAndSize(text: String,@ColorInt color: Int,size: Int,mStartList: MutableList<Int>,mEndList: MutableList<Int>){
        clearList()
        mColor = color
        mSize = size
        mSizeStartList = mStartList
        mColorStartList = mStartList
        mSizeEndList = mEndList
        mColorEndList = mEndList
        setText(text,false)
    }

    private fun clearList(){
        mColorStartList?.clear()
        mColorEndList?.clear()
        mSizeStartList?.clear()
        mSizeEndList?.clear()

        mColorStartList = null
        mColorEndList = null
        mSizeStartList = null
        mSizeEndList = null
    }

    override fun getText(): String {
        return mText
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isAnimating) {
            //已经消耗的时长
            val deltaTap = AnimationUtils.currentAnimationTimeMillis() - mStart

            val letters = mFadeSpanText.getSpans(0, mFadeSpanText.length, BaseSpan::class.java)

            letters.forEachIndexed { index, baseSpan ->
                val delta = Math.max(Math.min(deltaTap - index * mDurationPerLetter, mDurationPerLetter.toLong()), 0).toFloat()
                baseSpan.setAlpha(mInterpolator.getInterpolation(delta / mDurationPerLetter))
            }

            if (deltaTap < mDurationPerLetter * letters.size) {
                //时间还没到，绘制还没完成，继续绘制
                ViewCompat.postInvalidateOnAnimation(this)
            } else {
                isAnimating = false
            }
        }
    }

    /**
     * 马上完成打字效果
     */
    fun setComplete() {
        if (isAnimating) {
            isAnimating = false

            val letters = mFadeSpanText.getSpans(0, mFadeSpanText.length, BaseSpan::class.java)
            for (i in letters.indices) {
                letters[i].setAlpha(1f)
            }
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    //设置打字状态监听器
    fun setWriteStatusListener(listener: WriteStatusListener) {
        mListener = listener
    }

}