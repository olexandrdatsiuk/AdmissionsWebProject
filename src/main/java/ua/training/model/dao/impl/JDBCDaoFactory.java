package ua.training.model.dao.impl;

import ua.training.exception.db.DBException;
import ua.training.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public FacultyDao createFacultyDao() throws DBException {
        return new JDBCFacultyDao(getConnection());
    }

    @Override
    public UserDao createUserDao() throws DBException {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public UniversityDao createUniversityDao() throws DBException {
        return new JDBCUniversityDao(getConnection());
    }

    @Override
    public SubjectDao createSubjectDao() throws DBException {
        return new JDBCSubjectDao(getConnection());
    }

    @Override
    public RequestDao createRequestDao() throws DBException {
        return new JDBCRequestDao(getConnection());
    }

    private Connection getConnection() throws DBException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
