package dev.schoki.metricarchitect.codegen;


import dev.schoki.metricarchitect.codegen.PrometheusConfigGeneratorTemplate;
import dev.schoki.metricarchitect.model.floor.floormodel.Sensor;
import dev.schoki.metricarchitect.model.project.projectmodel.Floor;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

public class PrometheusConfigGenerator {
		public void generate(EList<Floor> model,IPath targetDir, IProgressMonitor monitor){
			
			List<Sensor> sensors = model.stream().flatMap( m -> m.getFloor().getSensors().stream()).collect(Collectors.toList());
			
			String code = PrometheusConfigGeneratorTemplate.generate(sensors, true);
			File outputFile = targetDir.append("prometheus.yml").toFile();
			Utils.writeFile(outputFile, code);
		}

}