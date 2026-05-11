package com.channapatna.nammapride.viewmodel;

import com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase;
import com.channapatna.nammapride.domain.usecase.GetArtisanByIdUseCase;
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
public final class ArtisanViewModel_Factory implements Factory<ArtisanViewModel> {
  private final Provider<GetAllArtisansUseCase> getAllArtisansUseCaseProvider;

  private final Provider<GetArtisanByIdUseCase> getArtisanByIdUseCaseProvider;

  private ArtisanViewModel_Factory(Provider<GetAllArtisansUseCase> getAllArtisansUseCaseProvider,
      Provider<GetArtisanByIdUseCase> getArtisanByIdUseCaseProvider) {
    this.getAllArtisansUseCaseProvider = getAllArtisansUseCaseProvider;
    this.getArtisanByIdUseCaseProvider = getArtisanByIdUseCaseProvider;
  }

  @Override
  public ArtisanViewModel get() {
    return newInstance(getAllArtisansUseCaseProvider.get(), getArtisanByIdUseCaseProvider.get());
  }

  public static ArtisanViewModel_Factory create(
      Provider<GetAllArtisansUseCase> getAllArtisansUseCaseProvider,
      Provider<GetArtisanByIdUseCase> getArtisanByIdUseCaseProvider) {
    return new ArtisanViewModel_Factory(getAllArtisansUseCaseProvider, getArtisanByIdUseCaseProvider);
  }

  public static ArtisanViewModel newInstance(GetAllArtisansUseCase getAllArtisansUseCase,
      GetArtisanByIdUseCase getArtisanByIdUseCase) {
    return new ArtisanViewModel(getAllArtisansUseCase, getArtisanByIdUseCase);
  }
}
