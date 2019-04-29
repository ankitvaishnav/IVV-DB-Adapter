package io.mosip.ivv.dba.domain;

public class Config {
    private String id;
    private String config_key;
    private String config_value;

    public Config(){}
    public Config(String id,String config_key,String config_value){
        this.id=id;
        this.config_key=config_key;
        this.config_value=config_value;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfig_key() {
        return config_key;
    }

    public void setConfig_key(String config_key) {
        this.config_key = config_key;
    }

    public String getConfig_value() {
        return config_value;
    }

    public void setConfig_value(String config_value) {
        this.config_value = config_value;
    }
}
