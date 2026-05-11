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
public final class SearchToysUseCase_Factory implements Factory<SearchToysUseCase> {
  private final Provider<IToyRepository> repositoryProvider;

  private SearchToysUseCase_Factory(Provider<IToyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SearchToysUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SearchToysUseCase_Factory create(Provider<IToyRepository> repositoryProvider) {
    return new SearchToysUseCase_Factory(repositoryProvider);
  }

  public static SearchToysUseCase newInstance(IToyRepository repository) {
    return new SearchToysUseCase(repository);
  }
}
