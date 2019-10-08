package com.example.dramaapp.base;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter {

  //回收RxJava的垃圾桶, 避免memory leak
  public CompositeDisposable disposable = new CompositeDisposable();

  public void onDestroy() {
    disposable.dispose();
  }
}
