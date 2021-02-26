package dev.schoki.metricarchitect.codegen;


import dev.schoki.metricarchitect.model.project.projectmodel.ProjectModel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;


import de.jabc.cinco.meta.plugin.generator.runtime.IGenerator;

public class ProjectGenerator implements IGenerator<ProjectModel> {
	@Override
	public void generate(final ProjectModel model, final IPath targetDir, final IProgressMonitor monitor){
		
		try {
			Files.walk(targetDir.toFile().toPath())
			.skip(1)
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach(File::delete);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		PrometheusConfigGenerator gPrometheus = new PrometheusConfigGenerator();
		gPrometheus.generate( 
			Stream.concat(
					model.getGrafanaModels().stream()
					.flatMap( m -> m.getGrafanaModels().getGraphQueryForGroups().stream())
					.flatMap( g -> g.getDeviceGroup().getDevicePredecessors().stream()),
					model.getGrafanaModels().stream()
					.flatMap( m -> m.getGrafanaModels().getGraphQueryForDevices().stream())
					.map( g -> g.getDevice())
		).collect(Collectors.toList()), targetDir, monitor);
		
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
		gGrafana.generate(model, model.getGrafanaModels().stream()
				.flatMap( f -> f.getGrafanaModels().getDashboards().stream())
				.collect(Collectors.toList()), targetDir, monitor);
		 
		
	}
}