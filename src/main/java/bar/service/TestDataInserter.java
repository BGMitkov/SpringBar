package bar.service;

import org.springframework.beans.factory.annotation.Autowired;

import bar.utility.DatabaseUtilities;

public class TestDataInserter {
	
	@Autowired
	private DatabaseUtilities databaseUtilities;

	public void init() {
		databaseUtilities.storeTestData();
	}
	
	public void destroy() {
		databaseUtilities.deleteData();
	}
}
