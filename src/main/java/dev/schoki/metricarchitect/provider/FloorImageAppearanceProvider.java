package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.SensorDeviceInstance;
import style.Appearance;
import style.StyleFactory;

public class FloorImageAppearanceProvider implements StyleAppearanceProvider<SensorDeviceInstance> {

	@Override
	public Appearance getAppearance(SensorDeviceInstance sensor, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		appearance.setImagePath(sensor.getSensor().getIcon());
		return appearance;
	}
}
