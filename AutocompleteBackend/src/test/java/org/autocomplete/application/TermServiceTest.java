package org.autocomplete.application;

import org.autocomplete.domain.Query;
import org.autocomplete.domain.Term;
import org.autocomplete.domain.TermsRepository;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.is;

public class TermServiceTest {
    @Test
    public void getByPrefix() {
        when(termsRepository.findByPrefix("te", 10)).thenReturn(List.of(Term.of(1L, "term")));
        final var result = termService.findByPrefix(Query.of("te", 10));

        verify(termsRepository).findByPrefix("te", 10);
        assertThat(result, is(List.of(Term.of(1L, "term"))));
    }

    @Test
    public void saveTerm() {
        when(termsRepository.save(Term.of("term"))).thenReturn(Term.of(1L, "term"));
        final var result = termService.save("term");

        verify(termsRepository).save(Term.of("term"));
        assertThat(result, is(Term.of(1L, "term")));

    }

    @Test
    public void deleteTerm() {
        termService.delete(1L);
        verify(termsRepository).delete(1L);
    }

    private final TermsRepository termsRepository = mock(TermsRepository.class);
    private final TermService termService = new TermService(termsRepository);
}
