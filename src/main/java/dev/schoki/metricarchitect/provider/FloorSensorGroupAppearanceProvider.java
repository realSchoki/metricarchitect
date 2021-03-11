package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.SensorGroup;
import style.Appearance;
import style.StyleFactory;

public class FloorSensorGroupAppearanceProvider implements StyleAppearanceProvider<SensorGroup> {

	@Override
	public Appearance getAppearance(SensorGroup deviceGroup, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		appearance.setLineWidth(2);
		appearance.setImagePath(deviceGroup.getIcon());
		return appearance;
	}
}
