package bar.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bar.model.Role;
import bar.model.User;

public class UserMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User(rs.getString("name"), rs.getString("password"), rs.getString("email"),
				Role.valueOf(rs.getString("role")), rs.getString("birthDate"));
		return user;
	}	
}
