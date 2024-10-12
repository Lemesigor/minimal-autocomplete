package org.autocomplete.infrastructure.repository;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.autocomplete.domain.Term;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TermInMemoryCacheRepositoryTest {

    @Test
    public void shouldRetrieveAddedTermsAfterUpsert() {
        final var terms = List.of(
                Term.of(1L, "terma"),
                Term.of(2L, "termb"),
                Term.of(3L, "termc")
        );
        repository.save("aprefix", terms);
        final var result = repository.findByPrefix("aprefix");
        assertThat(result, is(terms));
    }

    @Test
    public void shouldReturnEmptyListIfKeyIsNotPresent() {
        final var result = repository.findByPrefix("aprefix");
        assertThat(result.size(), is(0));
    }

    @Test
    public void invalidatesCache() {
        final var terms = List.of(
                Term.of(1L, "terma"),
                Term.of(2L, "termb"),
                Term.of(3L, "termc")
        );
        repository.save("aprefix", terms);
        repository.invalidate();
        final var result = repository.findByPrefix("aprefix");
        assertThat(result.size(), is(0));
    }




    @Before
    public void setUp() {
        cache.invalidateAll();
    }


    private final Cache<String, List<Term>> cache = Caffeine.newBuilder()
            .build();

    private final TermInMemoryCacheRepository repository = new TermInMemoryCacheRepository(cache);
}
