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
public final class GetAllArtisansUseCase_Factory implements Factory<GetAllArtisansUseCase> {
  private final Provider<IToyRepository> repositoryProvider;

  private GetAllArtisansUseCase_Factory(Provider<IToyRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAllArtisansUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAllArtisansUseCase_Factory create(Provider<IToyRepository> repositoryProvider) {
    return new GetAllArtisansUseCase_Factory(repositoryProvider);
  }

  public static GetAllArtisansUseCase newInstance(IToyRepository repository) {
    return new GetAllArtisansUseCase(repository);
  }
}
