package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.DeviceGroup;
import style.Appearance;
import style.StyleFactory;

public class FloorDeviceGroupAppearanceProvider implements StyleAppearanceProvider<DeviceGroup> {

	@Override
	public Appearance getAppearance(DeviceGroup deviceGroup, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		appearance.setLineWidth(2);
		appearance.setImagePath(deviceGroup.getIcon());
		return appearance;
	}
}
