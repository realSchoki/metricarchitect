package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryForGroup;
import style.Appearance;
import style.StyleFactory;

public class GrafanaGraphQueryForGroupAppearanceProvider implements StyleAppearanceProvider<GraphQueryForGroup> {

	@Override
	public Appearance getAppearance(GraphQueryForGroup graphQuery, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		appearance.setImagePath(graphQuery.getDeviceGroup().getIcon());
		return appearance;
	}
}
