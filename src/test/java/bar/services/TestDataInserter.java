package bar.services;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;

import bar.utility.DatabaseUtilities;

/**
 * Test class used in testing
 * 
 * @author bgmitkov
 *
 */
@Component
public class TestDataInserter {

	@Autowired
	private DatabaseUtilities databaseUtilities;

	@PostConstruct
	public void init() {
		databaseUtilities.storeTestData();
	}
}
