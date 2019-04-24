package io.mosip.ivv.dba.service;

import main.java.io.mosip.ivv.core.structures.Scenario;

import java.util.ArrayList;

public interface ScenarioService {
    boolean saveScenario(Scenario scenario);
    ArrayList<Scenario> getScenarios();
    Scenario getScenariosByTag(String tc_no);
    boolean saveScenarios(ArrayList<Scenario> listOfScenarios);
    void deleteScenarios(String scenarioId);

}
