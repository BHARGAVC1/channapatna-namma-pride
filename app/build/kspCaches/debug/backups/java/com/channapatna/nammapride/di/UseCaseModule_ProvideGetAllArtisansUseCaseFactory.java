package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.repository.IToyRepository;
import com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase;
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
public final class UseCaseModule_ProvideGetAllArtisansUseCaseFactory implements Factory<GetAllArtisansUseCase> {
  private final Provider<IToyRepository> repoProvider;

  private UseCaseModule_ProvideGetAllArtisansUseCaseFactory(Provider<IToyRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetAllArtisansUseCase get() {
    return provideGetAllArtisansUseCase(repoProvider.get());
  }

  public static UseCaseModule_ProvideGetAllArtisansUseCaseFactory create(
      Provider<IToyRepository> repoProvider) {
    return new UseCaseModule_ProvideGetAllArtisansUseCaseFactory(repoProvider);
  }

  public static GetAllArtisansUseCase provideGetAllArtisansUseCase(IToyRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetAllArtisansUseCase(repo));
  }
}
