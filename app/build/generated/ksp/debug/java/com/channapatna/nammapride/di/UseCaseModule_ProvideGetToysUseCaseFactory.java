package com.channapatna.nammapride.di;

import com.channapatna.nammapride.data.repository.IToyRepository;
import com.channapatna.nammapride.domain.usecase.GetToysUseCase;
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
public final class UseCaseModule_ProvideGetToysUseCaseFactory implements Factory<GetToysUseCase> {
  private final Provider<IToyRepository> repoProvider;

  private UseCaseModule_ProvideGetToysUseCaseFactory(Provider<IToyRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public GetToysUseCase get() {
    return provideGetToysUseCase(repoProvider.get());
  }

  public static UseCaseModule_ProvideGetToysUseCaseFactory create(
      Provider<IToyRepository> repoProvider) {
    return new UseCaseModule_ProvideGetToysUseCaseFactory(repoProvider);
  }

  public static GetToysUseCase provideGetToysUseCase(IToyRepository repo) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetToysUseCase(repo));
  }
}
