package com.channapatna.nammapride

import android.app.Application
import android.util.Log
import com.channapatna.nammapride.data.repository.AuthException
import com.channapatna.nammapride.data.repository.FirestoreSeeder
import com.channapatna.nammapride.data.repository.IAuthRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
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
            try {
                val debugProviderClass = Class.forName("com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory")
                val getInstanceMethod = debugProviderClass.getMethod("getInstance")
                val factory = getInstanceMethod.invoke(null) as com.google.firebase.appcheck.AppCheckProviderFactory
                firebaseAppCheck.installAppCheckProviderFactory(factory)
            } catch (e: Exception) {
                Log.e("ChannapatnaApp", "Failed to install debug App Check provider", e)
            }
        } else {
            firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance()
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                authRepository.ensureSignedIn()   // anonymous UID
                firestoreSeeder.seedIfEmpty()      // one-time data seed
            } catch (e: AuthException) {
                // Auth failed (offline / App Check rejected).
                // App works offline from Room cache. Retry next launch.
                Log.w("ChannapatnaApp", "Auth failed: ${e.message}")
            } catch (e: Exception) {
                Log.w("ChannapatnaApp", "Startup error: ${e.message}")
            }
        }
    }
}
