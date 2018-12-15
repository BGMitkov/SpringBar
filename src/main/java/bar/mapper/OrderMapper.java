package bar.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bar.model.Order;

@Component
public class OrderMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setOrderId(rs.getLong("id"));
		order.setAcceptanceDate(rs.getLong("acceptaceDate"));
		order.setExecutor(rs.getString("executor"));
		order.setOrderDate(rs.getLong("orderDate"));
		return null;
	}

}
