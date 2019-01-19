package bar.repository;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bar.mapper.OrderMapper;
import bar.model.Order;
import bar.model.OrderStatus;
import bar.model.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByStatus(OrderStatus status);
	
	List<Order> findByExecutorAndStatus(User executor, OrderStatus status);
}
