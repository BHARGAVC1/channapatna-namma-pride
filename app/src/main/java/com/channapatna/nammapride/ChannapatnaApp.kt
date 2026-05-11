package com.channapatna.nammapride

import android.app.Application
import com.channapatna.nammapride.data.repository.FirestoreSeeder
import com.channapatna.nammapride.data.repository.IAuthRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class ChannapatnaApp : Application() {

    @Inject lateinit var authRepository: IAuthRepository
    @Inject lateinit var firestoreSeeder: FirestoreSeeder

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        if (BuildConfig.DEBUG) {
            firebaseAppCheck.installAppCheckProviderFactory(
                DebugAppCheckProviderFactory.getInstance()
            )
        } else {
            firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance()
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                authRepository.ensureSignedIn()   // anonymous UID
                firestoreSeeder.seedIfEmpty()      // one-time data seed
            } catch (e: Exception) {
                // Offline on first launch — retries next run
            }
        }
    }
}
