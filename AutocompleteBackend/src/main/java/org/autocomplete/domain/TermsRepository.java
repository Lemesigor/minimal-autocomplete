package org.autocomplete.domain;

import java.util.List;

public interface TermsRepository {
    public List<Term> findByPrefix(String prefix);

    public void save(Term term);

    public void bulkSave(List<Term> terms);

    public void delete(Term term);
}
