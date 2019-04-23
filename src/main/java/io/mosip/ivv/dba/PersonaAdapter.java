package io.mosip.ivv.dba;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.mosip.ivv.dba.exception.PersonaIdNotFoundException;
import io.mosip.ivv.dba.exception.PersonaNotFoundException;
import io.mosip.ivv.dba.utils.Helper;
import main.java.io.mosip.ivv.core.structures.Persona;

public class PersonaAdapter implements PersonaService {

    private static final String SELECT_FROM_PERSONAS = "select * from personas";
    private String host = null;
    private String port = null;
    private String database = null;
    private String user = null;
    private String password = null;
    private Boolean ssl = false;

    public PersonaAdapter() {

    }

    public PersonaAdapter(String host, String port, String database, String user, String password, Boolean ssl) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.ssl = ssl;
    }

    public ArrayList<Persona> getPesonas() {
        ArrayList<Persona> personList = new ArrayList<Persona>();
        Persona persona = null;
        ArrayList<String> result = Helper.queryForSelect(SELECT_FROM_PERSONAS, this.host, this.port, this.database, this.user,
                this.password, this.ssl);
        if (result.size() > 0) {
            Iterator<String> it = result.iterator();
            try {
                while (it.hasNext()) {
                    String jsonInString = it.next();
                    if (jsonInString != null && jsonInString.startsWith("{")) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        persona = objectMapper.readValue(jsonInString, Persona.class);
                        personList.add(persona);
                    }
                }
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new PersonaNotFoundException("No Persona Found in DB");
        }

        return personList;
    }

    public Persona getPesonaById(String personaId) {
        Persona persona = null;
        String jsonInString = null;
        String sqlQuery = "select * from personas where id=" + personaId;
        ArrayList<String> result = Helper.queryForSelect(sqlQuery, this.host, this.port, this.database, this.user,
                this.password, this.ssl);

        if (result.size() > 0) {
            Iterator<String> it = result.iterator();
            try {
                while (it.hasNext()) {
                    jsonInString = it.next();
                    if (jsonInString != null && jsonInString.startsWith("{")) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        persona = objectMapper.readValue(jsonInString, Persona.class);
                    }
                }
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new PersonaIdNotFoundException("Persona Id " + personaId + " is not Found !! ");
        }

        return persona;
    }

    public Persona getPersonaByGroup(String group_name) {
        String sqlQuery = "select * from personas where group_name= '" + group_name + "' ";
        Persona persona = null;
        ArrayList<String> result = Helper.queryForSelect(sqlQuery, this.host, this.port, this.database, this.user,
                this.password, this.ssl);
        Iterator<String> it = result.iterator();
        try {
            while (it.hasNext()) {
                String jsonInString = it.next();
                if (jsonInString != null && jsonInString.startsWith("{")) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    persona = objectMapper.readValue(jsonInString, Persona.class);
                }
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public Persona getPersonaByClass(String persona_class) {
        String sqlQuery = "select * from personas where persona_class= '" + persona_class + "' ";
        Persona persona = null;
        ArrayList<String> result = Helper.queryForSelect(sqlQuery, this.host, this.port, this.database, this.user,
                this.password, this.ssl);
        Iterator<String> it = result.iterator();
        try {
            while (it.hasNext()) {
                String jsonInString = it.next();
                if (jsonInString != null && jsonInString.startsWith("{")) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    persona = objectMapper.readValue(jsonInString, Persona.class);
                }
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public boolean savePersona(Persona persona) {
        boolean isSaved = false;
        if (persona != null) {
            String jsonInString = null;
            String group_name = persona.group_name;
            String persona_class = persona.persona_class;
            ObjectMapper mapper = new ObjectMapper();
            try {
                jsonInString = mapper.writeValueAsString(persona);
                System.out.println(jsonInString);
            } catch (JsonProcessingException e) {
            }
            String sqlQuery = "insert into personas(group_name,persona_class,data) values('" + group_name + "','"
                    + persona_class + "','" + jsonInString + "')";
            int queryForNonSelect = Helper.queryForNonSelect(sqlQuery, this.host, this.port, this.database, this.user,
                    this.password, this.ssl);
            if (queryForNonSelect > 0) {
                isSaved = true;
            }
        } else {
            throw new RuntimeException("Persona can't be null");
        }
        return isSaved;
    }

}
