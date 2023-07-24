package net.validcat.justwriter.core.networking

interface JWNetworkDataSource {
    suspend fun getTopics(ids: List<String>?): List<String>
}
