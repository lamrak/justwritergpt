package net.validcat.justwriter.core.decoder

interface StringDecoder {
    fun decodeString(encodedString: String): String
}