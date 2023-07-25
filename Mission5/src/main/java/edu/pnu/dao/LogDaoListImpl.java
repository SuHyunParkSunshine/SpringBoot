package edu.pnu.dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogDaoListImpl implements LogDaoInterface {

	@Override
	public void addLog(String method, String sqlstring, boolean success) {
		try {
			File file = new File("log.txt");
			FileWriter fw = new FileWriter(file, true);
			fw.write(method + ", " + sqlstring + ", " + success + "\n");
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
