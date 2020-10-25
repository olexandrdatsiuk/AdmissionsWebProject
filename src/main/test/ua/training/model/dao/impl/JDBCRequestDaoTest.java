package ua.training.model.dao.impl;

import org.junit.Test;
import ua.training.model.dao.RequestDao;
import ua.training.model.entity.Request;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class JDBCRequestDaoTest {
    private Connection conn = mock(Connection.class);
    private PreparedStatement ps = mock(PreparedStatement.class);
    private ResultSet rs = mock(ResultSet.class);
    private RequestDao requestDao = new JDBCRequestDao(conn);

    @Test
    public void should_change_request_status() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeUpdate()).thenReturn(1);

        requestDao.changeRequestStatus(1, 1, 1);

        verify(ps, times(1)).executeUpdate();
    }

    @Test
    public void find_all_should_return_empty_optional_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(Optional.empty(), requestDao.findAll(1, ""));
    }

    @Test
    public void find_all_should_return_not_empty_optional() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");

        List<Request> list = requestDao.findAll(1, "").get();

        assertEquals(1, list.size(), 0);
    }

    @Test
    public void find_for_user_should_return_empty_optional_when_SQLException_thrown() throws SQLException {
        when(conn.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(Optional.empty(), requestDao.findForUser(1, ""));
    }

    @Test
    public void find_for_user_should_return_not_empty_optional() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt(anyInt())).thenReturn(1);
        when(rs.getString(anyInt())).thenReturn("");

        List<Request> list = requestDao.findAll(1, "").get();

        assertEquals(1, list.size(), 0);
    }

    @Test
    public void should_create_request() throws SQLException {
        when(conn.prepareStatement(anyString())).thenReturn(ps);
        doNothing().when(ps).setInt(anyInt(), anyInt());
        when(ps.executeUpdate()).thenReturn(1);

        requestDao.create(1, 1, "");

        verify(ps, times(1)).executeUpdate();
    }


}
