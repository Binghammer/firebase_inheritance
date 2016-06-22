package example.firebaseinheritance;

public class Truck extends Vehicle {
	private int bedLength;
	private boolean fourWheelDrive;
	private boolean diesel;

	public Truck() { }

	public int getBedLength() {
		return bedLength;
	}

	public void setBedLength(int bedLength) {
		this.bedLength = bedLength;
	}

	public boolean isFourWheelDrive() {
		return fourWheelDrive;
	}

	public void setFourWheelDrive(boolean fourWheelDrive) {
		this.fourWheelDrive = fourWheelDrive;
	}

	public boolean isDiesel() {
		return diesel;
	}

	public void setDiesel(boolean diesel) {
		this.diesel = diesel;
	}

	@Override
	public String toString() {
		return super.toString() + ", " +
			   "bedLength: " + bedLength + ", " +
			   "4x4: " + fourWheelDrive + ", " +
			   "diesel: " + diesel;
	}
}
