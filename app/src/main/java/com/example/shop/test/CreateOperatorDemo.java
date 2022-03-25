package com.example.shop.test;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class CreateOperatorDemo {
    public static void main(String[] args) {
        CreateOperatorDemo demo=new CreateOperatorDemo();
        demo.test1();
    }
    private void test1(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                emitter.onNext("aaa");
                emitter.onNext("zyl yy");
                emitter.onComplete();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe...");
            }

            @Override
            public void onNext(@NonNull Object o) {
                System.out.println("onNext...");
            }

            @Override
            public void onError(@NonNull Throwable e) {

                System.out.println("onError...");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete...");
            }
        });
    }
}
