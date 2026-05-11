package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.local.dao.ToyDao;
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
public final class DatabaseModule_ProvideToyDaoFactory implements Factory<ToyDao> {
  private final Provider<AppDatabase> dbProvider;

  private DatabaseModule_ProvideToyDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ToyDao get() {
    return provideToyDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideToyDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideToyDaoFactory(dbProvider);
  }

  public static ToyDao provideToyDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideToyDao(db));
  }
}
