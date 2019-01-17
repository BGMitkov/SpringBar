package bar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.Bill;
import bar.model.BillStatus;

@Repository
public interface BillDAO extends CrudRepository<Bill, Long> {
	
	//TODO check whether it returns a list or one object
	Bill findByTableNumberAndStatus(String table, BillStatus status);
}
