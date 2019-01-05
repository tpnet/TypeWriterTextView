package vip.skyhand.typewritertextview.typerwriter


interface WriteStatusListener {
    fun onWriteStart(view: TypeWriterTextView){}
    fun onWriteFinish(view: TypeWriterTextView){}
}