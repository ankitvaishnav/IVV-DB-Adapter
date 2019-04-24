package io.mosip.ivv.dba.service;

import main.java.io.mosip.ivv.core.structures.Persona;

import java.util.ArrayList;

public interface PersonaService {

    ArrayList<Persona> getPesonas();

    Persona getPesonaById(String personaId);
    boolean savePersona(Persona persona);

    Persona getPersonaByClass(String persona_class);

    Persona getPersonaByGroup(String group_name);

	boolean savePersonas(ArrayList<Persona> personas);

    void deletePersonas(String personaId);

}
