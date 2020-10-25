package ua.training.model.dao.impl;

import org.junit.Test;
import ua.training.exception.db.DBException;
import ua.training.exception.db.FacultyNotExistsException;
import ua.training.model.dao.FacultyDao;
import ua.training.model.entity.Faculty;
import ua.training.model.enumeration.RequestState;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JDBCFacultyDaoTest {
    private Connection conn = mock(Connection.class);
    private Statement st = mock(Statement.class);
    private PreparedStatement ps = mock(PreparedStatement.class);
    private ResultSet rs = mock(ResultSet.class);
    private FacultyDao facultyDao = new JDBCFacultyDao(conn);

    @Test
    public void should_finalise_faculty() throws SQLException, DBException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeUpdate()).thenReturn(1);

        facultyDao.finalizeFaculty(1);

        verify(ps, times(1)).executeUpdate();
    }

    @Test
    public void should_throw_DBException_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        boolean exceptionThrown = false;
        try {
            facultyDao.finalizeFaculty(1);
        } catch (DBException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void find_all_should_return_optional_with_empty_list_when_SQLException_thrown() throws SQLException {
        when(conn.createStatement()).thenThrow(new SQLException());
        assertEquals(0, facultyDao.findAll("").get().size(), 0);
    }

    @Test
    public void find_all_should_return_optional_with_not_empty_list() throws SQLException {
        when(conn.createStatement()).thenReturn(st);
        when(st.executeQuery(anyString())).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");
        assertEquals(1, facultyDao.findAll("").get().size(), 0);
    }

    @Test
    public void find_faculties_for_student_should_return_optional_with_empty_list() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");
        when(ps.executeQuery()).thenReturn(rs, rs);
        when(rs.next()).thenReturn(true, false);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        doNothing().when(conn).setAutoCommit(anyBoolean());
        doNothing().when(conn).rollback();
        doNothing().when(conn).commit();

        assertEquals(0, facultyDao.findFacultiesForStudent(1, "").get().size(), 0);
    }

    @Test
    public void update_faculty_for_university_should_throw_DBException_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        boolean exceptionThrown = false;
        try {
            facultyDao.updateFacultyForUniversity(1, new Faculty.FacultyBuilder().setName("").build());
        } catch (DBException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void update_faculty_for_university_should_throw_FacultyNotExistsException_when_execute_update_returns_zero() throws SQLException, DBException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeUpdate()).thenReturn(0);

        boolean exceptionThrown = false;
        try {
            facultyDao.updateFacultyForUniversity(1, new Faculty.FacultyBuilder().setName("").build());
        } catch (FacultyNotExistsException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void find_faculties_for_university_not_have_should_return_empty_list_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(0, facultyDao.findFacultiesForUniversityNotHave(1, "").size(), 0);
    }

    @Test
    public void find_faculties_for_university_not_have_should_return_not_empty_list() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");

        assertEquals(1, facultyDao.findFacultiesForUniversityNotHave(1, "").size(), 0);
    }

    @Test
    public void delete_from_university_should_throw_DBException_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        boolean exceptionThrown = false;
        try {
            facultyDao.deleteFromUniversity(1, 1, RequestState.CONSIDERED);
        } catch (DBException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void delete_from_university_should_throw_FacultyNotExistsException_when_execute_update_returns_zero() throws SQLException, DBException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeUpdate()).thenReturn(0);
        doNothing().when(conn).setAutoCommit(anyBoolean());
        doNothing().when(conn).rollback();
        doNothing().when(conn).commit();

        boolean exceptionThrown = false;
        try {
            facultyDao.deleteFromUniversity(1, 1, RequestState.CONSIDERED);
        } catch (FacultyNotExistsException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void should_delete_from_university() throws SQLException, DBException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeUpdate()).thenReturn(1);
        doNothing().when(conn).setAutoCommit(anyBoolean());
        doNothing().when(conn).rollback();
        doNothing().when(conn).commit();

        facultyDao.deleteFromUniversity(1, 1, RequestState.CONSIDERED);

        verify(ps, times(2)).executeUpdate();
    }

    @Test
    public void set_faculty_for_university_should_throw_DBException_when_execute_update_zero() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeUpdate()).thenReturn(0);

        boolean exceptionThrown = false;
        try {
            facultyDao.setFacultyForUniversity(1, new Faculty.FacultyBuilder().setName("").build());
        } catch (DBException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void set_faculty_for_university_should_throw_DBException_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());

        boolean exceptionThrown = false;
        try {
            facultyDao.setFacultyForUniversity(1, new Faculty.FacultyBuilder().setName("").build());
        } catch (DBException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void find_faculties_for_university_should_return_empty_list_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(0, facultyDao.findFacultiesForUniversity(1, "").size(), 0);
    }

    @Test
    public void find_faculties_for_university_should_return_not_empty_list() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");

        assertEquals(1, facultyDao.findFacultiesForUniversity(1, "").size(), 0);
        verify(ps, times(1)).executeQuery();
    }

    @Test
    public void find_faculties_name_for_university_should_return_empty_list_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(0, facultyDao.findFacultiesNameForUniversity(1, "").size(), 0);
    }

    @Test
    public void find_faculties_name_for_university_should_return_not_empty_list() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");
        assertEquals(1, facultyDao.findFacultiesNameForUniversity(1, "").size(), 0);
        verify(ps, times(1)).executeQuery();
    }
}
