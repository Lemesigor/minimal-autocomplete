package org.autocomplete.domain;

import java.sql.SQLException;
import java.util.List;

public interface TermsRepository {
    public List<Term> findByPrefix(String prefix, int limit);

    public Term save(Term term);

    public void bulkSave(List<Term> terms);

    public void delete(Term term);
}
