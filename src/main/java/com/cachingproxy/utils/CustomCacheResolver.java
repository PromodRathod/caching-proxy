package com.cachingproxy.utils;

import java.util.Collection;

import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;

public class CustomCacheResolver implements CacheResolver {

    private final CacheResolver delegate;

    public CustomCacheResolver(final CacheResolver delegate) {
        this.delegate = delegate;
    }


    @Override
    public Collection<CustomHeaderResponseCache> resolveCaches(final CacheOperationInvocationContext<?> context) {
        return delegate.resolveCaches(context)
                       .stream()
                       .map(CustomHeaderResponseCache::new)
                       .toList();
    }
}
