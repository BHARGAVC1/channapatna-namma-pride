package com.channapatna.nammapride.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.channapatna.nammapride.data.local.dao.ArtisanDao
import com.channapatna.nammapride.data.local.dao.ToyDao
import com.channapatna.nammapride.data.local.database.AppDatabase
import com.channapatna.nammapride.data.model.*
import com.channapatna.nammapride.data.repository.IToyRepository
import com.channapatna.nammapride.data.repository.ToyRepositoryImpl
import com.channapatna.nammapride.data.repository.IAuthRepository
import com.channapatna.nammapride.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Version 2 added isFavorite column to toys table
                database.execSQL(
                    "ALTER TABLE toys ADD COLUMN isFavorite INTEGER NOT NULL DEFAULT 0"
                )
            }
        }

        return Room.databaseBuilder(context, AppDatabase::class.java, "channapatna_db")
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    @Provides fun provideToyDao(db: AppDatabase): ToyDao = db.toyDao()
    @Provides fun provideArtisanDao(db: AppDatabase): ArtisanDao = db.artisanDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindToyRepository(impl: ToyRepositoryImpl): IToyRepository
}

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetToysUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.GetToysUseCase(repo)

    @Provides
    fun provideGetToyByIdUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.GetToyByIdUseCase(repo)

    @Provides
    fun provideGetToyByIdFlowUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.GetToyByIdFlowUseCase(repo)

    @Provides
    fun provideGetAllArtisansUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase(repo)

    @Provides
    fun provideGetArtisanByIdUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.GetArtisanByIdUseCase(repo)

    @Provides
    fun provideVerifyToyUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.VerifyToyUseCase(repo)

    @Provides
    fun provideSearchToysUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.SearchToysUseCase(repo)

    @Provides
    fun provideToggleFavoriteUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase(repo)

    @Provides
    fun provideGetFavoriteToysUseCase(repo: IToyRepository) = com.channapatna.nammapride.domain.usecase.GetFavoriteToysUseCase(repo)
}

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth =
        FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        val db = FirebaseFirestore.getInstance()
        db.firestoreSettings = firestoreSettings {
            isPersistenceEnabled = true
            cacheSizeBytes = 100 * 1024 * 1024L  // 100 MB
        }
        return db
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        impl: AuthRepositoryImpl
    ): IAuthRepository
}
