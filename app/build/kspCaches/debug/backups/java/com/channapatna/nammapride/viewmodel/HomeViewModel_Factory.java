package com.channapatna.nammapride.viewmodel;

import com.channapatna.nammapride.domain.usecase.VerifyToyUseCase;
import com.channapatna.nammapride.util.NetworkHelper;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<VerifyToyUseCase> verifyToyUseCaseProvider;

  private final Provider<NetworkHelper> networkHelperProvider;

  private HomeViewModel_Factory(Provider<VerifyToyUseCase> verifyToyUseCaseProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    this.verifyToyUseCaseProvider = verifyToyUseCaseProvider;
    this.networkHelperProvider = networkHelperProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(verifyToyUseCaseProvider.get(), networkHelperProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<VerifyToyUseCase> verifyToyUseCaseProvider,
      Provider<NetworkHelper> networkHelperProvider) {
    return new HomeViewModel_Factory(verifyToyUseCaseProvider, networkHelperProvider);
  }

  public static HomeViewModel newInstance(VerifyToyUseCase verifyToyUseCase,
      NetworkHelper networkHelper) {
    return new HomeViewModel(verifyToyUseCase, networkHelper);
  }
}
