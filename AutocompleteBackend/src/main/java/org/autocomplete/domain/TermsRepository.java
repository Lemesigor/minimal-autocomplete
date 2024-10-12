package org.autocomplete.domain;

import java.sql.SQLException;
import java.util.List;

public interface TermsRepository {
    List<Term> findByPrefix(String prefix, int limit);

    Term save(Term term);

    void bulkSave(List<Term> terms);

    void delete(long id);
}
