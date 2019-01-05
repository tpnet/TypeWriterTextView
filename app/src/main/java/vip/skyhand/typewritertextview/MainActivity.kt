package vip.skyhand.typewritertextview

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import vip.skyhand.typewritertextview.typerwriter.TypeWriterTextView
import vip.skyhand.typewritertextview.typerwriter.WriteStatusListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val textLess = findViewById<TypeWriterTextView>(R.id.mTVTextLess)
        textLess.setText("是！")

        //普通的打字
        val textNormal = findViewById<TypeWriterTextView>(R.id.mTVTextNormal)
        textNormal.setText(getString(R.string.text))

        //部分文字变大的打字
        //变大第5-8的字 和 11-15的字
        var sizeStartList = arrayListOf(5,11)
        var sizeEndList = arrayListOf(8,15)
        val textSize = findViewById<TypeWriterTextView>(R.id.mTVTextSize)
        textSize.setTextWithSize(getString(R.string.text),80,sizeStartList,sizeEndList)

        //部分文字不同颜色的打字
        //变大第22-26的字 和 30-35的字
        var colorStartList = arrayListOf(22,30)
        var colorEndList = arrayListOf(26,35)
        val textColor = findViewById<TypeWriterTextView>(R.id.mTVTextColor)
        textColor.setTextWithColor(getString(R.string.text),Color.RED,colorStartList,colorEndList)

        //部分文字不同颜色和大小的打字
        //同事变大和设置颜色第15-20的字 和 30-35的字
        var colorSizeStartList = arrayListOf(15,30)
        var colorSizeEndList = arrayListOf(20,35)
        val textColorSize = findViewById<TypeWriterTextView>(R.id.mTVTextColorSize)
        //监听打字状态
        textColorSize.setWriteStatusListener(object : WriteStatusListener {
            override fun onWriteStart(view: TypeWriterTextView) {
                Log.e("@@","开始打字啦")
            }
            override fun onWriteFinish(view: TypeWriterTextView) {
                Log.e("@@","打字完毕啦")
            }
        })
        textColorSize.setTextWithColorAndSize(getString(R.string.text),Color.GREEN,60,colorSizeStartList,colorSizeEndList)


        //点击瞬间完成
        textLess.setOnClickListener { v ->
            if (!textLess.isAnimating) {
                //第二个参数为false，则不清除上次设置的格式，重新打字
                textLess.setText("是！",false)
            } else {
                textLess.setComplete()
            }
        }
        textNormal.setOnClickListener { v ->
            if (!textNormal.isAnimating) {
                //第二个参数为false，则不清除上次设置的格式，重新打字
                textNormal.setText(getString(R.string.text),false)
            } else {
                textNormal.setComplete()
            }
        }
        textColor.setOnClickListener { v ->
            if (!textColor.isAnimating) {
                //第二个参数为false，则不清除上次设置的格式，重新打字
                textColor.setText(getString(R.string.text),false)
            } else {
                textColor.setComplete()
            }
        }
        textSize.setOnClickListener { v ->
            if (!textSize.isAnimating) {
                //第二个参数为false，则不清除上次设置的格式，重新打字
                textSize.setText(getString(R.string.text),false)
            } else {
                textSize.setComplete()
            }
        }
        textColorSize.setOnClickListener { v ->
            if (!textColorSize.isAnimating) {
                //第二个参数为false，则不清除上次设置的格式，重新打字
                textColorSize.setText(getString(R.string.text),false)
            } else {
                textColorSize.setComplete()
            }
        }
    }
}
