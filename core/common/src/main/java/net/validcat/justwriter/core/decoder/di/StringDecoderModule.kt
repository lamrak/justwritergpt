package net.validcat.justwriter.core.decoder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.validcat.justwriter.core.decoder.StringDecoder
import net.validcat.justwriter.core.decoder.UriDecoder

@Module
@InstallIn(SingletonComponent::class)
interface StringDecoderModule {

    @Binds
    fun bindStringDecoder(uriDecoder: UriDecoder): StringDecoder
}