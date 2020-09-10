package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQuery;
import style.Appearance;
import style.StyleFactory;

public class GrafanaGraphQueryAppearanceProvider implements StyleAppearanceProvider<GraphQuery> {

	@Override
	public Appearance getAppearance(GraphQuery graphQuery, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		appearance.setImagePath(graphQuery.getDeviceGroup().getIcon());
		return appearance;
	}
}
