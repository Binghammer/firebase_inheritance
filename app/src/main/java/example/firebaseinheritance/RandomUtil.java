package example.firebaseinheritance;

import android.graphics.Color;

import java.util.Random;

public class RandomUtil {

	private final Random random;

	public RandomUtil() {
		this.random = new Random();
	}

	public int color() {
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		return Color.argb(255, red, green, blue);
	}

	public double price() {
		return random.nextInt(90000);
	}

	public VehicleType vehicleType() {
		return VehicleType.values()[random.nextInt(VehicleType.values().length)];
	}

	public String motorSize() {
		return motorSizes[random.nextInt(motorSizes.length)];
	}

	private static final String[] motorSizes = new String[]{
			"150cc", "250cc", "450cc", "800cc", "1800cc",
	};

	public int number(int i) {
		return random.nextInt(i) + 1;
	}

	public boolean bool() {
		return random.nextBoolean();
	}
}
