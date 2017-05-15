package test;

import com.param.containers.*;

public class EnvInit {
	
	public static void setEnvs( EnvContainer envs ) {
		
		envs.put("ZCD_INFAREPO", "MBCDWRepZDev");
		envs.put("ZCD_INFAHOST", "mbcdwzcdetl01");
		envs.put("ZCD_INFAPORT", "6001");
		envs.put("ZCD_DBHOST", "MBCDWZCD-SCAN");
		envs.put("ZCD_DBPORT", "1521");
		envs.put("ZCD_DBSERVICE", "MBCDWZCDDB");
		//envs.put("ZCD_DBUSER", "cdw_user");
		//envs.put("ZCD_DBPASS", "D3vcdwu5r!");
		envs.put("ZCD_DBUSER", "c2lv");
		envs.put("ZCD_DBPASS", "9K89!05T27");
		envs.put("ZCD_DBMETA", "infometa");

		envs.put("ZC_INFAREPO", "MBCDWRepC");
		envs.put("ZC_INFAHOST", "mbcdwzsietl02");
		envs.put("ZC_INFAPORT", "6001");
		envs.put("ZC_DBHOST", "sfmbcdwqa-scan.comp.pge.com");
		envs.put("ZC_DBPORT", "1521");
		envs.put("ZC_DBSERVICE", "CDWTEST");
		//envs.put("ZC_DBUSER", "cdw_user");
		//envs.put("ZC_DBPASS", "T35tcdwu5r!");
		envs.put("ZC_DBUSER", "c2lv");
		envs.put("ZC_DBPASS", "9K89!05T27");
		envs.put("ZC_DBMETA", "infometa");
		
		
		envs.put("REGT_INFAREPO", "MBCDWRepRegT");
		envs.put("REGT_INFAHOST", "sfmbcdwetl02");
		envs.put("REGT_INFAPORT", "6001");
		envs.put("REGT_DBHOST", "mbcdwccb-scan");
		envs.put("REGT_DBPORT", "1521");
		envs.put("REGT_DBSERVICE", "CDWREGT");
		//envs.put("REGT_DBUSER", "cdw_user");
		//envs.put("REGT_DBPASS", "T35tcdwu5r!");
		envs.put("REGT_DBUSER", "c2lv");
		envs.put("REGT_DBPASS", "9K89!05T27");
		envs.put("REGT_DBMETA", "infometa");
		
		envs.put("ZSI_INFAREPO", "MBCDWRepZSi");
		envs.put("ZSI_INFAHOST", "mbcdwzsietl01");
		envs.put("ZSI_INFAPORT", "6001");
		envs.put("ZSI_DBHOST", "MBCDWZSI-SCAN.comp.pge.com");
		envs.put("ZSI_DBPORT", "1521");
		envs.put("ZSI_DBSERVICE", "MBCDWZSIDB");
		//envs.put("ZSI_DBUSER", "cdw_user");
		//envs.put("ZSI_DBPASS", "T35tcdwu5r!");
		envs.put("ZSI_DBUSER", "c2lv");
		envs.put("ZSI_DBPASS", "9K89!05T27");
		envs.put("ZSI_DBMETA", "infometa");
		
	}
}