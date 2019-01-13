package bar.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.Item;
import bar.model.ItemType;

@Repository
public interface ItemDAO extends CrudRepository<Item, Long> {
	
	Item findByName(String name);
	
	List<ItemType> findByItemType(ItemType itemType);
}
