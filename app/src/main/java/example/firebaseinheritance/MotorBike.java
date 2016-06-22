package example.firebaseinheritance;

public class MotorBike extends Vehicle {

	private String motorSize;

	public MotorBike() {
	}

	@Override
	public int getWheels() {
		return 2;
	}

	public String getMotorSize() {
		return motorSize;
	}

	public void setMotorSize(String motorSize) {
		this.motorSize = motorSize;
	}

	@Override
	public String toString() {
		return super.toString() + ", motorSize: " + motorSize;
	}
}
