import io.mosip.ivv.dba.adapter.ConfigAdapter;
import io.mosip.ivv.dba.domain.Config;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfigAdapterTest {
    private  ConfigAdapter configAdapter;

    @Before
    public void setUp(){
        configAdapter = new ConfigAdapter("localhost","3306","test","root","admin",false);
    }


    @Test
    public void saveConfigs(){
      System.out.println("started saveConfigs()");
        boolean isSaved=configAdapter.saveConfigs(createConfigMap());
        if(isSaved){
            System.out.println("Data is saved !!");
        }
        System.out.println("ended saveConfigs()");
    }

    @Test
    public void getConfigs(){
        System.out.println("started getConfigs()");
        ArrayList<Config> list=configAdapter.getConfigs();
        for(Config config :list){
            System.out.println(config.getId()+"\n"+config.getConfig_key()+"\n"+config.getConfig_value());
            System.out.println("");
        }
        System.out.println("ended getConfigs()");
    }

    @Test
    public void deleteConfigs(){
        System.out.println("started deleteConfigs()");
        int id=configAdapter.deleteConfigs("1");
        if(id>0)
        System.out.println(id +" is deleted");
        else{
            System.out.println(" No Record Found");
        }
        System.out.println("ended deleteConfigs()");
    }

    private HashMap<String, String> createConfigMap(){
        HashMap<String, String> configMap = new HashMap<String, String>();
        configMap.put("key-1","value-1");
        configMap.put("key-2","value-2");
        configMap.put("key-3","value-3");
        return configMap;
    }
}
