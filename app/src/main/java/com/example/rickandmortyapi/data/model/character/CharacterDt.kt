package com.example.rickandmortyapi.data.model.character

import com.example.rickandmortyapi.data.mapper.DataMapper
import com.example.rickandmortyapi.domain.model.character.Character

data class CharacterDt(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginCharacterDt,
    val location: LocationCharactersDt,
    val image:String,
    val episode:List<String>,
    val url:String,
    val created:String
):DataMapper<Character>{
    override fun toMapping(): Character {
        return Character(
            id, name, status, species, type, gender, origin.toMapping(), location.toMapping(), image, episode, url, created
        )
    }
}
