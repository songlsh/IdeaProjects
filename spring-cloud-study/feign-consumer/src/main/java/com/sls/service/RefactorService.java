package com.sls.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("rookie")
public interface RefactorService extends HelloServiceReactor {


}
