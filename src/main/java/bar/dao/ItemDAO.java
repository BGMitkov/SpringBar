package bar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.Item;

@Repository
public interface ItemDAO extends CrudRepository<Item, Long> {
	
	Item findByName(String name);
}
