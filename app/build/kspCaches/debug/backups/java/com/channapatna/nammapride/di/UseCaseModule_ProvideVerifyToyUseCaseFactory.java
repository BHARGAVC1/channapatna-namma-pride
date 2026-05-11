package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.repository.IToyRepository;
import com.channapatna.nammapride.domain.usecase.VerifyToyUseCase;
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
public final class UseCaseModule_ProvideVerifyToyUseCaseFactory implements Factory<VerifyToyUseCase> {
  private final Provider<IToyRepository> repoProvider;

  private UseCaseModule_ProvideVerifyToyUseCaseFactory(Provider<IToyRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public VerifyToyUseCase get() {
    return provideVerifyToyUseCase(repoProvider.get());
  }

  public static UseCaseModule_ProvideVerifyToyUseCaseFactory create(
      Provider<IToyRepository> repoProvider) {
    return new UseCaseModule_ProvideVerifyToyUseCaseFactory(repoProvider);
  }

  public static VerifyToyUseCase provideVerifyToyUseCase(IToyRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideVerifyToyUseCase(repo));
  }
}
