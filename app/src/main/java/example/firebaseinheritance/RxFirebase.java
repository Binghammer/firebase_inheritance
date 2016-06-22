package example.firebaseinheritance;

import android.util.Log;

import com.google.firebase.FirebaseException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.Subscriptions;

import static example.firebaseinheritance.FireEventType.CHILD_ADDED;
import static example.firebaseinheritance.FireEventType.CHILD_CHANGED;
import static example.firebaseinheritance.FireEventType.CHILD_MOVED;
import static example.firebaseinheritance.FireEventType.CHILD_REMOVED;

public class RxFirebase {

	private static final String TAG = RxFirebase.class.getSimpleName();

	public static Observable<FirebaseChildEvent> asObservable(final Query ref) {
		if (ref == null) return Observable.empty();

		return Observable.create(new Observable.OnSubscribe<FirebaseChildEvent>() {
			@Override
			public void call(final Subscriber<? super FirebaseChildEvent> subscriber) {
				final ChildEventListener listener = ref.addChildEventListener(new ChildEventListener() {
					@Override
					public void onChildAdded(DataSnapshot dataSnapshot, String prevName) {
						subscriber.onNext(new FirebaseChildEvent(dataSnapshot, CHILD_ADDED));
					}

					@Override
					public void onChildChanged(DataSnapshot dataSnapshot, String prevName) {
						subscriber.onNext(new FirebaseChildEvent(dataSnapshot, CHILD_CHANGED));
					}

					@Override
					public void onChildRemoved(DataSnapshot dataSnapshot) {
						subscriber.onNext(new FirebaseChildEvent(dataSnapshot, CHILD_REMOVED));
					}

					@Override
					public void onChildMoved(DataSnapshot dataSnapshot, String prevName) {
						subscriber.onNext(new FirebaseChildEvent(dataSnapshot, CHILD_MOVED));
					}

					@Override
					public void onCancelled(DatabaseError error) {
						subscriber.onError(new FirebaseException(error.getMessage()));
					}
				});

				// When the subscription is cancelled, remove the listener
				subscriber.add(Subscriptions.create(() -> {
					Log.d(TAG, "Unsubscribe success for: " + ref.getRef());
					ref.removeEventListener(listener);
				}));
			}
		}).onBackpressureBuffer(1000, () -> Log.e(TAG, "BackpressureBuffer OVERFLOW!"));
	}
}
