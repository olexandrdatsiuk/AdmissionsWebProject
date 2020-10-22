package ua.training.model.dao;

import ua.training.exception.db.DBException;
import ua.training.model.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao() throws DBException;

    public abstract UniversityDao createUniversityDao() throws DBException;

    public abstract FacultyDao createFacultyDao() throws DBException;

    public abstract SubjectDao createSubjectDao() throws DBException;

    public abstract RequestDao createRequestDao() throws DBException;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

}
