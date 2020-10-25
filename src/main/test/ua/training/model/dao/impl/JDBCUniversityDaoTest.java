package ua.training.model.dao.impl;

import org.junit.Test;
import ua.training.model.dao.UniversityDao;
import ua.training.model.entity.University;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JDBCUniversityDaoTest {
    private Connection conn = mock(Connection.class);
    private Statement st = mock(Statement.class);
    private ResultSet rs = mock(ResultSet.class);
    private UniversityDao universityDao = new JDBCUniversityDao(conn);

    @Test
    public void find_all_should_return_empty_optional_when_SQLException_thrown() throws SQLException {
        when(conn.createStatement()).thenThrow(new SQLException());
        assertEquals(Optional.empty(), universityDao.findAll(""));
    }

    @Test
    public void find_all_should_return_not_empty_optional() throws SQLException {
        when(conn.createStatement()).thenReturn(st);
        when(st.executeQuery(anyString())).thenReturn(rs);

        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(1)).thenReturn(12);
        when(rs.getString(2)).thenReturn("university name");

        List<University> list = universityDao.findAll("").get();

        assertEquals(1, list.size(), 0);

    }
}
