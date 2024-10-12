package org.autocomplete.infrastructure.repository;

import org.autocomplete.domain.CacheRepository;
import org.autocomplete.domain.Term;
import org.autocomplete.domain.TermsRepository;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class CompositeTermRepositoryTest {

    @Test
    public void returnTermsOnlyFromCacheIfHit() {
        when(cacheRepository.findByPrefix("prefix10")).thenReturn(List.of(Term.of(1L, "prefix10")));

        compositeTermsRepository.findByPrefix("prefix", 10);

        verify(cacheRepository).findByPrefix("prefix10");
        verify(jdbcRepository, never()).findByPrefix("prefix", 10);
    }

    @Test
    public void updateCacheIfNotFound() {
        when(cacheRepository.findByPrefix("prefix10")).thenReturn(List.of());
        when(jdbcRepository.findByPrefix("prefix", 10)).thenReturn(List.of(Term.of(1L, "prefix10")));

        compositeTermsRepository.findByPrefix("prefix", 10);

        verify(cacheRepository).findByPrefix("prefix10");
        verify(jdbcRepository).findByPrefix("prefix", 10);
        verify(cacheRepository).save("prefix10", List.of(Term.of(1L, "prefix10")));
    }

    @Test
    public void invalidateCacheInInsert(){
        compositeTermsRepository.save(Term.of(1L, "prefix10"));
        verify(jdbcRepository).save(Term.of(1L, "prefix10"));
        verify(cacheRepository).invalidate();

    }

    @Test
    public void invalidateCacheInDelete(){
        compositeTermsRepository.delete(1L);
        verify(jdbcRepository).delete(1L);
        verify(cacheRepository).invalidate();
    }


    private TermsRepository jdbcRepository = mock(TermsRepository.class);
    private CacheRepository cacheRepository = mock(CacheRepository.class);

    private final TermsRepository compositeTermsRepository = new CompositeTermsRepository(jdbcRepository, cacheRepository);
}
