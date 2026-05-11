package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.repository.IToyRepository;
import com.channapatna.nammapride.domain.usecase.GetToyByIdUseCase;
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
public final class UseCaseModule_ProvideGetToyByIdUseCaseFactory implements Factory<GetToyByIdUseCase> {
  private final Provider<IToyRepository> repoProvider;

  private UseCaseModule_ProvideGetToyByIdUseCaseFactory(Provider<IToyRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetToyByIdUseCase get() {
    return provideGetToyByIdUseCase(repoProvider.get());
  }

  public static UseCaseModule_ProvideGetToyByIdUseCaseFactory create(
      Provider<IToyRepository> repoProvider) {
    return new UseCaseModule_ProvideGetToyByIdUseCaseFactory(repoProvider);
  }

  public static GetToyByIdUseCase provideGetToyByIdUseCase(IToyRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetToyByIdUseCase(repo));
  }
}
