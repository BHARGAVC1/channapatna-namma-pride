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
public final class GetToyByIdUseCase_Factory implements Factory<GetToyByIdUseCase> {
  private final Provider<IToyRepository> repositoryProvider;

  private GetToyByIdUseCase_Factory(Provider<IToyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetToyByIdUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetToyByIdUseCase_Factory create(Provider<IToyRepository> repositoryProvider) {
    return new GetToyByIdUseCase_Factory(repositoryProvider);
  }

  public static GetToyByIdUseCase newInstance(IToyRepository repository) {
    return new GetToyByIdUseCase(repository);
  }
}
