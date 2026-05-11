package com.channapatna.nammapride.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.channapatna.nammapride.data.local.dao.ArtisanDao
import com.channapatna.nammapride.data.local.dao.ToyDao
import com.channapatna.nammapride.data.local.entity.Artisan
import com.channapatna.nammapride.data.local.entity.Toy

@Database(entities = [Toy::class, Artisan::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toyDao(): ToyDao
    abstract fun artisanDao(): ArtisanDao
}