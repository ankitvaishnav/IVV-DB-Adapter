package io.mosip.ivv.dba.service;

import io.mosip.ivv.dba.domain.Global;

import java.util.ArrayList;
import java.util.HashMap;

public interface GlobalService {
    boolean saveGlobals(HashMap<String,String> globalMap);
    ArrayList<Global> getGlobals();
    int deleteGlobal(String globalsId);
}
