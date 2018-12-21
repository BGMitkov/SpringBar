package bar.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

import bar.utility.DatabaseUtilities;

@TestComponent
public class TestDataInserter {

	@Autowired
	private DatabaseUtilities databaseUtilities;

	@PostConstruct
	public void init() {
		databaseUtilities.storeTestData();
	}
}
