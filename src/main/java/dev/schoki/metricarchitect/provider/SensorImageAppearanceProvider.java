package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Bolt;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Broadcast;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Fan;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Faucet;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Light;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Plug;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Sensor;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Wind;
import dev.schoki.metricarchitect.provider.SensorIconValueProvider.Icon;
import style.Appearance;
import style.StyleFactory;

public class SensorImageAppearanceProvider implements StyleAppearanceProvider<Sensor> {

	@Override
	public Appearance getAppearance(Sensor sensor, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		
		if (sensor instanceof Plug) {
			appearance.setImagePath(Icon.Plug.path);
		} else if (sensor instanceof Bolt) { 
			appearance.setImagePath(Icon.Bolt.path);
		} else if (sensor instanceof Broadcast) { 
			appearance.setImagePath(Icon.Broadcast.path);
		} else if (sensor instanceof Fan) { 
			appearance.setImagePath(Icon.Fan.path);
		} else if (sensor instanceof Faucet) { 
			appearance.setImagePath(Icon.Faucet.path);
		} else if (sensor instanceof Light) { 
			appearance.setImagePath(Icon.Light.path);
		} else if (sensor instanceof Wind) { 
			appearance.setImagePath(Icon.Wind.path);
		} else {
			appearance.setImagePath(sensor.getIcon());
		}
		
		return appearance;
	}
}
