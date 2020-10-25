package ua.training.model.dao.impl;

import org.junit.Test;
import ua.training.exception.db.DBException;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;

import java.sql.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JDBCUserDaoTest {
    private Connection conn = mock(Connection.class);
    private Statement st = mock(Statement.class);
    private PreparedStatement ps = mock(PreparedStatement.class);
    private ResultSet rs = mock(ResultSet.class);
    private UserDao userDao = new JDBCUserDao(conn);

    @Test
    public void create_should_throw_DBException_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        boolean exceptionThrown = false;
        try {
            userDao.create(User.EMPTY);
        } catch (DBException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    @Test
    public void should_create_user() throws SQLException, DBException {
        when(conn.prepareStatement(anyString(), anyInt())).thenReturn(ps);
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        doNothing().when(ps).setString(anyInt(), anyString());
        doNothing().when(ps).setFloat(anyInt(), anyFloat());

        doNothing().when(conn).setAutoCommit(anyBoolean());
        doNothing().when(conn).rollback();
        doNothing().when(conn).commit();

        when(ps.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(anyInt())).thenReturn(1);

        userDao.create(User.EMPTY);

        verify(conn, times(2)).prepareStatement(anyString(), anyInt());
        verify(conn, times(1)).prepareStatement(anyString());
        verify(ps, times(3)).executeUpdate();
        verify(ps, times(2)).getGeneratedKeys();
        verify(rs, times(2)).next();
    }
}
