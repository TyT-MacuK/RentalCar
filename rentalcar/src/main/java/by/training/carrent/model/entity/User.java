package by.training.carrent.model.entity;

import java.time.LocalDate;

public class User extends AbstractEntity {
	public enum UserStatus {
		ACTIVE, BANNED, CONFIRMATION_AWAITING
	}

	public enum UserRole {
		ADMIN, USER, GUEST
	}

	private long userId;
	private String email;
	private String firstName;
	private String lastName;
	private int discont;
	private String phoneNumber;
	private LocalDate dateOfBirth;
	private UserStatus status;
	private UserRole role;

	public User() {
	}

	public User(long userId, String email, String firstName, String lastName, int discont,
			String phoneNumber, LocalDate dateOfBirth, UserStatus status, UserRole role) {
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.discont = discont;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.status = status;
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getDiscont() {
		return discont;
	}

	public void setDiscont(int discont) {
		this.discont = discont;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(userId);
		result = prime * result + email.hashCode();
		result = prime * result + firstName.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + discont;
		result = prime * result + phoneNumber.hashCode();
		result = prime * result + dateOfBirth.hashCode();
		result = prime * result + status.hashCode();
		result = prime * result + role.hashCode();
		return result;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		User user = (User) object;
		if (userId != user.userId) {
			return false;
		}
		if (email == null) {
			if (user.email != null) {
				return false;
			}
		} else if (!email.equals(user.email)) {
			return false;
		}
		if (firstName == null) {
			if (user.firstName != null) {
				return false;
			}
		} else if (!firstName.equals(user.firstName)) {
			return false;
		}
		if (lastName == null) {
			if (user.lastName != null) {
				return false;
			}
		} else if (!lastName.equals(user.lastName)) {
			return false;
		}
		if (discont != user.discont) {
			return false;
		}
		if (dateOfBirth == null) {
			if (user.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(user.dateOfBirth)) {
			return false;
		}
		return status == user.status && role == user.role;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[User : id = ").append(userId);
		builder.append(", email = ").append(email);
		builder.append(", first name = ").append(firstName);
		builder.append(", last name = ").append(lastName);
		builder.append(", discont = ").append(discont);
		builder.append(", phone number = ").append(phoneNumber);
		builder.append(", date of birth = ").append(dateOfBirth);
		builder.append(", status = ").append(status.name().toLowerCase());
		builder.append(", role = ").append(role.name().toLowerCase());
		builder.append("]");
		return builder.toString();
	}

	public static class Builder {
		private User user;

		public Builder() {
			user = new User();
		}

		public Builder setUserId(long userId) {
			user.setUserId(userId);
			return this;
		}

		public Builder setEmail(String email) {
			user.setEmail(email);
			return this;
		}

		public Builder setFirstName(String firstName) {
			user.setFirstName(firstName);
			return this;
		}

		public Builder setLastName(String lastName) {
			user.setLastName(lastName);
			return this;
		}

		public Builder setDiscont(int discont) {
			user.setDiscont(discont);
			return this;
		}

		public Builder setPhoneNumber(String phoneNumber) {
			user.setPhoneNumber(phoneNumber);
			return this;
		}

		public Builder setDateOfBirth(LocalDate dateOfBirth) {
			user.setDateOfBirth(dateOfBirth);
			return this;
		}

		public Builder setStatus(UserStatus status) {
			user.setStatus(status);
			return this;
		}

		public Builder setRole(UserRole role) {
			user.setRole(role);
			return this;
		}

		public User build() {
			return user;
		}
	}
}
