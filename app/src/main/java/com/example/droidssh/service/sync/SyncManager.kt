package com.example.droidssh.service.sync

import com.example.droidssh.domain.model.ServerConnection
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import android.util.Base64

class SyncManager {

    // 使用用户主密码加密配置
    fun encryptConfig(connections: List<ServerConnection>, masterKey: String): String {
        val key = SecretKeySpec(masterKey.padEnd(32).substring(0, 32).toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val plainText = connections.toString() // 实际应使用 JSON 序列化
        return Base64.encodeToString(cipher.doFinal(plainText.toByteArray()), Base64.DEFAULT)
    }

    suspend fun syncToCloud(encryptedData: String) {
        // TODO: 调用 WebDAV 或自建 API 进行云端保存
    }
}
