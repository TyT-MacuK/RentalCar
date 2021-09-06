package by.training.carrent.model.entity;

import java.math.BigDecimal;

public class Car extends AbstractEntity {
	public enum CarClass {
		ECONOMY, STANDARD, PREMIUM, MINIVAN
	}

	public enum CarTransmission {
		AUTOMATIC, MANUAL
	}

	public enum CarManufacturer {
		VOLKSWAGEN, SKODA, RENAULT, TOYOTA, AUDI, MAZDA, MERCEDES
	}

	public enum CarStatus {
		RENTED, FREE, CAR_IS_SERVICED, IMPOSSIBLE_TO_RENT
	}

	private long carId;
	private String model;
	private int discount;
	private int year;
	private boolean conditioner;
	private BigDecimal cost;
	private CarClass carClass;
	private CarTransmission carTransmission;
	private CarManufacturer carManufacturer;
	private CarStatus carStatus;

	public Car() {
	}

	public Car(long carId, String model, int discount, int year, boolean conditioner, BigDecimal cost,
			CarClass carClass, CarTransmission carTransmission, CarManufacturer carManufacturer, CarStatus carStatus) {
		this.carId = carId;
		this.model = model;
		this.discount = discount;
		this.year = year;
		this.conditioner = conditioner;
		this.cost = cost;
		this.carClass = carClass;
		this.carTransmission = carTransmission;
		this.carManufacturer = carManufacturer;
		this.carStatus = carStatus;
	}

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isConditioner() {
		return conditioner;
	}

	public void setConditioner(boolean conditioner) {
		this.conditioner = conditioner;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public CarClass getCarClass() {
		return carClass;
	}

	public void setCarClass(CarClass carClass) {
		this.carClass = carClass;
	}

	public CarTransmission getCarTransmission() {
		return carTransmission;
	}

	public void setCarTransmission(CarTransmission carTransmission) {
		this.carTransmission = carTransmission;
	}

	public CarManufacturer getCarManufacturer() {
		return carManufacturer;
	}

	public void setCarManufacturer(CarManufacturer carManufacturer) {
		this.carManufacturer = carManufacturer;
	}

	public CarStatus getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(CarStatus carStatus) {
		this.carStatus = carStatus;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + (carId != 0 ? Long.hashCode(carId) : 0);
		result = prime * result + (model != null ? model.hashCode() : 0);
		result = prime * result + discount;
		result = prime * result + year;
		result = prime * result + Boolean.hashCode(conditioner);
		result = prime * result + (cost != null ? cost.hashCode() : 0);
		result = prime * result + (carClass != null ? carClass.hashCode() : 0);
		result = prime * result + (carTransmission != null ? carTransmission.hashCode() : 0);
		result = prime * result + (carManufacturer != null ? carManufacturer.hashCode() : 0);
		result = prime * result + (carStatus != null ? carStatus.hashCode() : 0);
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
		return carClass == car.carClass && carTransmission == this.carTransmission
				&& carManufacturer == car.carManufacturer && carStatus == car.carStatus;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Car : id = ").append(carId);
		builder.append(", manufacturer = ").append(carManufacturer.name());
		builder.append(", model = ").append(model);
		builder.append(", discount = ").append(discount);
		builder.append(", year = ").append(year);
		builder.append(", conditioner = ").append(conditioner);
		builder.append(", cost = ").append(cost);
		builder.append(", class = ").append(carClass.name().toLowerCase());
		builder.append(", transmission = ").append(carTransmission.name().toLowerCase());
		builder.append(", status = ").append(carStatus.name().toLowerCase());
		builder.append("]");
		return builder.toString();
	}

	public static class Builder {
		private Car car;

		public Builder() {
			car = new Car();
		}

		public Builder setCarId(long carId) {
			car.setCarId(carId);
			return this;
		}

		public Builder setModel(String model) {
			car.setModel(model);
			return this;
		}

		public Builder setDiscount(int discount) {
			car.setDiscount(discount);
			return this;
		}

		public Builder setYear(int year) {
			car.setYear(year);
			return this;
		}

		public Builder setConditioner(boolean conditioner) {
			car.setConditioner(conditioner);
			return this;
		}

		public Builder setCost(BigDecimal cost) {
			car.setCost(cost);
			return this;
		}

		public Builder setCarClass(CarClass carClass) {
			car.setCarClass(carClass);
			return this;
		}
		
		public Builder setCarTransmission(CarTransmission carTransmission) {
			car.setCarTransmission(carTransmission);
			return this;
		}

		public Builder setCarManufacturer(CarManufacturer carManufacturer) {
			car.setCarManufacturer(carManufacturer);
			return this;
		}

		public Builder setCarStatus(CarStatus carStatus) {
			car.setCarStatus(carStatus);
			return this;
		}

		public Car build() {
			return car;
		}
	}
}
