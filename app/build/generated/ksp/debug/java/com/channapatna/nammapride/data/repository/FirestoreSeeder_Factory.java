package com.channapatna.nammapride.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class FirestoreSeeder_Factory implements Factory<FirestoreSeeder> {
  private final Provider<FirebaseFirestore> dbProvider;

  private FirestoreSeeder_Factory(Provider<FirebaseFirestore> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public FirestoreSeeder get() {
    return newInstance(dbProvider.get());
  }

  public static FirestoreSeeder_Factory create(Provider<FirebaseFirestore> dbProvider) {
    return new FirestoreSeeder_Factory(dbProvider);
  }

  public static FirestoreSeeder newInstance(FirebaseFirestore db) {
    return new FirestoreSeeder(db);
  }
}
