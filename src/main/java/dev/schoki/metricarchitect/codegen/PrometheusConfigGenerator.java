package dev.schoki.metricarchitect.codegen;


import dev.schoki.metricarchitect.codegen.PrometheusConfigGeneratorTemplate;
import dev.schoki.metricarchitect.model.floor.floormodel.Sensor;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public class PrometheusConfigGenerator {
		public void generate(List<Sensor> sensordx,IPath targetDir, IProgressMonitor monitor){
			String code = PrometheusConfigGeneratorTemplate.generate(sensordx, true);
			File outputFile = targetDir.append("prometheus.yml").toFile();
			Utils.writeFile(outputFile, code);
		}

}