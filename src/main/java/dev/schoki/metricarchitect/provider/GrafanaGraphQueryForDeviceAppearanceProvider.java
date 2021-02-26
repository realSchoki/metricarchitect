package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryForDevice;
import style.Appearance;
import style.StyleFactory;

public class GrafanaGraphQueryForDeviceAppearanceProvider implements StyleAppearanceProvider<GraphQueryForDevice> {

	@Override
	public Appearance getAppearance(GraphQueryForDevice graphQuery, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		//appearance.setImagePath(graphQuery.getDevice().get);
		return appearance;
	}
}
