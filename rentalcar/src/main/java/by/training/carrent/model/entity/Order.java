package by.training.carrent.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends AbstractEntity {
	public enum OrderStatus {
		PAID, AWAITS_PAYMENT, DECLINED
	}

	private long orderId;
	private BigDecimal price;
	private LocalDateTime pickUpDate;
	private LocalDateTime returnDate;
	private OrderStatus status;
	private long carId;
	private long userId;

	public Order() {
	}

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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(LocalDateTime pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public OrderStatus getOrderStatus() {
		return status;
	}

	public void setOrderStatus(OrderStatus status) {
		this.status = status;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

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

	public static class Builder {
		private Order order;

		public Builder() {
			order = new Order();
		}

		public Builder setOrderId(long orderId) {
			order.setOrderId(orderId);
			return this;
		}

		public Builder setPrice(BigDecimal price) {
			order.setPrice(price);
			return this;
		}

		public Builder setPickUpDate(LocalDateTime pickUpDate) {
			order.setPickUpDate(pickUpDate);
			return this;
		}

		public Builder setReturnDate(LocalDateTime returnDate) {
			order.setReturnDate(returnDate);
			return this;
		}

		public Builder setOrderStatus(OrderStatus status) {
			order.setOrderStatus(status);
			return this;
		}

		public Builder setCarId(long carId) {
			order.setCarId(carId);
			return this;
		}

		public Builder setUserId(long userId) {
			order.setUserId(userId);
			return this;
		}

		public Order build() {
			return order;
		}
	}
}
