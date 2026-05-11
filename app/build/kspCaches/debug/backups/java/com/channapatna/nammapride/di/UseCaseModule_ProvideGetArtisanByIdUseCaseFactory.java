package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.repository.IToyRepository;
import com.channapatna.nammapride.domain.usecase.GetArtisanByIdUseCase;
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
public final class UseCaseModule_ProvideGetArtisanByIdUseCaseFactory implements Factory<GetArtisanByIdUseCase> {
  private final Provider<IToyRepository> repoProvider;

  private UseCaseModule_ProvideGetArtisanByIdUseCaseFactory(Provider<IToyRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetArtisanByIdUseCase get() {
    return provideGetArtisanByIdUseCase(repoProvider.get());
  }

  public static UseCaseModule_ProvideGetArtisanByIdUseCaseFactory create(
      Provider<IToyRepository> repoProvider) {
    return new UseCaseModule_ProvideGetArtisanByIdUseCaseFactory(repoProvider);
  }

  public static GetArtisanByIdUseCase provideGetArtisanByIdUseCase(IToyRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetArtisanByIdUseCase(repo));
  }
}
