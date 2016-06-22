package example.firebaseinheritance;

import com.google.firebase.database.DataSnapshot;

public class FirebaseChildEvent {

	private DataSnapshot snapshot;
	private FireEventType eventType;

	public FirebaseChildEvent(DataSnapshot snapshot, FireEventType eventType) {
		this.snapshot = snapshot;
		this.eventType = eventType;
	}

	public DataSnapshot getSnapshot() {
		return snapshot;
	}

	public FireEventType getFireEventType() {
		return eventType;
	}
}
