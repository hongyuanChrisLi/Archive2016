package com.web.param;

import java.util.HashMap;
import java.util.Map;

public class SysPrefContainer {

  private Map<String, InfaCredContainer> infaCredMap = new HashMap<String, InfaCredContainer>();

  public void putInfaCred (String env, InfaCredContainer infaCred) {
    infaCredMap.put(env, infaCred);
  }
  
  public InfaCredContainer getInfaCred (String env) {
    return infaCredMap.get(env);
  }
  
}