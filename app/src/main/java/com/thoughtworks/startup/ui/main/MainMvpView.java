package com.thoughtworks.startup.ui.main;

import java.util.List;

import com.thoughtworks.startup.data.model.Ribot;
import com.thoughtworks.startup.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
