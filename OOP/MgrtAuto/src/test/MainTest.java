package test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.control.*;
import com.control.requests.*;
import com.infa.*;
import com.infa.cmd.*;
import com.param.containers.*;
import com.infa.tasks.*;
import com.file.util.*;

public class MainTest {
	
	public static void main (String[] args ) throws IOException{
	
		
		MigrationRequest mgrtReq = new MigrationRequest();
		mgrtReq.setDBUser("c2lv");
		mgrtReq.setOrignEnv("ZCD");
		mgrtReq.setOrignInfaUser("c2lvadmin");
		mgrtReq.setOrignInfaPass("K89!05T27");
		mgrtReq.setRequester("Somebody");
		mgrtReq.setAdminUser("c2lvadmin");
		
		/*mgrtReq.setDestEnv("ZC");
		mgrtReq.setDestUser("c2lvadmin");
		mgrtReq.setDestPass("K89!05T27");*/
		
		
		mgrtReq.setDestEnv("ZSI");
		mgrtReq.setDestInfaUser("c2lvadmin");
		mgrtReq.setDestInfaPass("K89!05T27");
		
		/*mgrtReq.setCRQ("CRQ000000097267");
		mgrtReq.addInfaObject("EI_REBATE_DTL_SI MBCDW_Shared_Stg");
		mgrtReq.addInfaObject("m_EI_REBATE_DTL_PS_Load	PPA_DM");
		mgrtReq.addInfaObject("m_EE_REBATE_PMT_SAVE_FACT_EI_Load");*/
		//mgrtReq.addInfaObject("m_TSHT_TIME_ENTRY_BREAK_OUT FAS_DM");
		
		//mgrtReq.setCRQ("CRQ000000105695");
		//mgrtReq.addInfaObject("sc_EI_Measure_Savings__c PPA_DM");
		//mgrtReq.addInfaObject("m_SVC_PT_SI_Load");
		
		
		//mgrtReq.setIsGroup(false);
		
		//mgrtReq.setCRQ("CRQ000000104089");
		//mgrtReq.setCRQ("CRQ000000103681");
		mgrtReq.setCRQ("CRQ000000107334");
		mgrtReq.setIsGroup(true);
		
		
		EnvContainer envs = new EnvContainer();
		EnvInit.setEnvs(envs);
		
		InitParmContainer initParm = new InitParmContainer ();
		initParm.setEnvContainer(envs);
		initParm.setMigrationRequest(mgrtReq);
		

		ParamContainer params = new ParamContainer(initParm);

		
		MigrationFlow mgrtFlow = new MigrationFlow(params);
		mgrtFlow.start();
		System.out.println("The Tread is out");
		
		
		
		while ( mgrtFlow.isAlive() )
		{
			try {
				Thread.sleep (1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		params.getCustFileParm().getDisplayLogWriter().close();
		
	}
	
	/*ReadWriteTest rwtest = new ReadWriteTest() ;
	
	rwtest.testPersisFileReader();*/
}
