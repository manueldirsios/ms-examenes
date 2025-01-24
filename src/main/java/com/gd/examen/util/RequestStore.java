package com.gd.examen.util;

import java.util.concurrent.ConcurrentHashMap;

public class RequestStore {
	private final ConcurrentHashMap<String, Long> storeRequest = new ConcurrentHashMap<>();
    private final long ttlMillis;

    public RequestStore(long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }

    public boolean isDuplicate(String key) {
        long now = System.currentTimeMillis();
        storeRequest.entrySet().removeIf(entry -> now - entry.getValue() > ttlMillis);

        return storeRequest.putIfAbsent(key, now) != null;
    }
}
