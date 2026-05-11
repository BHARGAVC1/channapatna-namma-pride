package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.repository.IToyRepository;
import com.channapatna.nammapride.domain.usecase.GetFavoriteToysUseCase;
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
public final class UseCaseModule_ProvideGetFavoriteToysUseCaseFactory implements Factory<GetFavoriteToysUseCase> {
  private final Provider<IToyRepository> repoProvider;

  private UseCaseModule_ProvideGetFavoriteToysUseCaseFactory(
      Provider<IToyRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetFavoriteToysUseCase get() {
    return provideGetFavoriteToysUseCase(repoProvider.get());
  }

  public static UseCaseModule_ProvideGetFavoriteToysUseCaseFactory create(
      Provider<IToyRepository> repoProvider) {
    return new UseCaseModule_ProvideGetFavoriteToysUseCaseFactory(repoProvider);
  }

  public static GetFavoriteToysUseCase provideGetFavoriteToysUseCase(IToyRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetFavoriteToysUseCase(repo));
  }
}
