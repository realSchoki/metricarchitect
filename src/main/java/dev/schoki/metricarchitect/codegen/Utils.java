package dev.schoki.metricarchitect.codegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;

import dev.schoki.metricarchitect.model.floor.floormodel.Device;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryForDevice;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryForGroup;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryToPanel;

public class Utils {
	public static void writeFile(File f, CharSequence code) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
			writer.append(code);
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String randomString(int length) {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    Random random = new Random();
	 
	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(length)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    return generatedString;
	}
	
	public static String graphToString(GraphQueryToPanel g) {
		EList<Device> f = ECollections.emptyEList();
		if(g.getSourceElement() instanceof GraphQueryForGroup) {
			f = ((GraphQueryForGroup) g.getSourceElement()).getDeviceGroup().getDevicePredecessors();
		} else if ( g.getSourceElement() instanceof GraphQueryForDevice) {
			f = ECollections.asEList(((GraphQueryForDevice) g.getSourceElement()).getDevice());
		} else {
			throw new RuntimeException("Unknown node type: " + g.getSourceElement().getClass().toString());
		}
		String jobs = String.join("|",   f.stream().map(s -> s.getName()).collect(Collectors.toList()));
		String expr = String.format("%s{job=~\\\"%s\\\"}", g.getFilter(), jobs, g.getTimespan(), g.getTimeresolution());
		if(g.getTimespan() != null && !g.getTimespan().isEmpty()) {
			if(g.getTimeresolution() != null && !g.getTimeresolution().isEmpty()) {
				expr = String.format("%s[%s:%s]", expr, g.getTimespan(), g.getTimeresolution());
			} else {
				expr = String.format("%s[%s]", expr, g.getTimespan());
			}
		}
		if(g.getOffset() != null && !g.getOffset().isEmpty()) {
			expr = String.format("%s offset %s", expr, g.getOffset());
		}
		if(g.getFunction() != null && !g.getFunction().getName().equals("none")) {
			expr = String.format("%s(%s)", g.getFunction(), expr);
		}
		return expr;
	}
	

	
	public static <T> Predicate<T> distinctByKey(
	    Function<? super T, ?> keyExtractor) {
	  
	    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
	    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
	}
}
