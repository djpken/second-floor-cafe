package com.erp.sf.component

interface ObjectByteComponent {
    fun objectToByte(obj: Any): ByteArray
    fun <T> byteToObject(byte: ByteArray): T
}