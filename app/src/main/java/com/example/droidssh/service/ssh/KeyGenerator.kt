package com.example.droidssh.service.ssh

import net.schmizz.sshj.common.KeyType
import java.security.KeyPairGenerator
import java.security.SecureRandom

class KeyGenerator {

    fun generateRsaKey(bits: Int = 4096): java.security.KeyPair {
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(bits, SecureRandom())
        return generator.generateKeyPair()
    }

    fun generateEd25519Key(): java.security.KeyPair {
        val generator = KeyPairGenerator.getInstance("Ed25519")
        return generator.generateKeyPair()
    }
}
