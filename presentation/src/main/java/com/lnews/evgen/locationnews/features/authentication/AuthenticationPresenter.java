package com.lnews.evgen.locationnews.features.authentication;

import com.arellomobile.mvp.InjectViewState;
import com.google.firebase.firestore.DocumentSnapshot;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.domain.interactors.CategoryInteractor;
import com.lnews.evgen.domain.interactors.NewsInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.features.authorization.AuthFragment;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import com.lnews.evgen.locationnews.features.newslist.NewsListActivity;
import com.lnews.evgen.locationnews.features.passrecovery.PassRecoveryFragment;
import com.lnews.evgen.locationnews.features.registration.RegistrationFragment;
import com.lnews.evgen.locationnews.utils.CategoryParser;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

import static com.lnews.evgen.locationnews.features.launch.LaunchPresenter.CATEGORY_FIREBASE_TAG;

@InjectViewState
@PerActivity(AuthenticationActivity.class)
public class AuthenticationPresenter extends BasePresenter<AuthenticationView> {
    private final AuthorizationInteractor authorizationInteractor;
    private final NewsInteractor newsInteractor;
    private final CategoryInteractor categoryInteractor;
    private final CategoryParser categoryParser;

    @Inject
    AuthenticationPresenter(AuthorizationInteractor authorizationInteractor,
        NewsInteractor newsInteractor, CategoryParser categoryParser,
        CategoryInteractor categoryInteractor) {
        this.authorizationInteractor = authorizationInteractor;
        this.newsInteractor = newsInteractor;
        this.categoryParser = categoryParser;
        this.categoryInteractor = categoryInteractor;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showFragment(AuthFragment.newInstance());
    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearAuthenticationComponent();
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }

    private void syncDBCategories() {
        categoryInteractor.getCategoriesFirebase(new DisposableSingleObserver() {
            @Override
            public void onSuccess(Object o) {
                syncCategories(categoryParser.parseCategoriesFirebase(
                    Objects.requireNonNull(((DocumentSnapshot) o).get(CATEGORY_FIREBASE_TAG))
                        .toString()));
            }

            @Override
            public void onError(Throwable e) {
                initStartCategories();
                getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
            }
        });
    }

    private void syncCategories(List<String> categories) {
        List<Category> bufCategories = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            bufCategories.add(new Category(categories.get(i)));
        }

        categoryInteractor.insertCategories(bufCategories, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
            }

            @Override
            public void onError(Throwable e) {
                getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
            }
        });
    }

    private void initStartCategories() {
        List<Category> bufCategories = new ArrayList<>();
        String[] bufArrayCategories =
            context.getResources().getStringArray(R.array.all_start_categories);

        for (String bufArrayCategory : bufArrayCategories) {
            bufCategories.add(new Category(bufArrayCategory));
        }
        categoryInteractor.insertCategories(bufCategories, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    public void showForgotPassEvent() {
        getViewState().showFragment(PassRecoveryFragment.newInstance());
    }

    public void showRegistrationEvent() {
        getViewState().showFragment(RegistrationFragment.newInstance());
    }

    public void authSuccessEvent() {
        authorizationInteractor.saveToken();
        syncDBCategories();
    }
}
