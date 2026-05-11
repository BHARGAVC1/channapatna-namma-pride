package com.channapatna.nammapride.viewmodel;

import com.channapatna.nammapride.domain.usecase.GetToysUseCase;
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
public final class CatalogViewModel_Factory implements Factory<CatalogViewModel> {
  private final Provider<GetToysUseCase> getToysUseCaseProvider;

  private final Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider;

  private CatalogViewModel_Factory(Provider<GetToysUseCase> getToysUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    this.getToysUseCaseProvider = getToysUseCaseProvider;
    this.toggleFavoriteUseCaseProvider = toggleFavoriteUseCaseProvider;
  }

  @Override
  public CatalogViewModel get() {
    return newInstance(getToysUseCaseProvider.get(), toggleFavoriteUseCaseProvider.get());
  }

  public static CatalogViewModel_Factory create(Provider<GetToysUseCase> getToysUseCaseProvider,
      Provider<ToggleFavoriteUseCase> toggleFavoriteUseCaseProvider) {
    return new CatalogViewModel_Factory(getToysUseCaseProvider, toggleFavoriteUseCaseProvider);
  }

  public static CatalogViewModel newInstance(GetToysUseCase getToysUseCase,
      ToggleFavoriteUseCase toggleFavoriteUseCase) {
    return new CatalogViewModel(getToysUseCase, toggleFavoriteUseCase);
  }
}
