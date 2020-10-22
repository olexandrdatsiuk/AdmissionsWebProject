package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.dao.UniversityDao;
import ua.training.model.entity.University;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.training.exception.Message.DAO_EXCEPTION_THROWN;
import static ua.training.model.sql.JDBCQuery.FIND_ALL_UNIVERSITIES;

public class JDBCUniversityDao implements UniversityDao {
    private static final Logger logger = LogManager.getLogger(JDBCUniversityDao.class);

    private Connection conn;

    public JDBCUniversityDao(Connection connection) {
        this.conn = connection;
    }

    @Override
    public Optional<List<University>> findAll(String lang) {
        List<University> uList = new ArrayList<>();
        try (
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(String.format(FIND_ALL_UNIVERSITIES, lang))
        ) {
            while (rs.next()) {
                uList.add(new University(rs.getInt(1), rs.getString(2)));
            }
            return Optional.of(uList);
        } catch (SQLException e) {
            logger.error(DAO_EXCEPTION_THROWN, e);
        }
        return Optional.empty();
    }

    @Override
    public void close() {
        close(conn);
    }

    private void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                logger.error(DAO_EXCEPTION_THROWN, e);
            }
        }
    }
}
