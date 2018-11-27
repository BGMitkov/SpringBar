package bar.model;

import java.util.Date;

public class User {
	private Long id;
	private String username;
	//TODO encrypt and hash the password
	private String password;
	private String email;
	private Role role;
	private Date birthDate;
	public User(String username, String password, String email, Role role, Date birthDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.birthDate = birthDate;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", birthDate=" + birthDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
