package org.autocomplete.application;

import org.autocomplete.domain.Query;
import org.autocomplete.domain.Term;
import org.autocomplete.domain.TermsRepository;

import java.util.List;

public class TermService {
    private final TermsRepository termsRepository;

    public TermService(TermsRepository termsRepository) {
        this.termsRepository = termsRepository;
    }

    public Term save(String termValue) {
        final var term = Term.of(termValue);
        return termsRepository.save(term);
    }

    public void delete(long id) {
        termsRepository.delete(id);
    }

    public List<Term> findByPrefix(Query query) {
        return termsRepository.findByPrefix(query.value(), query.limit());
    }
}
