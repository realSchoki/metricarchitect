package dev.schoki.metricarchitect.codegen;


import de.jabc.cinco.meta.plugin.generator.runtime.IGenerator;
import dev.schoki.metricarchitect.model.project.projectmodel.ProjectModel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

public class ProjectGenerator implements IGenerator<ProjectModel> {
		public void generate(ProjectModel model,IPath targetDir, IProgressMonitor monitor){

			PrometheusConfigGenerator gPrometheus = new PrometheusConfigGenerator();
			gPrometheus.generate(model.getGrafanaModels().stream()
					.flatMap( m -> m.getGrafanaModels().getGraphs().stream())
					.flatMap( g -> g.getFunction().getSensorPredecessors().stream())
					.collect(Collectors.toList()), targetDir, monitor);
			
			DockerComposeGenerator gDocker = new DockerComposeGenerator();
			try {
				gDocker.generate(true, targetDir, monitor);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			GrafanaConfigGenerator gGrafana = new GrafanaConfigGenerator();
			gGrafana.generate(model.getGrafanaModels().stream()
					.flatMap( f -> f.getGrafanaModels().getDashboards().stream())
					.collect(Collectors.toList()), targetDir, monitor);
			
			
		}
}