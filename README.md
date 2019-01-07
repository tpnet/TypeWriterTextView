# TypeWriterTextView

Android打字机效果，渐变过度、可以同时设置文字大小和颜色


效果图丢帧，请下载demo查看

![效果图](https://raw.githubusercontent.com/tpnet/TypeWriterTextView/master/img/demo.gif)


# 使用

普通使用
```
    val textNormal = findViewById<TypeWriterTextView>(R.id.mTVTextNormal)
    textNormal.setText("对，控制，我有一种特殊的精神治疗剂，给你注射了之后，你就可以安心入睡了，并且醒来后记忆不会再缺失。 但是一旦12小时之内，你没有再次注射这种药剂，那你的记忆会在短时间内全部消失。注意，即使是你不睡觉，你的记忆还是会消失")
 
```


设置部分文字大小
```
    var sizeStartList = arrayListOf(5,11)
    var sizeEndList = arrayListOf(8,15)
    val textSize = findViewById<TypeWriterTextView>(R.id.mTVTextSize)
    textSize.setTextWithSize("对，控制，我有一种特殊的精神治疗剂，给你注射了之后，你就可以安心入睡了，并且醒来后记忆不会再缺失。 但是一旦12小时之内，你没有再次注射这种药剂，那你的记忆会在短时间内全部消失。注意，即使是你不睡觉，你的记忆还是会消失",60,sizeStartList,sizeEndList)
                              
```

设置部分文字颜色
```
    var colorStartList = arrayListOf(22,30)
    var colorEndList = arrayListOf(26,35)
    val textColor = findViewById<TypeWriterTextView>(R.id.mTVTextColor) 
    textColor.setTextWithColor("对，控制，我有一种特殊的精神治疗剂，给你注射了之后，你就可以安心入睡了，并且醒来后记忆不会再缺失。 但是一旦12小时之内，你没有再次注射这种药剂，那你的记忆会在短时间内全部消失。注意，即使是你不睡觉，你的记忆还是会消失",Color.RED,colorStartList,colorEndList)
                                                            
```

同时设置文字颜色和大小
```
    var colorSizeStartList = arrayListOf(15,30)
    var colorSizeEndList = arrayListOf(20,35)
    val textColorSize = findViewById<TypeWriterTextView>(R.id.mTVTextColorSize)

    textColorSize.setTextWithColorAndSize("对，控制，我有一种特殊的精神治疗剂，给你注射了之后，你就可以安心入睡了，并且醒来后记忆不会再缺失。 但是一旦12小时之内，你没有再次注射这种药剂，那你的记忆会在短时间内全部消失。注意，即使是你不睡觉，你的记忆还是会消失",Color.GREEN,60,colorSizeStartList,colorSizeEndList)

```

其他设置
```
    textNormal.setDurationPerLetter(20)  //设置每个文字的速度
    textNormal.setInterpolator(DecelerateInterpolator())  //设置渐变插值器
    textNormal.setComplete()   //瞬间打字完成

    //监听打字状态
    textNormal.setWriteStatusListener(object : WriteStatusListener {
        override fun onWriteStart(view: TypeWriterTextView) {
            Log.e("@@","开始打字啦")
        }
        override fun onWriteFinish(view: TypeWriterTextView) {
            Log.e("@@","打字完毕啦")
        }
    })
    
```
