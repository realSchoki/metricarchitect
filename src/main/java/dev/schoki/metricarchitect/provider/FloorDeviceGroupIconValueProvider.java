package dev.schoki.metricarchitect.provider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.jabc.cinco.meta.runtime.provider.CincoValuesProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.DeviceGroup;
import dev.schoki.metricarchitect.provider.SensorIconValueProvider.Icon;

public class FloorDeviceGroupIconValueProvider extends CincoValuesProvider<DeviceGroup, String> {

		private static Map<String, String> icons = new HashMap<String, String>();

		static {
			Arrays.asList(Icon.values()).forEach( x -> icons.put(x.path, x.name()));
		}

		@Override
		public Map<String, String> getPossibleValues(DeviceGroup deviceGroup) {
			return icons;
		}
	}
