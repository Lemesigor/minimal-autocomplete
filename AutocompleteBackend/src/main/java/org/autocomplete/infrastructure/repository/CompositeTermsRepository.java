package org.autocomplete.infrastructure.repository;

import org.autocomplete.domain.CacheRepository;
import org.autocomplete.domain.Term;
import org.autocomplete.domain.TermsRepository;

import java.util.List;

public class CompositeTermsRepository implements TermsRepository {
    private final TermsRepository jdbcTermsRepository;
    private final CacheRepository inMemoryTermsRepository;

    public CompositeTermsRepository(TermsRepository jdbcTermsRepository, CacheRepository inMemoryTermsRepository) {
        this.jdbcTermsRepository = jdbcTermsRepository;
        this.inMemoryTermsRepository = inMemoryTermsRepository;
    }

    @Override
    public List<Term> findByPrefix(String prefix, int limit) {
        final List<Term> terms = inMemoryTermsRepository.findByPrefix(prefix + limit);
        if (terms.isEmpty()) {
            final var jdbcTerms = jdbcTermsRepository.findByPrefix(prefix, limit);
            inMemoryTermsRepository.save(prefix + limit, jdbcTerms);
            return jdbcTerms;
        }
        return terms;
    }

    @Override
    public Term save(Term term) {
        inMemoryTermsRepository.invalidate();
        return jdbcTermsRepository.save(term);
    }

    @Override
    public void delete(long id) {
        inMemoryTermsRepository.invalidate();
        jdbcTermsRepository.delete(id);
    }
}
