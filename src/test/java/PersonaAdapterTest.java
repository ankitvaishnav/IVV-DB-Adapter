import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.mosip.ivv.dba.adapter.PersonaAdapter;
import main.java.io.mosip.ivv.core.structures.Person;
import main.java.io.mosip.ivv.core.structures.Persona;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PersonaAdapterTest {
   private PersonaAdapter padapter = null;

    @Before
    public void setUp(){
        padapter = new PersonaAdapter("localhost","3306","test","root","admin",false);
    }
    @Test
    public void savePersona(){
        System.out.println("savePersona() called");
        Persona persona = cratePersona("Group-12","PS-class-3","Pune","11111");
        boolean savePersona = padapter.savePersona(persona);
        if(savePersona)
            System.out.println("persona saved");
    }


    @Test
    public void saveListOfPersona(){
        System.out.println("saveListOfPersona() called");
        ArrayList<Persona> personaArrayList=createListOfPersona();
        boolean savePersona = padapter.savePersonas(personaArrayList);
        if(savePersona)
            System.out.println("persona saved");
    }

    @Test
    public  void getPesonas(){
        System.out.println("getPesonas() called");
        ArrayList<Persona> personas = padapter.getPesonas();
        for(Persona _persona : personas){
            displayPersona(_persona);
        }
    }

    @Test
    public void getPesonaById(){
        System.out.println("getPesonaById() called");
        Persona persona = padapter.getPesonaById("9");
        displayPersona(persona);
    }

    @Test
    public void getPersonaByGroup(){
        System.out.println("getPersonaByGroup() called");
        Persona persona =padapter.getPersonaByGroup("Group-12");
        displayPersona(persona);
    }

    @Test
    public void getPersonaByClass(){
        System.out.println("getPersonaByClass() called");
        Persona persona=padapter.getPersonaByClass("modify");
        displayPersona(persona);
    }

    @Test
    public void deletePersonas(){
        System.out.println("started deletePersonas()");
        padapter.deletePersonas("9");
        System.out.println("ended  deletePersonas()");
    }

    private  static ArrayList<Persona> createListOfPersona(){
        ArrayList<Persona> personaArrayList = new ArrayList<Persona>();
        personaArrayList.add(cratePersona("Group-10","PS-class-1","Hyd","45544"));
        personaArrayList.add(cratePersona("Group-11","PS-class-2","Chennai","22222"));
        return personaArrayList;
    }

    private static Persona cratePersona(String group_name,String persona_class,String person_city,String person_cnie_number){
        Persona persona = new Persona();
        persona.group_name=group_name;
        persona.persona_class=persona_class;
        persona.addPerson(createPerson(person_city,person_cnie_number));
        return persona;
    }

    private static Person createPerson(String city,String cnie_number){
        Person person= new Person();
        person.city=city;
        person.cnie_number=cnie_number;
        return person;
    }

    private static void displayPersona(Persona persona) {
        String personValue = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            personValue = objectMapper.writeValueAsString(persona);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("personas : " + personValue);
        System.out.println("  ");

    }
}
