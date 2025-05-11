package com.cachingproxy.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.service.annotation.GetExchange;

public interface ProxyService {

	@GetExchange(value = "/products")
	public ResponseEntity<String> getProducts();
}
