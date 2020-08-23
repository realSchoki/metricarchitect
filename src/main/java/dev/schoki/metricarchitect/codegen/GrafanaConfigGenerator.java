package dev.schoki.metricarchitect.codegen;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import dev.schoki.metricarchitect.codegen.GrafanaConfigGeneratorTemplate;
import dev.schoki.metricarchitect.model.floor.floormodel.Dashboard;

public class GrafanaConfigGenerator{
		public void generate(List<Dashboard> dashboards, IPath targetDir, IProgressMonitor monitor){

			File datasource = targetDir.append("grafana_prometheus_connection.yml").toFile();
			Utils.writeFile(datasource, GrafanaConfigGeneratorTemplate.getDatasource());

			File dashboard_config = targetDir.append("dashboards_config.yml").toFile();
			Utils.writeFile(dashboard_config, GrafanaConfigGeneratorTemplate.getDashboards());


			targetDir.append("/dashboards/").toFile().mkdir();
			dashboards.forEach(d -> {
				String name = Utils.randomString(10);
				File graph = targetDir.append("/dashboards/").append(name + ".json").toFile();
				
				Utils.writeFile(graph, GrafanaConfigGeneratorTemplate.getGraph(d.getGraphPredecessors(), name));
			});
		}

}