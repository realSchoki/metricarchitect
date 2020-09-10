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

import org.eclipse.emf.common.util.EList;

import dev.schoki.metricarchitect.model.floor.floormodel.SensorDeviceInstance;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryPanel;

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
	
	public static String graphToString(GraphQueryPanel g) {
		EList<SensorDeviceInstance> f = g.getSourceElement().getDeviceGroup().getSensorDeviceInstancePredecessors();
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
		if(g.getFunction() != null && !g.getFunction().isEmpty()) {
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
