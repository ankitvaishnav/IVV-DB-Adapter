package io.mosip.ivv.dba.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mosip.ivv.dba.exception.ScenarioNotFoundException;
import io.mosip.ivv.dba.service.ScenarioService;
import io.mosip.ivv.dba.utils.Helper;
import main.java.io.mosip.ivv.core.structures.Scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ScenarioAdapter implements ScenarioService {
    private static final String SELECT_FROM_SCENARIOS = "select * from Scenarios";
    private String host = null;
    private String port = null;
    private String database = null;
    private String user = null;
    private String password = null;
    private Boolean ssl = false;

    public ScenarioAdapter() {

    }

    public ScenarioAdapter(String host, String port, String database, String user, String password, Boolean ssl) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.ssl = ssl;
    }


    public boolean saveScenario(Scenario scenario) {
        boolean isSaved = false;
        String jsonInString = null;
        if (scenario != null) {
            String tc_no = scenario.tag;
            String group_name = scenario.group_name;
            try {
                ObjectMapper mapper = new ObjectMapper();
                jsonInString = mapper.writeValueAsString(scenario);
                System.out.println(jsonInString);
                String sqlQuery = "insert into scenarios(tc_no,group_name,data) values('" + tc_no + "','"
                        + group_name + "','" + jsonInString + "')";
                int queryForNonSelect = Helper.queryForNonSelect(sqlQuery, this.host, this.port, this.database, this.user,
                        this.password, this.ssl);
                if (queryForNonSelect > 0) {
                    isSaved = true;
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("scenario  cannot be null");
        }
        return isSaved;
    }


    public ArrayList<Scenario> getScenarios() {
        ArrayList<Scenario> scenarioList = new ArrayList<Scenario>();
        Scenario scenario = null;
        ArrayList<String> result = Helper.queryForSelect(SELECT_FROM_SCENARIOS, this.host, this.port, this.database, this.user,
                this.password, this.ssl);
        if (result.size() > 0) {
            Iterator<String> it = result.iterator();
            try {
                while (it.hasNext()) {
                    String jsonInString = it.next();
                    if (jsonInString != null && jsonInString.startsWith("{")) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        scenario = objectMapper.readValue(jsonInString, Scenario.class);
                        scenarioList.add(scenario);
                    }
                }
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new ScenarioNotFoundException("No Persona Found in DB");
        }

        return scenarioList;
    }

    public Scenario getScenariosByTag(String tc_no) {
        String sqlQuery = "select * from scenarios where tc_no= '" + tc_no + "' ";
        Scenario scenario = null;
        if (tc_no != null) {
            ArrayList<String> result = Helper.queryForSelect(sqlQuery, this.host, this.port, this.database, this.user,
                    this.password, this.ssl);
            Iterator<String> it = result.iterator();
            try {
                while (it.hasNext()) {
                    String jsonInString = it.next();
                    if (jsonInString != null && jsonInString.startsWith("{")) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                        scenario = objectMapper.readValue(jsonInString, Scenario.class);
                    }
                }
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return scenario;
    }

    public boolean saveScenarios(ArrayList<Scenario> listOfScenarios) {
        boolean isSaved=false;
        String jsonInString = null;
        if(!listOfScenarios.isEmpty()){
            for (Scenario scenario:listOfScenarios) {
                String group_name=scenario.group_name;
                String tc_no=scenario.tag;
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    jsonInString = mapper.writeValueAsString(scenario);
                    System.out.println(jsonInString);
                    String sqlQuery = "insert into scenarios(tc_no,group_name,data) values('" + tc_no + "','"
                            + group_name + "','" + jsonInString + "')";
                    int queryForNonSelect = Helper.queryForNonSelect(sqlQuery, this.host, this.port, this.database, this.user,
                            this.password, this.ssl);
                    if (queryForNonSelect > 0) {
                        isSaved = true;
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }else{
            throw new RuntimeException("size of listOfScenarios  cannot be empty");
        }
        return isSaved;
    }

    public void deleteScenarios(String scenarioId) {

        if(scenarioId!=null && scenarioId.length()>0){
        String sqlQuery="delete from scenarios where id= '" + scenarioId + "' ";
            int queryForNonSelect = Helper.queryForNonSelect(sqlQuery, this.host, this.port, this.database, this.user,
                    this.password, this.ssl);
            if (queryForNonSelect > 0) {
               System.out.println("Record with scenarioId   "+scenarioId +" is deleted  ");
            }else{
               throw new ScenarioNotFoundException("No scenario found with  Id "+scenarioId);
            }
        }else{
            throw new RuntimeException("scenarioId cannot be Empty!");
        }
    }
}

