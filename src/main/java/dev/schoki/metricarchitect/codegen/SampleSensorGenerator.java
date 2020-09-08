package dev.schoki.metricarchitect.codegen;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

public class SampleSensorGenerator {
	//private static Stream<Path> c;

	public static void generate(IPath targetDir, IProgressMonitor monitor) throws IOException, URISyntaxException {
		IPath dir = targetDir.append("/sample-sensor/");
		dir.toFile().mkdir();
		
		org.osgi.framework.Bundle b = org.eclipse.core.runtime.Platform.getBundle("dev.schoki.metricarchitect");
		java.net.URL url =  org.eclipse.core.runtime.FileLocator.find(b, new org.eclipse.core.runtime.Path("src/main/resources/sample-sensor"), null);
		Path path = new Path(org.eclipse.core.runtime.FileLocator.toFileURL(url).getPath());
		
		Files.list(path.toFile().toPath()).forEach( x -> {
			//java.net.URL u =  org.eclipse.core.runtime.FileLocator.find(b, new org.eclipse.core.runtime.Path(x.toFile().getPath()), null);
			try {
				//p = new Path(org.eclipse.core.runtime.FileLocator.toFileURL(u).getPath());
				Files.copy(x, dir.append(x.toFile().getName()).toFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
}