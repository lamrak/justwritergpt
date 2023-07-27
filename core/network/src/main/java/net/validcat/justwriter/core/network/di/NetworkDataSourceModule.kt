package net.validcat.justwriter.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.validcat.justwriter.core.network.NetworkDataSource
import net.validcat.justwriter.core.network.retrofit.RetrofitNetwork

@Module
@InstallIn(SingletonComponent::class)
interface NetworkDataSourceModule {

    @Binds
    fun bindsNetworkDataSource(
        retrofitNetwork: RetrofitNetwork,
    ): NetworkDataSource
}