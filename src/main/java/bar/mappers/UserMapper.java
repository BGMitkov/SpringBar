package bar.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bar.model.Role;
import bar.model.User;

@Component
public class UserMapper implements RowMapper<User> {
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User(rs.getString("name"), rs.getString("password"), rs.getString("email"),
				Role.valueOf(rs.getString("role")), rs.getString("birthDate"));
		return user;
	}	
}
