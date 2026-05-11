package com.channapatna.nammapride.viewmodel;

import com.channapatna.nammapride.domain.usecase.GetArtisanByIdUseCase;
import com.channapatna.nammapride.domain.usecase.GetToyByIdUseCase;
import com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase;
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
public final class ToyDetailViewModel_Factory implements Factory<ToyDetailViewModel> {
  private final Provider<GetToyByIdUseCase> getToyByIdUseCaseProvider;

  private final Provider<GetArtisanByIdUseCase> getArtisanByIdUseCaseProvider;

  private final Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider;

  private ToyDetailViewModel_Factory(Provider<GetToyByIdUseCase> getToyByIdUseCaseProvider,
      Provider<GetArtisanByIdUseCase> getArtisanByIdUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    this.getToyByIdUseCaseProvider = getToyByIdUseCaseProvider;
    this.getArtisanByIdUseCaseProvider = getArtisanByIdUseCaseProvider;
    this.toggleFavoriteUseCaseProvider = toggleFavoriteUseCaseProvider;
  }

  @Override
  public ToyDetailViewModel get() {
    return newInstance(getToyByIdUseCaseProvider.get(), getArtisanByIdUseCaseProvider.get(), toggleFavoriteUseCaseProvider.get());
  }

  public static ToyDetailViewModel_Factory create(
      Provider<GetToyByIdUseCase> getToyByIdUseCaseProvider,
      Provider<GetArtisanByIdUseCase> getArtisanByIdUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    return new ToyDetailViewModel_Factory(getToyByIdUseCaseProvider, getArtisanByIdUseCaseProvider, toggleFavoriteUseCaseProvider);
  }

  public static ToyDetailViewModel newInstance(GetToyByIdUseCase getToyByIdUseCase,
      GetArtisanByIdUseCase getArtisanByIdUseCase, ToggleFavoriteUseCase toggleFavoriteUseCase) {
    return new ToyDetailViewModel(getToyByIdUseCase, getArtisanByIdUseCase, toggleFavoriteUseCase);
  }
}
