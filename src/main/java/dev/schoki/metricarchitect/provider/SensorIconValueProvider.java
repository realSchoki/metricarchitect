package dev.schoki.metricarchitect.provider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import de.jabc.cinco.meta.runtime.provider.CincoValuesProvider;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Bolt;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Broadcast;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Fan;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Faucet;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Light;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Plug;
import dev.schoki.metricarchitect.model.sensor.sensormodel.SensorDevice;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Wind;

public class SensorIconValueProvider extends CincoValuesProvider<SensorDevice, String> {
	
	public static enum Icon {
		Bolt("src/main/resources/icons/bolt-solid.png"),
		Broadcast("src/main/resources/icons/broadcast-tower-solid.png"),
		Fan("src/main/resources/icons/fan-solid.png"),
		Faucet("src/main/resources/icons/faucet-solid.png"),
		Light("src/main/resources/icons/lightbulb-solid.png"),
		Plug("src/main/resources/icons/plug-solid.png"),
		Wind("src/main/resources/icons/wind-solid.png");
		
		public final String path;
		
		public String getPath() {
			return path;
		}
		
		private Icon(String path) {
			this.path = path;
		}
	}

	private static Map<String, String> icons = new HashMap<String, String>();

	static {
		Arrays.asList(Icon.values()).forEach( x -> icons.put(x.path, x.name()));
	}

	@Override
	public Map<String, String> getPossibleValues(SensorDevice sensor) {
		
		if (sensor instanceof Plug) {
			return Arrays.asList(Icon.Plug).stream().collect(Collectors.toMap(Icon::getPath, Icon::name));
		} else if (sensor instanceof Bolt) { 
			return Arrays.asList(Icon.Bolt).stream().collect(Collectors.toMap(Icon::getPath, Icon::name));
		} else if (sensor instanceof Broadcast) { 
			return Arrays.asList(Icon.Broadcast).stream().collect(Collectors.toMap(Icon::getPath, Icon::name));
		} else if (sensor instanceof Fan) { 
			return Arrays.asList(Icon.Fan).stream().collect(Collectors.toMap(Icon::getPath, Icon::name));
		} else if (sensor instanceof Faucet) { 
			return Arrays.asList(Icon.Faucet).stream().collect(Collectors.toMap(Icon::getPath, Icon::name));
		} else if (sensor instanceof Light) { 
			return Arrays.asList(Icon.Light).stream().collect(Collectors.toMap(Icon::getPath, Icon::name));
		} else if (sensor instanceof Wind) { 
			return Arrays.asList(Icon.Wind).stream().collect(Collectors.toMap(Icon::getPath, Icon::name));
			//return icons;
		} else {
			return icons;
		}
	}
}
