package by.training.carrent.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The Class Order.
 */
public class Order extends AbstractEntity {
	/**
	 * The Enum OrderStatus.
	 */
	public enum OrderStatus {
		PAID, AWAITS_PAYMENT, DECLINED
	}

	/** The serial version UID */
	private static final long serialVersionUID = 3423591603615761393L;

	/** The order id. */
	private long orderId;

	/** The price. */
	private BigDecimal price;

	/** The pick up date. */
	private LocalDate pickUpDate;

	/** The return date. */
	private LocalDate returnDate;

	/** The status. */
	private OrderStatus status;

	/** The car id. */
	private long carId;

	/** The user id. */
	private long userId;

	/**
	 * Instantiates a new order.
	 */
	public Order() {
	}

	/**
	 * Instantiates a new order.
	 *
	 * @param orderId    the order id
	 * @param price      the price
	 * @param pickUpDate the pick up date
	 * @param returnDate the return date
	 * @param status     the status
	 * @param carId      the car id
	 * @param userId     the user id
	 */
	public Order(long orderId, BigDecimal price, LocalDate pickUpDate, LocalDate returnDate, OrderStatus status,
			long carId, long userId) {
		this.orderId = orderId;
		this.price = price;
		this.pickUpDate = pickUpDate;
		this.returnDate = returnDate;
		this.status = status;
		this.carId = carId;
		this.userId = userId;
	}

	/**
	 * Get the order id.
	 *
	 * @return the order id
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Set the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Get the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Set the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Get the pick up date.
	 *
	 * @return the pick up date
	 */
	public LocalDate getPickUpDate() {
		return pickUpDate;
	}

	/**
	 * Set the pick up date.
	 *
	 * @param pickUpDate the new pick up date
	 */
	public void setPickUpDate(LocalDate pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	/**
	 * Get the return date.
	 *
	 * @return the return date
	 */
	public LocalDate getReturnDate() {
		return returnDate;
	}

	/**
	 * Set the return date.
	 *
	 * @param returnDate the new return date
	 */
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * Get the order status.
	 *
	 * @return the order status
	 */
	public OrderStatus getOrderStatus() {
		return status;
	}

	/**
	 * Set the order status.
	 *
	 * @param status the new order status
	 */
	public void setOrderStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * Get the car id.
	 *
	 * @return the car id
	 */
	public long getCarId() {
		return carId;
	}

	/**
	 * Set the car id.
	 *
	 * @param carId the new car id
	 */
	public void setCarId(long carId) {
		this.carId = carId;
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
	 * Returns a hash code value for the object. This method is supported for the
	 * benefit of hash tables such as those provided by java.util.HashMap.
	 *
	 * @return a hash code value for this object.
	 */
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + Long.hashCode(orderId);
		result = prime * result + price.hashCode();
		result = prime * result + pickUpDate.hashCode();
		result = prime * result + returnDate.hashCode();
		result = prime * result + status.hashCode();
		result = prime * result + Long.hashCode(carId);
		result = prime * result + Long.hashCode(userId);
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
		Order order = (Order) object;
		if (orderId != order.orderId) {
			return false;
		}
		if (price == null) {
			if (order.price != null) {
				return false;
			}
		} else if (!price.equals(order.price)) {
			return false;
		}
		if (pickUpDate == null) {
			if (order.pickUpDate != null) {
				return false;
			}
		} else if (!pickUpDate.equals(order.pickUpDate)) {
			return false;
		}
		if (returnDate == null) {
			if (order.returnDate != null) {
				return false;
			}
		} else if (!returnDate.equals(order.returnDate)) {
			return false;
		}
		if (carId != order.carId) {
			return false;
		}
		if (userId != order.userId) {
			return false;
		}
		return status == order.status;
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
		builder.append("[Order : id = ").append(orderId);
		builder.append(", price = ").append(price);
		builder.append(", pick up date = ").append(pickUpDate);
		builder.append(", return date = ").append(returnDate);
		builder.append(", status = ").append(status.name().toLowerCase());
		builder.append(", car id = ").append(carId);
		builder.append(", user id = ").append(userId);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {

		/** The order. */
		private Order order;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			order = new Order();
		}

		/**
		 * Set the order id.
		 *
		 * @param orderId the order id
		 * @return the builder
		 */
		public Builder setOrderId(long orderId) {
			order.setOrderId(orderId);
			return this;
		}

		/**
		 * Set the price.
		 *
		 * @param price the price
		 * @return the builder
		 */
		public Builder setPrice(BigDecimal price) {
			order.setPrice(price);
			return this;
		}

		/**
		 * Set the pick up date.
		 *
		 * @param pickUpDate the pick up date
		 * @return the builder
		 */
		public Builder setPickUpDate(LocalDate pickUpDate) {
			order.setPickUpDate(pickUpDate);
			return this;
		}

		/**
		 * Set the return date.
		 *
		 * @param returnDate the return date
		 * @return the builder
		 */
		public Builder setReturnDate(LocalDate returnDate) {
			order.setReturnDate(returnDate);
			return this;
		}

		/**
		 * Set the order status.
		 *
		 * @param status the status
		 * @return the builder
		 */
		public Builder setOrderStatus(OrderStatus status) {
			order.setOrderStatus(status);
			return this;
		}

		/**
		 * Set the car id.
		 *
		 * @param carId the car id
		 * @return the builder
		 */
		public Builder setCarId(long carId) {
			order.setCarId(carId);
			return this;
		}

		/**
		 * Set the user id.
		 *
		 * @param userId the user id
		 * @return the builder
		 */
		public Builder setUserId(long userId) {
			order.setUserId(userId);
			return this;
		}

		/**
		 * Build the order.
		 *
		 * @return the order
		 */
		public Order build() {
			return order;
		}
	}
}
