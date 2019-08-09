package com.bz.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static io.reactivex.schedulers.Schedulers.*;

public class SchedulersTransformer implements ObservableTransformer {
    @Override
    public ObservableSource apply(Observable upstream) {
        return upstream.subscribeOn(io())
                .unsubscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
