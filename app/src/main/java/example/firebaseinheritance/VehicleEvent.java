package example.firebaseinheritance;

public class VehicleEvent {
	private final boolean added;
	private final Vehicle vehicle;

	public VehicleEvent(boolean added, Vehicle vehicle) {
		this.added = added;
		this.vehicle = vehicle;
	}

	public boolean isAdded() {
		return added;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}
}
