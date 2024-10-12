package org.autocomplete.infrastructure.controller;

import org.autocomplete.domain.Term;

import java.util.List;

public record TermListResponse (int total, List<Term> terms) {
    public TermListResponse(List<Term> terms) {
        this(terms.size(), terms);
    }
}
