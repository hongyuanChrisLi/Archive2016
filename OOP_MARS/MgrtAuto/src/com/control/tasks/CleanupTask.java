package com.control.tasks;

import java.io.IOException;
import java.sql.SQLException;

import com.control.*;
import com.param.containers.ParamContainer;

public class CleanupTask implements FlowTask {

	ParamContainer params;
	
	public CleanupTask (ParamContainer params) {
		
		this.params = params;
	}

	@Override
	public int execute() {
		
		
		try {
			params.getCustFileParm().getDisplayLogWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			params.getOrignDBParm().getCnx().close();
			params.getDestDBParm().getCnx().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return 0;
	}
	
}