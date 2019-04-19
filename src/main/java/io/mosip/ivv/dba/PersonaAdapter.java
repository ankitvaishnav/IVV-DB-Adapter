package io.mosip.ivv.dba;

import io.mosip.ivv.dba.utils.Helper;
import main.java.io.mosip.ivv.core.structures.Persona;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonaAdapter implements BaseInterface{

    private String host = null;
    private String port = null;
    private String database = null;
    private String user = null;
    private String password = null;
    private Boolean ssl = false;

    public void setConfig(String host, String port, String database, String user, String password, Boolean ssl) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.ssl = ssl;
    }

//    public Persona getPersonaById(String id){
//        ResultSet rs = query("select * from table where id="+id+";");
//        //TODO resultset to persona
//    }

    private Persona resultSet2Persona(ResultSet rs){
        Persona persona = new Persona();
        try {
            persona.group_name = rs.getString("group_name");
            persona.persona_class = rs.getString("persona_class");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    private ArrayList<ResultSet> query(String query){
        ArrayList<ResultSet> rs = Helper.query(query, this.host, this.port,this.database, this.user, this.password, this.ssl);
        return rs;
    }


}
