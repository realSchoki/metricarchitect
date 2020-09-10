package dev.schoki.metricarchitect.provider;

import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Functions;

import de.jabc.cinco.meta.runtime.provider.CincoValuesProvider;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryPanel;
import dev.schoki.metricarchitect.model.sensor.sensormodel.SensorMetric;

public class FilterValueProvider extends CincoValuesProvider<GraphQueryPanel, String> {

	@Override
	public Map<String, String> getPossibleValues(GraphQueryPanel arg0) {
		return arg0.getSourceElement().getDeviceGroup().getIncoming().stream().flatMap( edge -> edge.getSourceElement()
					.getSensor()
					.getSensorMetricPredecessors()
					.stream()
					.map(SensorMetric::getLabel))
				.distinct()
				.collect(Collectors.toMap(Functions.identity(), Functions.identity()));
	}
}
