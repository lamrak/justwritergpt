package net.validcat.justwriter.core.network

interface JWNetworkDataSource {
    suspend fun getTopics(ids: List<String>?): List<String>
}
