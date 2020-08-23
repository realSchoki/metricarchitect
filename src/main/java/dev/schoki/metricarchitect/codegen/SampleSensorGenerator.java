package dev.schoki.metricarchitect.codegen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public class SampleSensorGenerator {
	public void generate(IPath targetDir, IProgressMonitor monitor) throws IOException {
		// String code = DockerComposeGeneratorTemplate.generate(generateStubSensor);
		targetDir.append("/sample-sensor/").toFile().mkdir();
		Path copied = targetDir.append("/sample-sensor/").append("package.json").toFile().toPath();
		Path originalPath = Paths.get("src/main/resources/package.json");
		Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
	}
}