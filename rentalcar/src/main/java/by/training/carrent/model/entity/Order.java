package by.training.carrent.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
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

	/** The order id. */
	private long orderId;

	/** The price. */
	private BigDecimal price;

	/** The pick up date. */
	private LocalDateTime pickUpDate;

	/** The return date. */
	private LocalDateTime returnDate;

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
	public Order(long orderId, BigDecimal price, LocalDateTime pickUpDate, LocalDateTime returnDate, OrderStatus status,
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
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * Gets the pick up date.
	 *
	 * @return the pick up date
	 */
	public LocalDateTime getPickUpDate() {
		return pickUpDate;
	}

	/**
	 * Sets the pick up date.
	 *
	 * @param pickUpDate the new pick up date
	 */
	public void setPickUpDate(LocalDateTime pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	/**
	 * Gets the return date.
	 *
	 * @return the return date
	 */
	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	/**
	 * Sets the return date.
	 *
	 * @param returnDate the new return date
	 */
	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * Gets the order status.
	 *
	 * @return the order status
	 */
	public OrderStatus getOrderStatus() {
		return status;
	}

	/**
	 * Sets the order status.
	 *
	 * @param status the new order status
	 */
	public void setOrderStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * Gets the car id.
	 *
	 * @return the car id
	 */
	public long getCarId() {
		return carId;
	}

	/**
	 * Sets the car id.
	 *
	 * @param carId the new car id
	 */
	public void setCarId(long carId) {
		this.carId = carId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
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
	 * Equals.
	 *
	 * @param object the object
	 * @return true, if successful
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
	 * To string.
	 *
	 * @return the string
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
		 * Sets the order id.
		 *
		 * @param orderId the order id
		 * @return the builder
		 */
		public Builder setOrderId(long orderId) {
			order.setOrderId(orderId);
			return this;
		}

		/**
		 * Sets the price.
		 *
		 * @param price the price
		 * @return the builder
		 */
		public Builder setPrice(BigDecimal price) {
			order.setPrice(price);
			return this;
		}

		/**
		 * Sets the pick up date.
		 *
		 * @param pickUpDate the pick up date
		 * @return the builder
		 */
		public Builder setPickUpDate(LocalDateTime pickUpDate) {
			order.setPickUpDate(pickUpDate);
			return this;
		}

		/**
		 * Sets the return date.
		 *
		 * @param returnDate the return date
		 * @return the builder
		 */
		public Builder setReturnDate(LocalDateTime returnDate) {
			order.setReturnDate(returnDate);
			return this;
		}

		/**
		 * Sets the order status.
		 *
		 * @param status the status
		 * @return the builder
		 */
		public Builder setOrderStatus(OrderStatus status) {
			order.setOrderStatus(status);
			return this;
		}

		/**
		 * Sets the car id.
		 *
		 * @param carId the car id
		 * @return the builder
		 */
		public Builder setCarId(long carId) {
			order.setCarId(carId);
			return this;
		}

		/**
		 * Sets the user id.
		 *
		 * @param userId the user id
		 * @return the builder
		 */
		public Builder setUserId(long userId) {
			order.setUserId(userId);
			return this;
		}

		/**
		 * Builds the order.
		 *
		 * @return the order
		 */
		public Order build() {
			return order;
		}
	}
}
