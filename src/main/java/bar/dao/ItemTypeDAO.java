package bar.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.ItemType;

@Repository
public interface ItemTypeDAO extends CrudRepository<ItemType, Long> {
	ItemType findByName(String name);
}
