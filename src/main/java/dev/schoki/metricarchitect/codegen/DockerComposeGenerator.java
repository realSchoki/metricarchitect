package dev.schoki.metricarchitect.codegen;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import dev.schoki.metricarchitect.codegen.DockerComposeGeneratorTemplate;

public class DockerComposeGenerator {
	public void generate(Boolean generateStubSensor, IPath targetDir, IProgressMonitor monitor) throws IOException, URISyntaxException {
		String code = DockerComposeGeneratorTemplate.generate(generateStubSensor);
		SampleSensorGenerator.generate(targetDir, monitor);
		File outputFile = targetDir.append("docker-compose.yml").toFile();
		Utils.writeFile(outputFile, code);
	}
}