package com.channapatna.nammapride;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.channapatna.nammapride.data.local.dao.ArtisanDao;
import com.channapatna.nammapride.data.local.dao.ToyDao;
import com.channapatna.nammapride.data.local.database.AppDatabase;
import com.channapatna.nammapride.data.repository.AuthRepositoryImpl;
import com.channapatna.nammapride.data.repository.FirestoreSeeder;
import com.channapatna.nammapride.data.repository.ToyRepositoryImpl;
import com.channapatna.nammapride.di.DatabaseModule_ProvideArtisanDaoFactory;
import com.channapatna.nammapride.di.DatabaseModule_ProvideDatabaseFactory;
import com.channapatna.nammapride.di.DatabaseModule_ProvideToyDaoFactory;
import com.channapatna.nammapride.di.FirebaseModule_ProvideFirebaseAuthFactory;
import com.channapatna.nammapride.di.FirebaseModule_ProvideFirestoreFactory;
import com.channapatna.nammapride.di.UseCaseModule_ProvideGetAllArtisansUseCaseFactory;
import com.channapatna.nammapride.di.UseCaseModule_ProvideGetArtisanByIdUseCaseFactory;
import com.channapatna.nammapride.di.UseCaseModule_ProvideGetFavoriteToysUseCaseFactory;
import com.channapatna.nammapride.di.UseCaseModule_ProvideGetToyByIdUseCaseFactory;
import com.channapatna.nammapride.di.UseCaseModule_ProvideGetToysUseCaseFactory;
import com.channapatna.nammapride.di.UseCaseModule_ProvideToggleFavoriteUseCaseFactory;
import com.channapatna.nammapride.di.UseCaseModule_ProvideVerifyToyUseCaseFactory;
import com.channapatna.nammapride.domain.usecase.GetAllArtisansUseCase;
import com.channapatna.nammapride.domain.usecase.GetArtisanByIdUseCase;
import com.channapatna.nammapride.domain.usecase.GetFavoriteToysUseCase;
import com.channapatna.nammapride.domain.usecase.GetToyByIdUseCase;
import com.channapatna.nammapride.domain.usecase.GetToysUseCase;
import com.channapatna.nammapride.domain.usecase.ToggleFavoriteUseCase;
import com.channapatna.nammapride.domain.usecase.VerifyToyUseCase;
import com.channapatna.nammapride.util.NetworkHelper;
import com.channapatna.nammapride.viewmodel.ArtisanViewModel;
import com.channapatna.nammapride.viewmodel.ArtisanViewModel_HiltModules;
import com.channapatna.nammapride.viewmodel.ArtisanViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.channapatna.nammapride.viewmodel.ArtisanViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.channapatna.nammapride.viewmodel.CatalogViewModel;
import com.channapatna.nammapride.viewmodel.CatalogViewModel_HiltModules;
import com.channapatna.nammapride.viewmodel.CatalogViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.channapatna.nammapride.viewmodel.CatalogViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.channapatna.nammapride.viewmodel.FavoritesViewModel;
import com.channapatna.nammapride.viewmodel.FavoritesViewModel_HiltModules;
import com.channapatna.nammapride.viewmodel.FavoritesViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.channapatna.nammapride.viewmodel.FavoritesViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.channapatna.nammapride.viewmodel.HomeViewModel;
import com.channapatna.nammapride.viewmodel.HomeViewModel_HiltModules;
import com.channapatna.nammapride.viewmodel.HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.channapatna.nammapride.viewmodel.HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.channapatna.nammapride.viewmodel.ToyDetailViewModel;
import com.channapatna.nammapride.viewmodel.ToyDetailViewModel_HiltModules;
import com.channapatna.nammapride.viewmodel.ToyDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey;
import com.channapatna.nammapride.viewmodel.ToyDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerChannapatnaApp_HiltComponents_SingletonC {
  private DaggerChannapatnaApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public ChannapatnaApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements ChannapatnaApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public ChannapatnaApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements ChannapatnaApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public ChannapatnaApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements ChannapatnaApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public ChannapatnaApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements ChannapatnaApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ChannapatnaApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements ChannapatnaApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public ChannapatnaApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements ChannapatnaApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public ChannapatnaApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements ChannapatnaApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public ChannapatnaApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends ChannapatnaApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends ChannapatnaApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    FragmentCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends ChannapatnaApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends ChannapatnaApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    ActivityCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>of(ArtisanViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ArtisanViewModel_HiltModules.KeyModule.provide(), CatalogViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, CatalogViewModel_HiltModules.KeyModule.provide(), FavoritesViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, FavoritesViewModel_HiltModules.KeyModule.provide(), HomeViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, HomeViewModel_HiltModules.KeyModule.provide(), ToyDetailViewModel_HiltModules_KeyModule_Provide_LazyMapKey.lazyClassKeyName, ToyDetailViewModel_HiltModules.KeyModule.provide()));
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }
  }

  private static final class ViewModelCImpl extends ChannapatnaApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    Provider<ArtisanViewModel> artisanViewModelProvider;

    Provider<CatalogViewModel> catalogViewModelProvider;

    Provider<FavoritesViewModel> favoritesViewModelProvider;

    Provider<HomeViewModel> homeViewModelProvider;

    Provider<ToyDetailViewModel> toyDetailViewModelProvider;

    ViewModelCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        SavedStateHandle savedStateHandleParam, ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.artisanViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.catalogViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.favoritesViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.toyDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>of(ArtisanViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (artisanViewModelProvider)), CatalogViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (catalogViewModelProvider)), FavoritesViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (favoritesViewModelProvider)), HomeViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (homeViewModelProvider)), ToyDetailViewModel_HiltModules_BindsModule_Binds_LazyMapKey.lazyClassKeyName, ((Provider) (toyDetailViewModelProvider))));
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.channapatna.nammapride.viewmodel.ArtisanViewModel
          return (T) new ArtisanViewModel(singletonCImpl.getAllArtisansUseCase(), singletonCImpl.getArtisanByIdUseCase());

          case 1: // com.channapatna.nammapride.viewmodel.CatalogViewModel
          return (T) new CatalogViewModel(singletonCImpl.getToysUseCase(), singletonCImpl.toggleFavoriteUseCase());

          case 2: // com.channapatna.nammapride.viewmodel.FavoritesViewModel
          return (T) new FavoritesViewModel(singletonCImpl.getFavoriteToysUseCase(), singletonCImpl.toggleFavoriteUseCase());

          case 3: // com.channapatna.nammapride.viewmodel.HomeViewModel
          return (T) new HomeViewModel(singletonCImpl.verifyToyUseCase(), singletonCImpl.networkHelperProvider.get());

          case 4: // com.channapatna.nammapride.viewmodel.ToyDetailViewModel
          return (T) new ToyDetailViewModel(singletonCImpl.getToyByIdUseCase(), singletonCImpl.getArtisanByIdUseCase(), singletonCImpl.toggleFavoriteUseCase());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends ChannapatnaApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends ChannapatnaApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends ChannapatnaApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    Provider<FirebaseAuth> provideFirebaseAuthProvider;

    Provider<AuthRepositoryImpl> authRepositoryImplProvider;

    Provider<FirebaseFirestore> provideFirestoreProvider;

    Provider<FirestoreSeeder> firestoreSeederProvider;

    Provider<AppDatabase> provideDatabaseProvider;

    Provider<NetworkHelper> networkHelperProvider;

    Provider<ToyRepositoryImpl> toyRepositoryImplProvider;

    SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    ToyDao toyDao() {
      return DatabaseModule_ProvideToyDaoFactory.provideToyDao(provideDatabaseProvider.get());
    }

    ArtisanDao artisanDao() {
      return DatabaseModule_ProvideArtisanDaoFactory.provideArtisanDao(provideDatabaseProvider.get());
    }

    GetAllArtisansUseCase getAllArtisansUseCase() {
      return UseCaseModule_ProvideGetAllArtisansUseCaseFactory.provideGetAllArtisansUseCase(toyRepositoryImplProvider.get());
    }

    GetArtisanByIdUseCase getArtisanByIdUseCase() {
      return UseCaseModule_ProvideGetArtisanByIdUseCaseFactory.provideGetArtisanByIdUseCase(toyRepositoryImplProvider.get());
    }

    GetToysUseCase getToysUseCase() {
      return UseCaseModule_ProvideGetToysUseCaseFactory.provideGetToysUseCase(toyRepositoryImplProvider.get());
    }

    ToggleFavoriteUseCase toggleFavoriteUseCase() {
      return UseCaseModule_ProvideToggleFavoriteUseCaseFactory.provideToggleFavoriteUseCase(toyRepositoryImplProvider.get());
    }

    GetFavoriteToysUseCase getFavoriteToysUseCase() {
      return UseCaseModule_ProvideGetFavoriteToysUseCaseFactory.provideGetFavoriteToysUseCase(toyRepositoryImplProvider.get());
    }

    VerifyToyUseCase verifyToyUseCase() {
      return UseCaseModule_ProvideVerifyToyUseCaseFactory.provideVerifyToyUseCase(toyRepositoryImplProvider.get());
    }

    GetToyByIdUseCase getToyByIdUseCase() {
      return UseCaseModule_ProvideGetToyByIdUseCaseFactory.provideGetToyByIdUseCase(toyRepositoryImplProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideFirebaseAuthProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseAuth>(singletonCImpl, 1));
      this.authRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<AuthRepositoryImpl>(singletonCImpl, 0));
      this.provideFirestoreProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseFirestore>(singletonCImpl, 3));
      this.firestoreSeederProvider = DoubleCheck.provider(new SwitchingProvider<FirestoreSeeder>(singletonCImpl, 2));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 5));
      this.networkHelperProvider = DoubleCheck.provider(new SwitchingProvider<NetworkHelper>(singletonCImpl, 6));
      this.toyRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ToyRepositoryImpl>(singletonCImpl, 4));
    }

    @Override
    public void injectChannapatnaApp(ChannapatnaApp channapatnaApp) {
      injectChannapatnaApp2(channapatnaApp);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private ChannapatnaApp injectChannapatnaApp2(ChannapatnaApp instance) {
      ChannapatnaApp_MembersInjector.injectAuthRepository(instance, authRepositoryImplProvider.get());
      ChannapatnaApp_MembersInjector.injectFirestoreSeeder(instance, firestoreSeederProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @Override
      @SuppressWarnings("unchecked")
      public T get() {
        switch (id) {
          case 0: // com.channapatna.nammapride.data.repository.AuthRepositoryImpl
          return (T) new AuthRepositoryImpl(singletonCImpl.provideFirebaseAuthProvider.get());

          case 1: // com.google.firebase.auth.FirebaseAuth
          return (T) FirebaseModule_ProvideFirebaseAuthFactory.provideFirebaseAuth();

          case 2: // com.channapatna.nammapride.data.repository.FirestoreSeeder
          return (T) new FirestoreSeeder(singletonCImpl.provideFirestoreProvider.get());

          case 3: // com.google.firebase.firestore.FirebaseFirestore
          return (T) FirebaseModule_ProvideFirestoreFactory.provideFirestore();

          case 4: // com.channapatna.nammapride.data.repository.ToyRepositoryImpl
          return (T) new ToyRepositoryImpl(singletonCImpl.toyDao(), singletonCImpl.artisanDao(), singletonCImpl.provideFirestoreProvider.get(), singletonCImpl.provideFirebaseAuthProvider.get(), singletonCImpl.networkHelperProvider.get());

          case 5: // com.channapatna.nammapride.data.local.database.AppDatabase
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 6: // com.channapatna.nammapride.util.NetworkHelper
          return (T) new NetworkHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
