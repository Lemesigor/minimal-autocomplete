package org.autocomplete.infrastructure.repository;

import com.github.benmanes.caffeine.cache.Cache;
import org.autocomplete.domain.CacheRepository;
import org.autocomplete.domain.Term;

import java.util.List;

public class TermInMemoryCacheRepository implements CacheRepository {
    private final Cache<String, List<Term>> cache;

    public TermInMemoryCacheRepository(Cache<String, List<Term>> cache) {
        this.cache = cache;
    }

    @Override
    public List<Term> findByPrefix(String prefix) {
        return cache.get(prefix, k -> List.of());
    }

    @Override
    public void invalidate() {
        cache.invalidateAll();
    }

    @Override
    public void save(String prefix, List<Term> terms) {
        cache.put(prefix, terms);
    }
}
