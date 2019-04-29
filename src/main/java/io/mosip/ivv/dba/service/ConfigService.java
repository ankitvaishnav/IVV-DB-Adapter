package io.mosip.ivv.dba.service;

import io.mosip.ivv.dba.domain.Config;

import java.util.ArrayList;
import java.util.HashMap;

public interface ConfigService {
    boolean saveConfigs(HashMap<String,String> hashMap);
    ArrayList<Config> getConfigs();
    int deleteConfigs(String configId);
}
