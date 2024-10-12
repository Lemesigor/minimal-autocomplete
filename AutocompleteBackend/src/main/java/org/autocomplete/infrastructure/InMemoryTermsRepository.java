package org.autocomplete.infrastructure;

import org.autocomplete.domain.Term;
import org.autocomplete.domain.TermsRepository;

import java.util.List;

public class InMemoryTermsRepository implements TermsRepository {
    @Override
    public List<Term> findByPrefix(String prefix, int limit) {
        return List.of(Term.of(1L, "term"), Term.of(2L, "term2"));
    }

    @Override
    public Term save(Term term) {
        return null;
    }

    @Override
    public void bulkSave(List<Term> terms) {

    }

    @Override
    public void delete(Term term) {

    }
}
