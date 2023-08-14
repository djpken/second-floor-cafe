package com.erp.sf.component.impl

import com.erp.sf.component.ObjectByteComponent
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@Component
class ObjectByteComponentImpl : ObjectByteComponent {
    override fun objectToByte(obj: Any): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        objectOutputStream.writeObject(obj)
        byteArrayOutputStream.close()
        objectOutputStream.close()
        return byteArrayOutputStream.toByteArray()

    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> byteToObject(byte: ByteArray): T {
        val byteArrayInputStream = ByteArrayInputStream(byte)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        val obj = objectInputStream.readObject() as Any
        byteArrayInputStream.close()
        objectInputStream.close()
        return obj as T
    }
}