package bar.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bar.model.Order;
import bar.model.User;

@Component
public class OrderMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setAcceptanceDate(rs.getLong("acceptaceDate"));
		order.setExecutor((User) rs.getObject("executor"));
		order.setOrderDate(rs.getLong("orderDate"));
		return null;
	}

}
