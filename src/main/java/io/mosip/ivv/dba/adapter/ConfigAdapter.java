package io.mosip.ivv.dba.adapter;

import io.mosip.ivv.dba.domain.Config;
import io.mosip.ivv.dba.service.ConfigService;
import io.mosip.ivv.dba.utils.Helper;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.util.*;

public class ConfigAdapter implements ConfigService {
    private String host = null;
    private String port = null;
    private String database = null;
    private String user = null;
    private String password = null;
    private Boolean ssl = false;

    public ConfigAdapter() {

    }

    public ConfigAdapter(String host, String port, String database, String user, String password, Boolean ssl) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
        this.ssl = ssl;
    }

    public boolean saveConfigs(HashMap<String, String> configMap) {
        boolean isSaveConfigData = false;
        Config config = null;
        if (configMap != null && !configMap.isEmpty()) {
            try {
                Iterator<Map.Entry<String, String>> entryIterator = configMap.entrySet().iterator();
                while (entryIterator.hasNext()) {
                    Map.Entry<String, String> entry = entryIterator.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    String sqlQuery = "insert into config(config_key,config_value) values('" + key + "','" + value + "')";
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
            throw new RuntimeException("configMap can't be Empty!!");
        }
        return isSaveConfigData;
    }

    public ArrayList<Config> getConfigs() {
        ArrayList<Config> configList =null;
        String sqlQuery = "select * from config ";
        ArrayList<String> result = Helper.queryForSelect(sqlQuery, this.host, this.port, this.database, this.user,
                this.password, this.ssl);
        if(result!=null && result.size()>0){
            configList=createConfigList(result);
        }else {
            throw new RuntimeException("No Config Result found in DB");
        }
        return configList;
    }

    public int deleteConfigs(String configId) {
        int isRecordDeleted=0;
        if(configId!=null && configId.length()>0){
            String sqlQuery="delete from config where id= '" + configId + "' ";
             isRecordDeleted = Helper.queryForNonSelect(sqlQuery, this.host, this.port, this.database, this.user,
                    this.password, this.ssl);
        }else{
            throw new RuntimeException("configId cannot be Empty!");
        }
        return isRecordDeleted;
    }


    private static ArrayList<Config> createConfigList(ArrayList<String> configResult) {
        ArrayList<Config> configList = new ArrayList<Config>();
        Config config = null;
        for (int i = 0; i < configResult.size(); i++) {
            String id = configResult.get(i++);
            String _key = configResult.get(i++);
            String _value = configResult.get(i);
            config = new Config(id, _key, _value);
            configList.add(config);
        }
        return configList;
    }
}
