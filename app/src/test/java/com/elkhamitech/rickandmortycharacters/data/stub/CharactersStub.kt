package com.elkhamitech.rickandmortycharacters.data.stub

import com.elkhamitech.rickandmortycharacters.data.model.*

/**
 * Created by A.Elkhami on 04,November,2021
 */
object CharactersStub {

    val info = Info(
        20,
        "1",
        2,
        0
    )

    val character = Character("",
        listOf(),
        "",
        1,
        "",
        Location("",""),
        "",
        Origin("",""),
        "",
        "",
        "",
        ""
    )

    val charactersList = listOf(character)

    val characters = Characters(info, charactersList)
}