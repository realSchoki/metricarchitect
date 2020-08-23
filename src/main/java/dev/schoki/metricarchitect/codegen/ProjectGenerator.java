package dev.schoki.metricarchitect.codegen;


import de.jabc.cinco.meta.plugin.generator.runtime.IGenerator;
import dev.schoki.metricarchitect.model.project.projectmodel.ProjectModel;

import java.util.stream.Collectors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public class ProjectGenerator implements IGenerator<ProjectModel> {
		public void generate(ProjectModel model,IPath targetDir, IProgressMonitor monitor){

			PrometheusConfigGenerator gPrometheus = new PrometheusConfigGenerator();
			gPrometheus.generate(model.getFloors(), targetDir, monitor);
			
			DockerComposeGenerator gDocker = new DockerComposeGenerator();
			gDocker.generate(true, targetDir, monitor);
			
			GrafanaConfigGenerator gGrafana = new GrafanaConfigGenerator();
			gGrafana.generate(model.getFloors().stream().flatMap( f -> f.getFloor().getDashboards().stream()).collect(Collectors.toList()), targetDir, monitor);
			
			
		}
}