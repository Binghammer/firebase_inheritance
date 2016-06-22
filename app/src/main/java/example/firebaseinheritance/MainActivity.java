package example.firebaseinheritance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

	private VehicleProvider provider;
	private List<Vehicle> vehicles;

	private VehicleAdapter adapter;

	private LayoutInflater inflater;
	private Unbinder unbinder;

	@BindView(R.id.rv) RecyclerView rv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		inflater = LayoutInflater.from(this);
		unbinder = ButterKnife.bind(this);
		vehicles = new ArrayList<>();

		adapter = new VehicleAdapter();
		rv.setLayoutManager(new LinearLayoutManager(this));
		rv.setAdapter(adapter);

		provider = new VehicleProvider();
		provider.getVehicles().subscribe(this::onVehicleEvent);
	}

	private void onVehicleEvent(VehicleEvent event) {
		if (event.getVehicle() == null) return;

		if (event.isAdded()) {
			vehicles.add(event.getVehicle());

		} else {
			for (Vehicle vehicle : vehicles) {
				if (vehicle.getVin().equals(event.getVehicle().getVin())) {
					vehicles.remove(vehicle);
					break;
				}
			}
		}

		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbinder.unbind();
	}

	@OnClick(R.id.fab)
	public void onFabClicked() {
		provider.createNewVehicle();
	}

	public class VehicleViewHolder extends RecyclerView.ViewHolder {

		@BindView(R.id.tvTitle) TextView tvTitle;
		@BindView(R.id.tvDescription) TextView tvDescription;

		private Vehicle vehicle;

		public VehicleViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(view -> provider.removeVehicle(vehicle.getVin()));
		}

		public void bind(Vehicle vehicle) {
			this.vehicle = vehicle;
			tvTitle.setText(WordUtils.capitalizeFully(vehicle.getVehicleType().name().replace("_", " ")));
			tvDescription.setText(vehicle.toString());
		}
	}

	private class VehicleAdapter extends RecyclerView.Adapter<VehicleViewHolder> {

		private View inflate(ViewGroup vg) {
			return inflater.inflate(R.layout.view_holder_vehicle, vg, false);
		}

		@Override
		public VehicleViewHolder onCreateViewHolder(ViewGroup vg, int viewType) {
			return new VehicleViewHolder(inflate(vg));
		}

		@Override
		public void onBindViewHolder(VehicleViewHolder vh, int position) {
			vh.bind(vehicles.get(position));
		}

		@Override
		public int getItemCount() {
			return vehicles.size();
		}

	}
}
