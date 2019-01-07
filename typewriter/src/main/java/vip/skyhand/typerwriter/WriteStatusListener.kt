package vip.skyhand.typerwriter

/**
 * 打字状态接口
 */
interface WriteStatusListener {
    fun onWriteStart(view: TypeWriterTextView){}
    fun onWriteFinish(view: TypeWriterTextView){}
}