package example.firebaseinheritance;

import com.google.firebase.database.Exclude;

import java.text.NumberFormat;

public class Vehicle {

	public String vin;
	public int wheels;
	public int color;
	public double price;
	public String type;

	public Vehicle() { }

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getWheels() {
		return wheels;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Exclude
	public VehicleType getVehicleType() {
		return VehicleType.valueOf(type);
	}

	@Exclude
	public void setVehicleType(VehicleType vehicleType) {
		this.type = vehicleType.name();
	}

	@Override
	public String toString() {
		return "VIN = \'" + vin + "\', " +
			   "wheels: " + wheels + ", " +
			   "color: " + color + ", " +
			   "price: " + NumberFormat.getCurrencyInstance().format(price) + ", " +
			   "type: " + type;
	}
}
