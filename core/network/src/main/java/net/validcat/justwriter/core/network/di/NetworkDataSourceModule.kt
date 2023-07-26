package net.validcat.justwriter.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.validcat.justwriter.core.network.JWNetworkDataSource
import net.validcat.justwriter.core.network.retrofit.RetrofitJWNetwork

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindsNetworkDataSource(
        retrofitNetwork: RetrofitJWNetwork,
    ): JWNetworkDataSource
}