package example.firebaseinheritance;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import rx.Observable;

public class VehicleProvider {
	private static final String TAG = VehicleProvider.class.getSimpleName();
	private RandomUtil random;

	public VehicleProvider() {
		random = new RandomUtil();
	}

	public void removeVehicle(String key) {
		getRef().child(key).removeValue();
	}

	private DatabaseReference getRef() {
		return FirebaseDatabase.getInstance().getReference();
	}

	private void err(Throwable e) {
		Log.e(TAG, "ERROR: " + e.getMessage());
		e.printStackTrace();
	}

	public Observable<VehicleEvent> getVehicles() {
		return Observable.create(subscriber -> {
			RxFirebase
					.asObservable(getRef())
					.subscribe(event -> {
						if (event.getSnapshot().getValue() != null) {
							try {
								final Vehicle vehicle = event.getSnapshot().getValue(Vehicle.class);
								final boolean isAdded = event.getFireEventType() != FireEventType.CHILD_REMOVED;
								subscriber.onNext(new VehicleEvent(isAdded, vehicle));
							} catch (Exception e) {
								err(e);
							}
						} else {
							subscriber.onNext(new VehicleEvent(false, null));
						}
					}, this::err);
		});
	}

	public void createNewVehicle() {
		final VehicleType type = random.vehicleType();
		switch (type) {
			case MOTOR_BIKE:
				newMotorBike();
				break;

			case CAR:
				newSedan();
				break;

			case TRUCK:
				newTruck();
				break;

			default:
		}
	}

	private <T extends Vehicle> void setBasics(T t) {
		t.setVin(getRef().push().getKey());
		t.setVehicleType(random.vehicleType());
		t.setColor(random.color());
		t.setPrice(random.price());
	}

	private void newSedan() {
		final Sedan sedan = new Sedan();
		setBasics(sedan);
		saveVehicle(sedan);
		saveVehicle(sedan);
	}

	private void newMotorBike() {
		final MotorBike bike = new MotorBike();
		setBasics(bike);
		bike.setMotorSize(random.motorSize());
		bike.setVehicleType(VehicleType.MOTOR_BIKE);
		saveVehicle(bike);
	}

	private void newTruck() {
		final Truck truck = new Truck();
		setBasics(truck);
		truck.setBedLength(random.number(8));
		truck.setDiesel(random.bool());
		truck.setFourWheelDrive(random.bool());
		truck.setVehicleType(VehicleType.TRUCK);
		saveVehicle(truck);
	}

	private void saveVehicle(Vehicle vehicle) {
		Log.d(TAG, "Saving vehicle:\n" + vehicle);

		getRef().child(vehicle.getVin()).setValue(vehicle);
	}
}
