package dev.schoki.metricarchitect.provider;

import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Functions;

import de.jabc.cinco.meta.runtime.provider.CincoValuesProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.Function;
import dev.schoki.metricarchitect.model.sensor.sensormodel.Attribute;

public class ValueProvider extends CincoValuesProvider<Function, Attribute> {

	@Override
	public Map<Attribute, String> getPossibleValues(Function arg0) {
		return arg0.getIncoming().stream().flatMap( edge -> edge.getSourceElement()
				.getSensor()
				.getAttributePredecessors()
				.stream()
				.map( f -> f )).collect(Collectors.toMap(Functions.identity(), a -> a.getLabel()));
	}

}
