package io.mosip.ivv.dba.adapter;

import io.mosip.ivv.dba.domain.Global;
import io.mosip.ivv.dba.service.GlobalService;
import io.mosip.ivv.dba.utils.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GlobalAdapter implements GlobalService {
    private String host = null;
    private String port = null;
    private String database = null;
    private String user = null;
    private String password = null;
    private Boolean ssl = false;

    public GlobalAdapter() {

    }

    public GlobalAdapter(String host, String port, String database, String user, String password, Boolean ssl) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.ssl = ssl;
    }

    public boolean saveGlobals(HashMap<String, String> globalMap) {
        boolean isSaveConfigData = false;
        if (globalMap != null && !globalMap.isEmpty()) {
            try {
                Iterator<Map.Entry<String, String>> entryIterator = globalMap.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, String> entry = entryIterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    String sqlQuery = "insert into globals(global_key,global_value) values('" + key + "','" + value + "')";
                    int queryForNonSelect = Helper.queryForNonSelect(sqlQuery, this.host, this.port, this.database, this.user,
                            this.password, this.ssl);
                    if (queryForNonSelect > 0) {
                        isSaveConfigData = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("globalMap can't be Empty!!");
        }
        return isSaveConfigData;
    }

    public ArrayList<Global> getGlobals() {
        ArrayList<Global> globalList =null;
        String sqlQuery = "select * from globals ";
        ArrayList<String> result = Helper.queryForSelect(sqlQuery, this.host, this.port, this.database, this.user,
                this.password, this.ssl);
        if(result!=null && result.size()>0){
            globalList=createGlobalList(result);
        }else {
            throw new RuntimeException("No Global Result found in DB");
        }
        return globalList;
    }

    public int deleteGlobal(String globalId) {
        int isRecordDeleted=0;
        if(globalId!=null && globalId.length()>0){
            String sqlQuery="delete from globals where id= '" + globalId + "' ";
            isRecordDeleted = Helper.queryForNonSelect(sqlQuery, this.host, this.port, this.database, this.user,
                    this.password, this.ssl);
        }else{
            throw new RuntimeException("globalId cannot be Empty!");
        }
        return isRecordDeleted;
    }

    /*
        This method is used to create List of Global.
        @param globalResult.
        return ArrayList<Global>.
     */
    private static ArrayList<Global> createGlobalList(ArrayList<String> globalResult) {
        ArrayList<Global> globalList = new ArrayList<Global>();
        Global global = null;
        for (int i = 0; i < globalResult.size(); i++) {
            String id = globalResult.get(i++);
            String _key = globalResult.get(i++);
            String _value = globalResult.get(i);
            global = new Global(id, _key, _value);
            globalList.add(global);
        }
        return globalList;
    }
}
