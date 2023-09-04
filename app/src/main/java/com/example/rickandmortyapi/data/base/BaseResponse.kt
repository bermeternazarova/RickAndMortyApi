package com.example.rickandmortyapi.data.base

import com.example.rickandmortyapi.data.model.base.InfoDt

data class BaseResponse<T>(
    val info: InfoDt,
    val results: List<T>
)