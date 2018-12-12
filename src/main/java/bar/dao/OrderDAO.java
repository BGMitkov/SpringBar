package bar.dao;

import org.springframework.stereotype.Repository;

import bar.mappers.OrderMapper;
import bar.model.Order;

@Repository
public class OrderDAO extends AbstractJpaDAO<Order>{
	
	private OrderMapper orderMapper;
	
}
