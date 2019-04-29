package io.mosip.ivv.dba.domain;

public class Global {
    private  String id;
    private String global_key;
    private String  global_value;

    public Global(){

    }
    public Global(String id, String global_key, String global_value) {
        this.id = id;
        this.global_key = global_key;
        this.global_value = global_value;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGlobal_key() {
        return global_key;
    }

    public void setGlobal_key(String global_key) {
        this.global_key = global_key;
    }

    public String getGlobal_value() {
        return global_value;
    }

    public void setGlobal_value(String global_value) {
        this.global_value = global_value;
    }




}
