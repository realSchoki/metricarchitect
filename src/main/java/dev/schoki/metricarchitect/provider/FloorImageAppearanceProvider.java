package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.Device;
import style.Appearance;
import style.StyleFactory;

public class FloorImageAppearanceProvider implements StyleAppearanceProvider<Device> {

	@Override
	public Appearance getAppearance(Device sensor, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		appearance.setImagePath(sensor.getSensor().getIcon());
		return appearance;
	}
}
