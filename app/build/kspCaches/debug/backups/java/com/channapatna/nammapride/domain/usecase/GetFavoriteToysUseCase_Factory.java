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
public final class GetFavoriteToysUseCase_Factory implements Factory<GetFavoriteToysUseCase> {
  private final Provider<IToyRepository> repositoryProvider;

  private GetFavoriteToysUseCase_Factory(Provider<IToyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetFavoriteToysUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetFavoriteToysUseCase_Factory create(Provider<IToyRepository> repositoryProvider) {
    return new GetFavoriteToysUseCase_Factory(repositoryProvider);
  }

  public static GetFavoriteToysUseCase newInstance(IToyRepository repository) {
    return new GetFavoriteToysUseCase(repository);
  }
}
