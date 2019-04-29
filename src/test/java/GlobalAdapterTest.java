import io.mosip.ivv.dba.adapter.GlobalAdapter;
import io.mosip.ivv.dba.domain.Global;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalAdapterTest {
    private GlobalAdapter globalAdapter;

    @Before
    public void setUp(){
        globalAdapter = new GlobalAdapter("localhost","3306","test","root","admin",false);
    }


    @Test
    public void  saveGlobals(){
        System.out.println("started saveGlobals()");
        boolean isSaved=globalAdapter.saveGlobals(createGlobalMap());
        if(isSaved)
            System.out.println("Data saved !");
        else{
            System.out.println("Some problem occured while saving data");
        }
        System.out.println("ended saveGlobals()");
    }


    @Test
    public void getGlobals(){
        System.out.println("started getGlobals()");
        ArrayList<Global> list=globalAdapter.getGlobals();
        for(Global global :list){
            System.out.println(global.getId()+"\n"+global.getGlobal_key()+"\n"+global.getGlobal_value());
            System.out.println("");
        }
        System.out.println("ended getGlobals()");
    }

    @Test
    public void deleteGlobals(){
        System.out.println("started deleteGlobals()");
        int id=globalAdapter.deleteGlobal("1");
        if(id>0)
            System.out.println(id +" is deleted");
        else{
        }
        System.out.println("ended deleteGlobals()");
    }



    private HashMap<String, String> createGlobalMap(){
        HashMap<String, String> globalMap = new HashMap<String, String>();
        globalMap.put("key-1","value-1");
        globalMap.put("key-2","value-2");
        globalMap.put("key-3","value-3");
        return globalMap;
    }
}
