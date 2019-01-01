package bar.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import bar.model.User;

@Component
public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//		Date date = rs.getDate("birthDate");
//		LocalDate localDate = LocalDate.ofEpochDay(date.getTime());
//		User user = new User(rs.getString("name"), rs.getString("password"), rs.getString("email"),
//				Role.valueOf(rs.getString("role")), localDate);
		return null;
	}
}
