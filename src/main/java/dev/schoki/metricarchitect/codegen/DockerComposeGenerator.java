package dev.schoki.metricarchitect.codegen;

import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import dev.schoki.metricarchitect.codegen.DockerComposeGeneratorTemplate;

public class DockerComposeGenerator {
	public void generate(Boolean generateStubSensor, IPath targetDir, IProgressMonitor monitor) {
		String code = DockerComposeGeneratorTemplate.generate(generateStubSensor);
		File outputFile = targetDir.append("docker-compose.yml").toFile();
		Utils.writeFile(outputFile, code);
	}
}