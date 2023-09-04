package com.example.rickandmortyapi.presentation.di

//import androidx.paging.PagingSource
//import com.example.rickandmortyapi.data.paging.CharacterPagingSource
//import com.example.rickandmortyapi.data.remote.ApiService
//import com.example.rickandmortyapi.domain.model.character.Character
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule2 {
//    @Provides
//    @Singleton
//    fun provideOkHttp():OkHttpClient{
//        return OkHttpClient.Builder()
//            .readTimeout(15,TimeUnit.SECONDS)
//            .connectTimeout(15,TimeUnit.SECONDS)
//            .build()
//    }
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://rickandmortyapi.com/api/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiService(retrofit: Retrofit):ApiService{
//        return retrofit.create(ApiService::class.java)
//    }
//
//    @Provides
//    @Singleton
//    fun provideCharacterPAgingSource(apiService: ApiService):
//            PagingSource<Int,Character>{
//        return CharacterPagingSource(
//            apiService = apiService
//        )
//    }
//}