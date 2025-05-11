package com.cachingproxy.utils;

import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

public class CustomHeaderResponseCache implements ResponseEntityCache {

    private final Cache delegate;

    public CustomHeaderResponseCache(final Cache delegate) {
        this.delegate = delegate;
    }

    @Override
    @NonNull
    public String getName() {
        return delegate.getName();
    }

    @Override
    @NonNull
    public Object getNativeCache() {
        return delegate.getNativeCache();
    }

    @Override
    public ValueWrapper get(@NonNull final Object key) {
        return delegate.get(key);
    }

    @Override
    public <T> T get(@NonNull final Object key, final Class<T> type) {
        return delegate.get(key, type);
    }

    @Override
    public <T> T get(@NonNull final Object key, @NonNull final Callable<T> valueLoader) {
        return delegate.get(key, valueLoader);
    }

    @Override
    public void evict(@NonNull final Object key) {
        delegate.evict(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public void put(@NonNull final Object key, final ResponseEntity<String> value) {
        delegate.put(key, new CachedResponseEntity(value));
    }
}
