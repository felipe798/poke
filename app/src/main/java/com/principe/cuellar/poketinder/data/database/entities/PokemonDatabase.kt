package com.principe.cuellar.poketinder.data.database.entities

import androidx.room.Database
import androidx.room.RoomDatabase
import com.principe.cuellar.poketinder.data.database.dao.PokemonDao
import com.principe.cuellar.poketinder.data.database.entities.MyPokemonEntity

@Database(entities = [MyPokemonEntity::class], exportSchema = false, version = 1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}
