package com.cachingproxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cachingproxy.service.ProxyService;

@RestController
public class CachingProxyController {

	private final ProxyService proxyService;

    @Autowired
    public CachingProxyController(final ProxyService proxyService) {
        this.proxyService = proxyService;
    }

    @GetMapping("/products")
    @Cacheable(cacheNames = "cache1", cacheResolver = "cacheResolver", key = "#root.methodName")
    public ResponseEntity<String> products() {
        return proxyService.getProducts();
    }
}
