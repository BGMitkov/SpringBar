package bar.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bar.utility.DatabaseUtilities;

/**
 * Class for inserting data used during development
 * 
 * @author bgmitkov
 *
 */
@Component
public class DataInserter {

	@Autowired
	private DatabaseUtilities databaseUtilities;

	@PostConstruct
	public void init() {
		databaseUtilities.storeTestData();
	}
}
