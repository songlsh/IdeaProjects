package com.sls.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "helloFallback")
    public String serviceHello(){
        return  restTemplate.getForEntity("http://rookie/hello",String.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "helloFallback1")
    public Future<String> getHello(){
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                return restTemplate.getForEntity("http://rookie/hello",String.class).getBody();
            }
        };
    }

    @HystrixCommand(fallbackMethod = "helloFallback1")
    public Observable<String> getHelloObservable(){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()){
                        String result = restTemplate.getForEntity("http://rookie/hello",String.class).getBody();
                        subscriber.onNext(result);
                        subscriber.onCompleted();
                    }
                } catch (RestClientException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    public String helloFallback(){
        return "error";
    }
}
