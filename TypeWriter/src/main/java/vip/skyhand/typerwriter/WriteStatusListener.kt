package vip.skyhand.typerwriter


interface WriteStatusListener {
    fun onWriteStart(view: TypeWriterTextView){}
    fun onWriteFinish(view: TypeWriterTextView){}
}