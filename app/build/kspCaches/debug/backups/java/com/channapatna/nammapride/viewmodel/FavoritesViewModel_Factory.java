package com.channapatna.nammapride.viewmodel;

import com.channapatna.nammapride.domain.usecase.GetFavoriteToysUseCase;
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
public final class FavoritesViewModel_Factory implements Factory<FavoritesViewModel> {
  private final Provider<GetFavoriteToysUseCase> getFavoriteToysUseCaseProvider;

  private final Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider;

  private FavoritesViewModel_Factory(
      Provider<GetFavoriteToysUseCase> getFavoriteToysUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    this.getFavoriteToysUseCaseProvider = getFavoriteToysUseCaseProvider;
    this.toggleFavoriteUseCaseProvider = toggleFavoriteUseCaseProvider;
  }

  @Override
  public FavoritesViewModel get() {
    return newInstance(getFavoriteToysUseCaseProvider.get(), toggleFavoriteUseCaseProvider.get());
  }

  public static FavoritesViewModel_Factory create(
      Provider<GetFavoriteToysUseCase> getFavoriteToysUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    return new FavoritesViewModel_Factory(getFavoriteToysUseCaseProvider, toggleFavoriteUseCaseProvider);
  }

  public static FavoritesViewModel newInstance(GetFavoriteToysUseCase getFavoriteToysUseCase,
      ToggleFavoriteUseCase toggleFavoriteUseCase) {
    return new FavoritesViewModel(getFavoriteToysUseCase, toggleFavoriteUseCase);
  }
}
