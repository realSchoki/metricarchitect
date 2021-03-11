package dev.schoki.metricarchitect.provider;

import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import com.google.common.base.Functions;

import de.jabc.cinco.meta.runtime.provider.CincoValuesProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.Sensor;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryForGroup;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryForSensor;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryToPanel;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Metric;

public class FilterValueProvider extends CincoValuesProvider<GraphQueryToPanel, String> {

	@Override
	public Map<String, String> getPossibleValues(GraphQueryToPanel arg0) {
		EList<Sensor> f = ECollections.emptyEList();
		if(arg0.getSourceElement() instanceof GraphQueryForGroup) {
			f = ((GraphQueryForGroup) arg0.getSourceElement()).getSensorGroup().getSensorPredecessors();
		} else if ( arg0.getSourceElement() instanceof GraphQueryForSensor) {
			f = ECollections.asEList(((GraphQueryForSensor) arg0.getSourceElement()).getSensor());
		} else {
			throw new RuntimeException("Unknown node type: " + arg0.getSourceElement().getClass().toString());
		}

		return f.stream()
		.flatMap( d -> d
			.getSensor()
			.getMetricPredecessors()
			.stream()
			.map(Metric::getLabel)
		)
		.distinct()
		.collect(Collectors.toMap(Functions.identity(), Functions.identity()));
	}
}
