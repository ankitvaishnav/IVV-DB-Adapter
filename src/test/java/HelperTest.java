import com.google.gson.Gson;

import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class HelperTest {
    public static void main(String[] args){
        ArrayList<String> rs = query("select * from users","localhost","5432", "swiftpad", "matt", "hannibal", false);
        for(String r: rs){
            System.out.println(r);
        }
    }
    public static ArrayList<String> query(String query, String host, String port, String database, String user, String password, Boolean ssl){
        String url = "jdbc:postgresql://"+host+":"+port+"/"+database;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        props.setProperty("ssl", String.valueOf(ssl));
        ArrayList<String> data = null;
        Gson gs = new Gson();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, props);
            if (conn != null) {
                Statement st = null;
                st = conn.createStatement();
                Boolean status = st.execute(query);
                if(status){
                    ResultSet rs = st.getResultSet();
                    data = new ArrayList<String>();
                    while(rs.next()){
                        data.add(gs.toJson(rs.getRow()));
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
