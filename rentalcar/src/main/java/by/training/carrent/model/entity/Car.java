package by.training.carrent.model.entity;

import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 */
public class Car extends AbstractEntity {

	/**
	 * The Enum CarTransmission.
	 */
	public enum CarTransmission {
		AUTOMATIC, 
		MANUAL
	}

	/**
	 * The Enum CarManufacturer.
	 */
	public enum CarManufacturer {
		VOLKSWAGEN, 
		SKODA, 
		RENAULT,  
		MAZDA, 
		MERCEDES
	}

	/**
	 * The Enum CarStatus.
	 */
	public enum CarStatus {
		BOOKED,  
		FREE, 
		CAR_IS_SERVICED, 
		IMPOSSIBLE_TO_RENT
	}

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
	 * Gets the model.
	 *
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Gets the discount.
	 *
	 * @return the discount
	 */
	public int getDiscount() {
		return discount;
	}

	/**
	 * Sets the discount.
	 *
	 * @param discount the new discount
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Checks if is of conditioner.
	 *
	 * @return true, if is conditioner presence
	 */
	public boolean isConditioner() {
		return conditioner;
	}

	/**
	 * Sets the conditioner.
	 *
	 * @param conditioner the new conditioner
	 */
	public void setConditioner(boolean conditioner) {
		this.conditioner = conditioner;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * Sets the cost.
	 *
	 * @param cost the new cost
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * Gets the image url.
	 *
	 * @return the image url
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the image url.
	 *
	 * @param imageUrl the new image url
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Gets the car transmission.
	 *
	 * @return the car transmission
	 */
	public CarTransmission getCarTransmission() {
		return carTransmission;
	}

	/**
	 * Sets the car transmission.
	 *
	 * @param carTransmission the new car transmission
	 */
	public void setCarTransmission(CarTransmission carTransmission) {
		this.carTransmission = carTransmission;
	}

	/**
	 * Gets the car manufacturer.
	 *
	 * @return the car manufacturer
	 */
	public CarManufacturer getCarManufacturer() {
		return carManufacturer;
	}

	/**
	 * Sets the car manufacturer.
	 *
	 * @param carManufacturer the new car manufacturer
	 */
	public void setCarManufacturer(CarManufacturer carManufacturer) {
		this.carManufacturer = carManufacturer;
	}

	/**
	 * Gets the car status.
	 *
	 * @return the car status
	 */
	public CarStatus getCarStatus() {
		return carStatus;
	}

	/**
	 * Sets the car status.
	 *
	 * @param carStatus the new car status
	 */
	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
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
		return carTransmission == car.carTransmission
				&& carManufacturer == car.carManufacturer && carStatus == car.carStatus;
	}

	/**
	 * To string.
	 *
	 * @return the string
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
		 * Sets the car id.
		 *
		 * @param carId the car id
		 * @return the builder
		 */
		public Builder setCarId(long carId) {
			car.setCarId(carId);
			return this;
		}

		/**
		 * Sets the model.
		 *
		 * @param model the model
		 * @return the builder
		 */
		public Builder setModel(String model) {
			car.setModel(model);
			return this;
		}

		/**
		 * Sets the discount.
		 *
		 * @param discount the discount
		 * @return the builder
		 */
		public Builder setDiscount(int discount) {
			car.setDiscount(discount);
			return this;
		}

		/**
		 * Sets the year.
		 *
		 * @param year the year
		 * @return the builder
		 */
		public Builder setYear(int year) {
			car.setYear(year);
			return this;
		}

		/**
		 * Sets the conditioner.
		 *
		 * @param conditioner the conditioner
		 * @return the builder
		 */
		public Builder setConditioner(boolean conditioner) {
			car.setConditioner(conditioner);
			return this;
		}

		/**
		 * Sets the cost.
		 *
		 * @param cost the cost
		 * @return the builder
		 */
		public Builder setCost(BigDecimal cost) {
			car.setCost(cost);
			return this;
		}
		
		/**
		 * Sets the image url.
		 *
		 * @param imageUrl the image url
		 * @return the builder
		 */
		public Builder setImageUrl(String imageUrl) {
			car.setImageUrl(imageUrl);
			return this;
		}

		/**
		 * Sets the car transmission.
		 *
		 * @param carTransmission the car transmission
		 * @return the builder
		 */
		public Builder setCarTransmission(CarTransmission carTransmission) {
			car.setCarTransmission(carTransmission);
			return this;
		}

		/**
		 * Sets the car manufacturer.
		 *
		 * @param carManufacturer the car manufacturer
		 * @return the builder
		 */
		public Builder setCarManufacturer(CarManufacturer carManufacturer) {
			car.setCarManufacturer(carManufacturer);
			return this;
		}

		/**
		 * Sets the car status.
		 *
		 * @param carStatus the car status
		 * @return the builder
		 */
		public Builder setCarStatus(CarStatus carStatus) {
			car.setCarStatus(carStatus);
			return this;
		}

		/**
		 * Builds the car.
		 *
		 * @return the car
		 */
		public Car build() {
			return car;
		}
	}
}