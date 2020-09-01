package dev.schoki.metricarchitect.codegen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;

import dev.schoki.metricarchitect.model.floor.floormodel.Function;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.Graph;

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
	
	public static String graphToString(Graph g) {
		Function f = g.getFunction();
		String jobs = String.join("|",   f.getSensorPredecessors().stream().map(s -> s.getName()).collect(Collectors.toList()));
		String expr = String.format("%s{job=~\\\"%s\\\"}", f.getFilter(), jobs, f.getTimespan(), f.getTimeresolution());
		if(f.getTimespan() != null && !f.getTimespan().isEmpty()) {
			if(f.getTimeresolution() != null && !f.getTimeresolution().isEmpty()) {
				expr = String.format("%s[%s:%s]", expr, f.getTimespan(), f.getTimeresolution());
			} else {
				expr = String.format("%s[%s]", expr, f.getTimespan());
			}
		}
		if(f.getOffset() != null && !f.getOffset().isEmpty()) {
			expr = String.format("%s offset %s", expr, f.getOffset());
		}
		if(f.getFunction() != null && !f.getFunction().isEmpty()) {
			expr = String.format("%s(%s)", f.getFunction(), expr);
		}
		return "";
	}
}
