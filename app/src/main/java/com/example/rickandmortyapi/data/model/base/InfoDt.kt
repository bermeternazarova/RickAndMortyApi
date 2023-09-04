package com.example.rickandmortyapi.data.model.base

import com.example.rickandmortyapi.data.mapper.DataMapper
import com.example.rickandmortyapi.domain.model.base.Info

data class InfoDt(
    val count: Int,
    val page: Int,
    val next: String,
    val prev: Any
):DataMapper<Info>{
    override fun toMapping(): Info {
        return Info(
            count, page, next, prev
        )
    }
}
