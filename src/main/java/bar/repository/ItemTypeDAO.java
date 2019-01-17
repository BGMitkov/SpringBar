package bar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.model.ItemType;

@Repository
public interface ItemTypeDAO extends NameRepository<ItemType/*, Long*/>, CrudRepository<ItemType, Long> {
}
