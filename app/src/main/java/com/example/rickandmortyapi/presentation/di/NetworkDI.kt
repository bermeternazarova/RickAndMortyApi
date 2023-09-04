package com.example.rickandmortyapi.presentation.di

import androidx.paging.PagingSource
import com.example.rickandmortyapi.data.paging.CharacterPagingSource
import com.example.rickandmortyapi.data.remote.ApiService
import com.example.rickandmortyapi.data.repository.RepositoryImple
import com.example.rickandmortyapi.domain.model.character.Character
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    factory { provideOkHttp() }
    factory { provideApiService(get()) }
    single { provideRetrofit(get()) }
  //  single { provideRepositoryImple(get())  }

}

fun provideOkHttp(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15,TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
fun provideRepositoryImple(apiService: ApiService):RepositoryImple{
    return RepositoryImple(apiService)
}