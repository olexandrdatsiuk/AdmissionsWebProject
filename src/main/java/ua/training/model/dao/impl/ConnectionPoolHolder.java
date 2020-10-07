package ua.training.model.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static final Logger logger = LogManager.getLogger(ConnectionPoolHolder.class);

    private static volatile DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    logger.debug("Instantiating DataSource..");
                    final String name = "java:comp/env/jdbc/DB4ADMISSIONS";
                    try {
                        InitialContext ctx = new InitialContext();
                        dataSource = (DataSource) ctx.lookup(name);
                        //todo to close ctx ??
//                        ctx.close(); // todo close
                    } catch (NamingException e) {
                        String ex = "NamingException is thrown for name: " + name;
                        logger.fatal("FAILED: " + ex);
                        throw new IllegalArgumentException("ex", e);
                        // todo what exception to throw
                    }
                    logger.debug("Successfully instantiated");
                }
            }
        }
        return dataSource;

    }
}

