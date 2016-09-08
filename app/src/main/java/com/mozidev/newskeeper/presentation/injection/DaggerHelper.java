package com.mozidev.newskeeper.presentation.injection;

import com.mozidev.newskeeper.NewsKeeper;

/**
 * Created by mozi on 06.09.16.
 */
public class DaggerHelper {

    private static MainComponent mainComponent;

    public static MainComponent init(NewsKeeper app) {
        mainComponent = DaggerMainComponent.builder()
                .dataModule(new DataModule(app))
                .build();

        return mainComponent;
    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }

}
