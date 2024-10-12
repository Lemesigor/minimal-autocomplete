package org.autocomplete.domain;

import java.util.List;

public interface CacheRepository {

    List<Term> findByPrefix(String prefix);

    void invalidate();

    void save(String prefix, List<Term> terms);
}
