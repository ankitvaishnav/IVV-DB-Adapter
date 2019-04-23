package io.mosip.ivv.dba.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Helper {
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String SSL = "ssl";

    public static int queryForNonSelect(String nonSelectSQLQuery, String host, String port, String database,
                                        String user, String password, Boolean ssl) {
        Statement st = getQueryStatus(host, port, database, user, password, ssl);
        int rowUpdated = 0;
        try {
            rowUpdated = st.executeUpdate(nonSelectSQLQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection(null, st, null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rowUpdated;

    }

    public static ArrayList<String> queryForSelect(String selectSQLQuery, String host, String port, String database,
                                                   String user, String password, Boolean ssl) {
        Statement st = getQueryStatus(host, port, database, user, password, ssl);
        ResultSet rs = null;
        ArrayList<String> data = new ArrayList<String>();
        try {
            rs = st.executeQuery(selectSQLQuery);
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
                    data.add(rs.getString(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnection(null, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    private static Statement getQueryStatus(String host, String port, String database, String user, String password,
                                            boolean ssl) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        Properties props = getConnectionProperty(user, password, ssl);
        Connection conn = null;
        Statement statement = null;
        try {
            conn = DriverManager.getConnection(url, props);
            if (conn != null) {
                statement = conn.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    private static void closeConnection(Connection conn, Statement st, ResultSet rs) throws SQLException {
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }
        if (rs != null) {
            rs.close();
        }

    }

    private static Properties getConnectionProperty(String user, String password, boolean ssl) {
        Properties props = new Properties();
        props.setProperty(USER, user);
        props.setProperty(PASSWORD, password);
        props.setProperty(SSL, String.valueOf(ssl));
        return props;
    }
}
