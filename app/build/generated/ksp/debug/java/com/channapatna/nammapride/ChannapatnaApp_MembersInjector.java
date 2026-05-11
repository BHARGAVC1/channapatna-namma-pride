package com.channapatna.nammapride;

import com.channapatna.nammapride.data.repository.FirestoreSeeder;
import com.channapatna.nammapride.data.repository.IAuthRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class ChannapatnaApp_MembersInjector implements MembersInjector<ChannapatnaApp> {
  private final Provider<IAuthRepository> authRepositoryProvider;

  private final Provider<FirestoreSeeder> firestoreSeederProvider;

  private ChannapatnaApp_MembersInjector(Provider<IAuthRepository> authRepositoryProvider,
      Provider<FirestoreSeeder> firestoreSeederProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.firestoreSeederProvider = firestoreSeederProvider;
  }

  @Override
  public void injectMembers(ChannapatnaApp instance) {
    injectAuthRepository(instance, authRepositoryProvider.get());
    injectFirestoreSeeder(instance, firestoreSeederProvider.get());
  }

  public static MembersInjector<ChannapatnaApp> create(
      Provider<IAuthRepository> authRepositoryProvider,
      Provider<FirestoreSeeder> firestoreSeederProvider) {
    return new ChannapatnaApp_MembersInjector(authRepositoryProvider, firestoreSeederProvider);
  }

  @InjectedFieldSignature("com.channapatna.nammapride.ChannapatnaApp.authRepository")
  public static void injectAuthRepository(ChannapatnaApp instance, IAuthRepository authRepository) {
    instance.authRepository = authRepository;
  }

  @InjectedFieldSignature("com.channapatna.nammapride.ChannapatnaApp.firestoreSeeder")
  public static void injectFirestoreSeeder(ChannapatnaApp instance,
      FirestoreSeeder firestoreSeeder) {
    instance.firestoreSeeder = firestoreSeeder;
  }
}
