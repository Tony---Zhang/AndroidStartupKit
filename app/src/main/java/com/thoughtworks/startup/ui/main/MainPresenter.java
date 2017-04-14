package com.thoughtworks.startup.ui.main;

import com.thoughtworks.startup.data.DataManager;
import com.thoughtworks.startup.data.model.Ribot;
import com.thoughtworks.startup.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;

import static rx.android.schedulers.AndroidSchedulers.mainThread;
import static rx.schedulers.Schedulers.io;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager dataManager;
    private Subscription subscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null) subscription.unsubscribe();
    }

    public void loadRibots() {
        checkViewAttached();
        subscription = dataManager.getRibots()
                .observeOn(mainThread())
                .subscribeOn(io())
                .subscribe(new Subscriber<List<Ribot>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Ribot> ribots) {
                        if (ribots.isEmpty()) {
                            getMvpView().showRibotsEmpty();
                        } else {
                            getMvpView().showRibots(ribots);
                        }
                    }
                });
    }

}
