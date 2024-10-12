package org.autocomplete.infrastructure.repository;

import org.autocomplete.domain.Term;
import org.autocomplete.domain.TermsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TermJdbcRepository implements TermsRepository {
    private static String SELECT_BY_PREFIX = "SELECT * FROM terms WHERE term LIKE ? LIMIT ?";
    private static String INSERT = "INSERT INTO terms (term) VALUES (?)";
    private static String DELETE = "DELETE FROM terms WHERE id = ?";

    private final PreparedStatement selectByPrefixStatement;
    private final PreparedStatement insertStatement;
    private final PreparedStatement deleteStatement;


    public TermJdbcRepository(final Connection connection) throws SQLException {
        this.selectByPrefixStatement = connection.prepareStatement(SELECT_BY_PREFIX);
        this.insertStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        this.deleteStatement = connection.prepareStatement(DELETE);
    }


    @Override
    public List<Term> findByPrefix(String prefix, int limit) {
        try {
            selectByPrefixStatement.setString(1, prefix + "%");
            selectByPrefixStatement.setInt(2, limit);
            ResultSet resultSet = selectByPrefixStatement.executeQuery();
            return mapResultSetToTerms(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Term> mapResultSetToTerms(ResultSet resultSet) throws SQLException {
        List<Term> terms = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String value = resultSet.getString("term");
            terms.add(Term.of(id, value));
        }
        return terms;
    }

    @Override
    public Term save(Term term) {
        try {
            insertStatement.setString(1, term.value());
            insertStatement.executeUpdate();
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            generatedKeys.next();
            return Term.of(generatedKeys.getLong(1), term.value());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            deleteStatement.setLong(1, id);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
