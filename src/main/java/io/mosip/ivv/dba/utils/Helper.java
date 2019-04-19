package io.mosip.ivv.dba.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class Helper {

    public static ArrayList<ResultSet> query(String query, String host, String port, String database, String user, String password, Boolean ssl){
        String url = "jdbc:postgresql://"+host+":"+port+"/"+database;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("ssl", String.valueOf(ssl));
        ArrayList<ResultSet> data = null;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
            if (conn != null) {
                Statement st = null;
                st = conn.createStatement();
                Boolean status = st.execute(query);
                if(status){
                    ResultSet rs = st.getResultSet();
                    while(rs.next()){
                        data.add(rs);
                    }
                }
                st.close();
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
