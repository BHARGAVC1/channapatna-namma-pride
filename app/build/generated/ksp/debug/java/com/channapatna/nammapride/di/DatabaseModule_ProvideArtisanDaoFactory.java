package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.local.dao.ArtisanDao;
import com.channapatna.nammapride.data.local.database.AppDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DatabaseModule_ProvideArtisanDaoFactory implements Factory<ArtisanDao> {
  private final Provider<AppDatabase> dbProvider;

  private DatabaseModule_ProvideArtisanDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ArtisanDao get() {
    return provideArtisanDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideArtisanDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideArtisanDaoFactory(dbProvider);
  }

  public static ArtisanDao provideArtisanDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideArtisanDao(db));
  }
}
