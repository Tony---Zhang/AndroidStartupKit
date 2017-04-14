package com.thoughtworks.startup.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Func1;
import com.thoughtworks.startup.data.local.DatabaseHelper;
import com.thoughtworks.startup.data.local.PreferencesHelper;
import com.thoughtworks.startup.data.model.Ribot;
import com.thoughtworks.startup.data.remote.RibotsService;
import com.thoughtworks.startup.util.EventPosterHelper;

@Singleton
public class DataManager {

    private final RibotsService ribotsService;
    private final DatabaseHelper databaseHelper;
    private final PreferencesHelper preferencesHelper;
    private final EventPosterHelper eventPosterHelper;

    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        this.ribotsService = ribotsService;
        this.preferencesHelper = preferencesHelper;
        this.databaseHelper = databaseHelper;
        this.eventPosterHelper = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }

    public Observable<Ribot> syncRibots() {
        return ribotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return databaseHelper.setRibots(ribots);
                    }
                });
    }

    public Observable<List<Ribot>> getRibots() {
        return databaseHelper.getRibots().distinct();
    }


    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return new Action0() {
            @Override
            public void call() {
                eventPosterHelper.postEventSafely(event);
            }
        };
    }

}
