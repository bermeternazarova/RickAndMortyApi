package com.example.rickandmortyapi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remotekeys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage:Int?,
    val nextPage:Int?
)
