package ua.training.model.dao.impl;

import org.junit.Test;
import ua.training.exception.db.DBException;
import ua.training.model.dao.SubjectDao;
import ua.training.model.entity.Subject;

import java.sql.*;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JDBCSubjectDaoTest {
    private Connection conn = mock(Connection.class);
    private Statement st = mock(Statement.class);
    private PreparedStatement ps = mock(PreparedStatement.class);
    private ResultSet rs = mock(ResultSet.class);
    private SubjectDao subjectDao = new JDBCSubjectDao(conn);

    @Test
    public void get_subjects_names_should_return_empty_optional_when_SQLException_thrown() throws SQLException {
        when(conn.createStatement()).thenThrow(new SQLException());
        assertEquals(Optional.empty(), subjectDao.getSubjectsNames(""));
    }

    @Test
    public void get_subjects_names_should_return_optional_with_not_empty_list() throws SQLException {
        when(conn.createStatement()).thenReturn(st);
        when(st.executeQuery(anyString())).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");

        assertNotEquals(Optional.empty(), subjectDao.getSubjectsNames(""));
    }

    @Test
    public void find_subjects_user_not_have_should_return_empty_optional_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(Optional.empty(), subjectDao.findSubjectsUserNotHave(1, ""));
    }

    @Test
    public void find_subjects_user_not_have_should_return_optional_with_not_empty_list() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");

        assertNotEquals(Optional.empty(), subjectDao.findSubjectsUserNotHave(1, ""));
    }

    @Test
    public void set_subject_for_user_should_throw_DBException_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());

        boolean exceptionThrown = false;
        try {
            subjectDao.setSubjectForUser(new Subject("", 1));
        } catch (DBException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void set_subject_for_user_should_set_subject() throws SQLException, DBException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeQuery()).thenReturn(rs);
        subjectDao.setSubjectForUser(new Subject("", 1));
        verify(ps, times(1)).executeUpdate();
    }

    @Test
    public void find_subjects_for_user_should_return_empty_optional_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(Optional.empty(), subjectDao.findSubjectsForUser(1, ""));
    }

    @Test
    public void find_subjects_for_user_should_return_optional_with_not_empty_list() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");

        assertNotEquals(Optional.empty(), subjectDao.findSubjectsForUser(1, ""));
    }


}
