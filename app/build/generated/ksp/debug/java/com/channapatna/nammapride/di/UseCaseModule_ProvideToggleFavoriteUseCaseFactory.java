package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.repository.IToyRepository;
import com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase;
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
public final class UseCaseModule_ProvideToggleFavoriteUseCaseFactory implements Factory<ToggleFavoriteUseCase> {
  private final Provider<IToyRepository> repoProvider;

  private UseCaseModule_ProvideToggleFavoriteUseCaseFactory(Provider<IToyRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public ToggleFavoriteUseCase get() {
    return provideToggleFavoriteUseCase(repoProvider.get());
  }

  public static UseCaseModule_ProvideToggleFavoriteUseCaseFactory create(
      Provider<IToyRepository> repoProvider) {
    return new UseCaseModule_ProvideToggleFavoriteUseCaseFactory(repoProvider);
  }

  public static ToggleFavoriteUseCase provideToggleFavoriteUseCase(IToyRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideToggleFavoriteUseCase(repo));
  }
}
