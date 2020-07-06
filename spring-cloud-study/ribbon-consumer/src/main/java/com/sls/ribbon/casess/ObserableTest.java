package com.sls.ribbon.casess;


import rx.Observable;
import rx.Subscriber;

public class ObserableTest {

    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello Rxjava");
            subscriber.onNext(" java Id");
            subscriber.onCompleted();
        }
    });

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onNext(String s) {
            System.out.println("subscriber: "+s);
        }
    };

    public static void main(String[] args) {
//        new ObserableTest().observable.subscribe()
    }
}
