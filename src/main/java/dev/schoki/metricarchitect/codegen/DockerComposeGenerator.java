package dev.schoki.metricarchitect.codegen;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import dev.schoki.metricarchitect.codegen.DockerComposeGeneratorTemplate;

public class DockerComposeGenerator {
	public void generate(Boolean generateStubSensor, IPath targetDir, IProgressMonitor monitor) throws IOException, URISyntaxException {
		java.net.URL url = null;
		org.osgi.framework.Bundle b = org.eclipse.core.runtime.Platform.getBundle("dev.schoki.metricarchitect");
		url =  org.eclipse.core.runtime.FileLocator.find(b, new org.eclipse.core.runtime.Path("src/main/resources/sample-sensor/Dockerfile"), null);
		
		//Search for all used images in graphmodel and register them in the image provider
		//addImage("src/main/resources/icons/chalkboard-solid.png", url.toString());
		
		String code = DockerComposeGeneratorTemplate.generate(generateStubSensor);
		SampleSensorGenerator.generate(targetDir, monitor);
		File outputFile = targetDir.append("docker-compose.yml").toFile();
		Utils.writeFile(outputFile, code);
	}
}