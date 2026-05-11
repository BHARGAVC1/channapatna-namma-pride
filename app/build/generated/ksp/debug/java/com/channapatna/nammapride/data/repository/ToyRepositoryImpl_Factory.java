package com.channapatna.nammapride.data.repository;

import com.channapatna.nammapride.data.local.dao.ArtisanDao;
import com.channapatna.nammapride.data.local.dao.ToyDao;
import com.channapatna.nammapride.util.NetworkHelper;
import com.google.firebase.auth.FirebaseAuth;
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
public final class ToyRepositoryImpl_Factory implements Factory<ToyRepositoryImpl> {
  private final Provider<ToyDao> toyDaoProvider;

  private final Provider<ArtisanDao> artisanDaoProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseAuth> authProvider;

  private final Provider<NetworkHelper> networkHelperProvider;

  private ToyRepositoryImpl_Factory(Provider<ToyDao> toyDaoProvider,
      Provider<ArtisanDao> artisanDaoProvider, Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider, Provider<NetworkHelper> networkHelperProvider) {
    this.toyDaoProvider = toyDaoProvider;
    this.artisanDaoProvider = artisanDaoProvider;
    this.firestoreProvider = firestoreProvider;
    this.authProvider = authProvider;
    this.networkHelperProvider = networkHelperProvider;
  }

  @Override
  public ToyRepositoryImpl get() {
    return newInstance(toyDaoProvider.get(), artisanDaoProvider.get(), firestoreProvider.get(), authProvider.get(), networkHelperProvider.get());
  }

  public static ToyRepositoryImpl_Factory create(Provider<ToyDao> toyDaoProvider,
      Provider<ArtisanDao> artisanDaoProvider, Provider<FirebaseFirestore> firestoreProvider,
      Provider<FirebaseAuth> authProvider, Provider<NetworkHelper> networkHelperProvider) {
    return new ToyRepositoryImpl_Factory(toyDaoProvider, artisanDaoProvider, firestoreProvider, authProvider, networkHelperProvider);
  }

  public static ToyRepositoryImpl newInstance(ToyDao toyDao, ArtisanDao artisanDao,
      FirebaseFirestore firestore, FirebaseAuth auth, NetworkHelper networkHelper) {
    return new ToyRepositoryImpl(toyDao, artisanDao, firestore, auth, networkHelper);
  }
}
