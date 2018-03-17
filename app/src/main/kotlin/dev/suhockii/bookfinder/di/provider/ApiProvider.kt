package dev.suhockii.bookfinder.di.provider

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import dev.suhockii.bookfinder.data.remote.GoogleDriveApi
import dev.suhockii.bookfinder.di.qualifier.GoogleDrivePath
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Provider

class ApiProvider @Inject constructor(
        private val okHttpClient: OkHttpClient,
        private val gson: Gson,
        @GoogleDrivePath private val serverPath: String
) : Provider<GoogleDriveApi> {

    override fun get() =
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(okHttpClient)
                    .baseUrl(serverPath)
                    .build()
                    .create(GoogleDriveApi::class.java)
}