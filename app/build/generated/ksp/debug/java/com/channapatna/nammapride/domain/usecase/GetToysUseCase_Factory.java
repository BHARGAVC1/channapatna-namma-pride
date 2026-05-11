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
public final class GetToysUseCase_Factory implements Factory<GetToysUseCase> {
  private final Provider<IToyRepository> repositoryProvider;

  private GetToysUseCase_Factory(Provider<IToyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetToysUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetToysUseCase_Factory create(Provider<IToyRepository> repositoryProvider) {
    return new GetToysUseCase_Factory(repositoryProvider);
  }

  public static GetToysUseCase newInstance(IToyRepository repository) {
    return new GetToysUseCase(repository);
  }
}
