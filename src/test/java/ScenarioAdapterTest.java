import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mosip.ivv.dba.adapter.ScenarioAdapter;
import main.java.io.mosip.ivv.core.structures.Person;
import main.java.io.mosip.ivv.core.structures.Persona;
import main.java.io.mosip.ivv.core.structures.Scenario;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ScenarioAdapterTest {
    private ScenarioAdapter scenarioAdapter=null;

    @Before
    public void setUp(){
        scenarioAdapter = new ScenarioAdapter("localhost","3306","test","root","admin",false);
    }

//    @Test
//    public void saveScenario(){
//        System.out.println("started saveScenario()");
//        Scenario scenario=crateScenario("Group-1","Tag-1","PsGroup-name");
//        scenarioAdapter.saveScenario(scenario);
//        System.out.println("ended  saveScenario()");
//    }

    @Test
    public void getScenariosByTag(){
        System.out.println("started getScenariosByTag()");
        Scenario scenario=scenarioAdapter.getScenariosByTag("tag1");
        displayScenario(scenario);
        System.out.println("ended  getScenariosByTag()");
    }


    @Test
    public void getAllScenarios(){
        System.out.println("started getAllScenarios()");
        ArrayList<Scenario> scenarios=scenarioAdapter.getScenarios();
        for(Scenario scenario :scenarios)
        displayScenario(scenario);
        System.out.println("ended  getAllScenarios()");
    }

//    @Test
//    public void saveListOfScenarios(){
//        System.out.println("started saveListOfScenarios()");
//        ArrayList<Scenario> arryListOfScenario=createScenarioList();
//        boolean isScenarioSaved=scenarioAdapter.saveScenarios(arryListOfScenario);
//        if(isScenarioSaved)
//            System.out.println("Data Saved!!!!");
//        else{
//            System.out.println("some peroblem occured while saving data!!");
//        }
//        System.out.println("ended  saveListOfScenarios()");
//    }

    @Test
    public void deleteScenarios(){
        System.out.println("started deleteScenarios()");
        scenarioAdapter.deleteScenarios("9");
        System.out.println("ended  deleteScenarios()");
    }


//    private ArrayList<Scenario> createScenarioList() {
//        ArrayList<Scenario> scenariosList= new ArrayList<Scenario>();
//        scenariosList.add(crateScenario("Group-2","Tag-2","PsGroup-name2"));
//        scenariosList.add(crateScenario("Group-3","Tag-3","PsGroup-name3"));
//         return  scenariosList;
//    }

//    private  Scenario crateScenario(String group_name,String tag, String personaGroup_name){
//        Scenario scenario= new Scenario();
//        scenario.group_name=group_name;
//        scenario.tag=tag;
//
//        Scenario.Data dt= new Scenario.Data();
//        dt.persona_class="Data_persona_class";
//        dt.tag="Data_tag";
//        dt.group_name="Data_group_name";
//
//        Person person=createPerson();
//
//        Persona per = new Persona();
//        per.group_name=personaGroup_name;
//        per.persona_class="invalid_test";
//        per.addPerson(person);
//
//        dt.persons.add(per);
//        scenario.data=dt;
//        return scenario;
//
//    }
//
//    private static Person createPerson(){
//        Person person= new Person();
//        person.city="Bangalore";
//        person.cnie_number="787878787";
//        return person;
//    }



    private static void displayScenario(Scenario scenario) {
        String scenarioValue = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            scenarioValue = objectMapper.writeValueAsString(scenario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("scenario : " + scenarioValue);
        System.out.println("  ");

    }

}
