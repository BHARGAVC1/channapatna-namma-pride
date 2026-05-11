package com.channapatna.nammapride.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface IAuthRepository {
    suspend fun ensureSignedIn(): FirebaseUser
    fun currentUser(): FirebaseUser?
    suspend fun linkGoogleAccount(idToken: String): FirebaseUser
}

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : IAuthRepository {

    override suspend fun ensureSignedIn(): FirebaseUser =
        auth.currentUser
            ?: auth.signInAnonymously().await().user!!

    override fun currentUser(): FirebaseUser? = auth.currentUser

    override suspend fun linkGoogleAccount(idToken: String): FirebaseUser {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        return auth.currentUser!!
            .linkWithCredential(credential).await().user!!
    }
}
