package by.training.carrent.model.entity;

import java.math.BigDecimal;

/**
 * The Class Car.
 */
public class Car extends AbstractEntity {
	/**
	 * The Enum CarTransmission.
	 */
	public enum CarTransmission {
		AUTOMATIC, MANUAL
	}

	/**
	 * The Enum CarManufacturer.
	 */
	public enum CarManufacturer {
		VOLKSWAGEN, SKODA, RENAULT, MAZDA, MERCEDES
	}

	/**
	 * The Enum CarStatus.
	 */
	public enum CarStatus {
		BOOKED, FREE, CAR_IS_SERVICED, IMPOSSIBLE_TO_RENT
	}

	/** The serial version UID */
	private static final long serialVersionUID = -5585309648370863494L;

	/** The car id. */
	private long carId;

	/** The model. */
	private String model;

	/** The discount. */
	private int discount;

	/** The year. */
	private int year;

	/** The conditioner. */
	private boolean conditioner;

	/** The cost. */
	private BigDecimal cost;

	/** The image url. */
	private String imageUrl;

	/** The car transmission. */
	private CarTransmission carTransmission;

	/** The car manufacturer. */
	private CarManufacturer carManufacturer;

	/** The car status. */
	private CarStatus carStatus;

	/**
	 * Instantiates a new car.
	 */
	public Car() {
	}

	/**
	 * Instantiates a new car.
	 *
	 * @param carId           the car id
	 * @param model           the model
	 * @param discount        the discount
	 * @param year            the year
	 * @param conditioner     the presence of the conditioner
	 * @param cost            the cost
	 * @param imageUrl        the image url
	 * @param carClass        the car class
	 * @param carTransmission the car transmission
	 * @param carManufacturer the car manufacturer
	 * @param carStatus       the car status
	 */
	public Car(long carId, String model, int discount, int year, boolean conditioner, BigDecimal cost, String imageUrl,
			CarTransmission carTransmission, CarManufacturer carManufacturer, CarStatus carStatus) {
		this.carId = carId;
		this.model = model;
		this.discount = discount;
		this.year = year;
		this.conditioner = conditioner;
		this.cost = cost;
		this.imageUrl = imageUrl;
		this.carTransmission = carTransmission;
		this.carManufacturer = carManufacturer;
		this.carStatus = carStatus;
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
	 * Get the model.
	 *
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Set the model.
	 *
	 * @param model the new model
	 */
	public void setModel(String model) {
		this.model = model;
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
	 * Get the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Set the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Check if is of conditioner.
	 *
	 * @return true, if is conditioner presence
	 */
	public boolean isConditioner() {
		return conditioner;
	}

	/**
	 * Set the conditioner.
	 *
	 * @param conditioner the new conditioner
	 */
	public void setConditioner(boolean conditioner) {
		this.conditioner = conditioner;
	}

	/**
	 * Get the cost.
	 *
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * Set the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * Get the image url.
	 *
	 * @return the image url
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Set the image url.
	 *
	 * @param imageUrl the new image url
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Get the car transmission.
	 *
	 * @return the car transmission
	 */
	public CarTransmission getCarTransmission() {
		return carTransmission;
	}

	/**
	 * Set the car transmission.
	 *
	 * @param carTransmission the new car transmission
	 */
	public void setCarTransmission(CarTransmission carTransmission) {
		this.carTransmission = carTransmission;
	}

	/**
	 * Get the car manufacturer.
	 *
	 * @return the car manufacturer
	 */
	public CarManufacturer getCarManufacturer() {
		return carManufacturer;
	}

	/**
	 * Set the car manufacturer.
	 *
	 * @param carManufacturer the new car manufacturer
	 */
	public void setCarManufacturer(CarManufacturer carManufacturer) {
		this.carManufacturer = carManufacturer;
	}

	/**
	 * Get the car status.
	 *
	 * @return the car status
	 */
	public CarStatus getCarStatus() {
		return carStatus;
	}

	/**
	 * Set the car status.
	 *
	 * @param carStatus the new car status
	 */
	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
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
		result = prime * result + (carId != 0 ? Long.hashCode(carId) : 0);
		result = prime * result + (model != null ? model.hashCode() : 0);
		result = prime * result + discount;
		result = prime * result + year;
		result = prime * result + Boolean.hashCode(conditioner);
		result = prime * result + (cost != null ? cost.hashCode() : 0);
		result = prime * result + (imageUrl != null ? imageUrl.hashCode() : 0);
		result = prime * result + (carTransmission != null ? carTransmission.hashCode() : 0);
		result = prime * result + (carManufacturer != null ? carManufacturer.hashCode() : 0);
		result = prime * result + (carStatus != null ? carStatus.hashCode() : 0);
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
		Car car = (Car) object;
		if (carId != car.carId) {
			return false;
		}
		if (model == null) {
			if (car.model != null) {
				return false;
			}
		} else if (!model.equals(car.model)) {
			return false;
		}
		if (discount != car.discount) {
			return false;
		}
		if (year != car.year) {
			return false;
		}
		if (conditioner != car.conditioner) {
			return false;
		}
		if (cost == null) {
			if (car.cost != null) {
				return false;
			}
		} else if (!cost.equals(car.cost)) {
			return false;
		}
		if (imageUrl == null) {
			if (car.imageUrl != null) {
				return false;
			}
		} else if (!imageUrl.equals(car.imageUrl)) {
			return false;
		}
		return carTransmission == car.carTransmission && carManufacturer == car.carManufacturer
				&& carStatus == car.carStatus;
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
		builder.append("[Car : id = ").append(carId);
		builder.append(", manufacturer = ").append(carManufacturer.name());
		builder.append(", model = ").append(model);
		builder.append(", discount = ").append(discount);
		builder.append(", year = ").append(year);
		builder.append(", conditioner = ").append(conditioner);
		builder.append(", cost = ").append(cost);
		builder.append(", transmission = ").append(carTransmission.name().toLowerCase());
		builder.append(", status = ").append(carStatus.name().toLowerCase());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * The Class Builder.
	 */
	public static class Builder {

		/** The car. */
		private Car car;

		/**
		 * Instantiates a new builder.
		 */
		public Builder() {
			car = new Car();
		}

		/**
		 * Set the car id.
		 *
		 * @param carId the car id
		 * @return the builder
		 */
		public Builder setCarId(long carId) {
			car.setCarId(carId);
			return this;
		}

		/**
		 * Set the model.
		 *
		 * @param model the model
		 * @return the builder
		 */
		public Builder setModel(String model) {
			car.setModel(model);
			return this;
		}

		/**
		 * Set the discount.
		 *
		 * @param discount the discount
		 * @return the builder
		 */
		public Builder setDiscount(int discount) {
			car.setDiscount(discount);
			return this;
		}

		/**
		 * Set the year.
		 *
		 * @param year the year
		 * @return the builder
		 */
		public Builder setYear(int year) {
			car.setYear(year);
			return this;
		}

		/**
		 * Set the conditioner.
		 *
		 * @param conditioner the conditioner
		 * @return the builder
		 */
		public Builder setConditioner(boolean conditioner) {
			car.setConditioner(conditioner);
			return this;
		}

		/**
		 * Set the cost.
		 *
		 * @param cost the cost
		 * @return the builder
		 */
		public Builder setCost(BigDecimal cost) {
			car.setCost(cost);
			return this;
		}

		/**
		 * Set the image url.
		 *
		 * @param imageUrl the image url
		 * @return the builder
		 */
		public Builder setImageUrl(String imageUrl) {
			car.setImageUrl(imageUrl);
			return this;
		}

		/**
		 * Set the car transmission.
		 *
		 * @param carTransmission the car transmission
		 * @return the builder
		 */
		public Builder setCarTransmission(CarTransmission carTransmission) {
			car.setCarTransmission(carTransmission);
			return this;
		}

		/**
		 * Set the car manufacturer.
		 *
		 * @param carManufacturer the car manufacturer
		 * @return the builder
		 */
		public Builder setCarManufacturer(CarManufacturer carManufacturer) {
			car.setCarManufacturer(carManufacturer);
			return this;
		}

		/**
		 * Set the car status.
		 *
		 * @param carStatus the car status
		 * @return the builder
		 */
		public Builder setCarStatus(CarStatus carStatus) {
			car.setCarStatus(carStatus);
			return this;
		}

		/**
		 * Build the car.
		 *
		 * @return the car
		 */
		public Car build() {
			return car;
		}
	}
}