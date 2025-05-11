package com.cachingproxy.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import lombok.Builder;

@Builder
public final class CachedResponseEntity extends ResponseEntity<String> {

    public static final String X_CACHE = "X-Cache";
    private ResponseEntity<String> delegate;

    public CachedResponseEntity(final ResponseEntity<String> delegate) {
        super(delegate.getBody(), delegate.getStatusCode());
        this.delegate = delegate;

    }

    @Override
    @NonNull
	public HttpHeaders getHeaders() {
		final Map<String, List<String>> originalHeaders = this.delegate.getHeaders().entrySet().stream()
				.filter(e -> !e.getKey().equals(X_CACHE))
				.collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));


        final HttpHeaders cacheInfoHeaders = new HttpHeaders();
        cacheInfoHeaders.putAll(originalHeaders);
        cacheInfoHeaders.add(X_CACHE, CacheHeader.HIT.toString());
        return cacheInfoHeaders;
    }


}
