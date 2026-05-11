package com.channapatna.nammapride.domain.usecase;

import com.channapatna.nammapride.data.repository.IToyRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class VerifyToyUseCase_Factory implements Factory<VerifyToyUseCase> {
  private final Provider<IToyRepository> repositoryProvider;

  private VerifyToyUseCase_Factory(Provider<IToyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public VerifyToyUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static VerifyToyUseCase_Factory create(Provider<IToyRepository> repositoryProvider) {
    return new VerifyToyUseCase_Factory(repositoryProvider);
  }

  public static VerifyToyUseCase newInstance(IToyRepository repository) {
    return new VerifyToyUseCase(repository);
  }
}
