package com.thoughtworks.startup.ui.main;

import com.thoughtworks.startup.TestDataFactory;
import com.thoughtworks.startup.data.DataManager;
import com.thoughtworks.startup.data.model.Ribot;
import com.thoughtworks.startup.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock MainMvpView mockMainMvpView;
    @Mock DataManager mockDataManager;
    private MainPresenter mainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mainPresenter = new MainPresenter(mockDataManager);
        mainPresenter.attachView(mockMainMvpView);
    }

    @After
    public void tearDown() {
        mainPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
        List<Ribot> ribots = TestDataFactory.makeListRibots(10);
        doReturn(Observable.just(ribots))
                .when(mockDataManager)
                .getRibots();

        mainPresenter.loadRibots();
        verify(mockMainMvpView).showRibots(ribots);
        verify(mockMainMvpView, never()).showRibotsEmpty();
        verify(mockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsReturnsEmptyList() {
        doReturn(Observable.just(Collections.emptyList()))
                .when(mockDataManager)
                .getRibots();

        mainPresenter.loadRibots();
        verify(mockMainMvpView).showRibotsEmpty();
        verify(mockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
        verify(mockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsFails() {
        doReturn(Observable.error(new RuntimeException()))
                .when(mockDataManager)
                .getRibots();

        mainPresenter.loadRibots();
        verify(mockMainMvpView).showError();
        verify(mockMainMvpView, never()).showRibotsEmpty();
        verify(mockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
    }
}
