package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryForSensor;
import style.Appearance;
import style.StyleFactory;

public class GrafanaGraphQueryForSensorAppearanceProvider implements StyleAppearanceProvider<GraphQueryForSensor> {

	@Override
	public Appearance getAppearance(GraphQueryForSensor graphQuery, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		//appearance.setImagePath(graphQuery.getDevice().get);
		return appearance;
	}
}
