package by.training.carrent.model.entity;

import java.time.LocalDate;

/**
 * The Class User.
 */
public class User extends AbstractEntity {
	/**
	 * The Enum UserStatus.
	 */
	public enum UserStatus {
		ACTIVE, BANNED, CONFIRMATION_AWAITING
	}

	/**
	 * The Enum UserRole.
	 */
	public enum UserRole {
		ADMIN, USER
	}

	/** The serial version UID */
	private static final long serialVersionUID = 7376896234790761285L;

	/** The user id. */
	private long userId;

	/** The email. */
	private String email;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The discount. */
	private int discount;

	/** The phone number. */
	private String phoneNumber;

	/** The date of birth. */
	private LocalDate dateOfBirth;

	/** The status. */
	private UserStatus status;

	/** The role. */
	private UserRole role;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param userId      the user id
	 * @param email       the email
	 * @param firstName   the first name
	 * @param lastName    the last name
	 * @param discount    the discount
	 * @param phoneNumber the phone number
	 * @param dateOfBirth the date of birth
	 * @param status      the status
	 * @param role        the role
	 */
	public User(long userId, String email, String firstName, String lastName, int discount, String phoneNumber,
			LocalDate dateOfBirth, UserStatus status, UserRole role) {
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.discount = discount;
		this.phoneNumber = phoneNumber;
		this.dateOfBirth = dateOfBirth;
		this.status = status;
		this.role = role;
	}

	/**
	 * Get the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Set the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Get the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the discount.
	 *
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * Set the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/**
	 * Get the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Set the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Get the date of birth.
	 *
	 * @return the date of birth
	 */
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Set the date of birth.
	 *
	 * @param dateOfBirth the new date of birth
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Get the status.
	 *
	 * @return the status
	 */
	public UserStatus getStatus() {
		return status;
	}

	/**
	 * Set the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	/**
	 * Get the role.
	 *
	 * @return the role
	 */
	public UserRole getRole() {
		return role;
	}

	/**
	 * Set the role.
	 *
	 * @param role the new role
	 */
	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * Returns a hash code value for the object. This method is supported for the
	 * benefit of hash tables such as those provided by java.util.HashMap.
	 *
	 * @return a hash code value for this object.
	 */
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(userId);
		result = prime * result + email.hashCode();
		result = prime * result + firstName.hashCode();
		result = prime * result + lastName.hashCode();
		result = prime * result + discount;
		result = prime * result + phoneNumber.hashCode();
		result = prime * result + dateOfBirth.hashCode();
		result = prime * result + status.hashCode();
		result = prime * result + role.hashCode();
		return result;
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 *
	 * @param object the reference object with which to compare.
	 * @return true if this object is the same as the compared object
	 */
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
		if (discount != user.discount) {
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

	/**
	 * Returns a string containing the characters in this sequence in the same order
	 * as this sequence. The length of the string will be the length of this
	 * sequence.
	 *
	 * @return a string consisting of exactly this sequence of characters
	 */
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[User : id = ").append(userId);
		builder.append(", email = ").append(email);
		builder.append(", first name = ").append(firstName);
		builder.append(", last name = ").append(lastName);
		builder.append(", discont = ").append(discount);
		builder.append(", phone number = ").append(phoneNumber);
		builder.append(", date of birth = ").append(dateOfBirth);
		builder.append(", status = ").append(status.name().toLowerCase());
		builder.append(", role = ").append(role.name().toLowerCase());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {

		/** The user. */
		private User user;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			user = new User();
		}

		/**
		 * Set the user id.
		 *
		 * @param userId the user id
		 * @return the builder
		 */
		public Builder setUserId(long userId) {
			user.setUserId(userId);
			return this;
		}

		/**
		 * Set the email.
		 *
		 * @param email the email
		 * @return the builder
		 */
		public Builder setEmail(String email) {
			user.setEmail(email);
			return this;
		}

		/**
		 * Set the first name.
		 *
		 * @param firstName the first name
		 * @return the builder
		 */
		public Builder setFirstName(String firstName) {
			user.setFirstName(firstName);
			return this;
		}

		/**
		 * Set the last name.
		 *
		 * @param lastName the last name
		 * @return the builder
		 */
		public Builder setLastName(String lastName) {
			user.setLastName(lastName);
			return this;
		}

		/**
		 * Set the discount.
		 *
		 * @param discont the discount
		 * @return the builder
		 */
		public Builder setDiscount(int discount) {
			user.setDiscount(discount);
			return this;
		}

		/**
		 * Set the phone number.
		 *
		 * @param phoneNumber the phone number
		 * @return the builder
		 */
		public Builder setPhoneNumber(String phoneNumber) {
			user.setPhoneNumber(phoneNumber);
			return this;
		}

		/**
		 * Set the date of birth.
		 *
		 * @param dateOfBirth the date of birth
		 * @return the builder
		 */
		public Builder setDateOfBirth(LocalDate dateOfBirth) {
			user.setDateOfBirth(dateOfBirth);
			return this;
		}

		/**
		 * Set the status.
		 *
		 * @param status the status
		 * @return the builder
		 */
		public Builder setStatus(UserStatus status) {
			user.setStatus(status);
			return this;
		}

		/**
		 * Set the role.
		 *
		 * @param role the role
		 * @return the builder
		 */
		public Builder setRole(UserRole role) {
			user.setRole(role);
			return this;
		}

		/**
		 * Build the user.
		 *
		 * @return the user
		 */
		public User build() {
			return user;
		}
	}
}
