package dev.schoki.metricarchitect.codegen;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.Bundle;
import com.google.common.base.Charsets;

import de.jabc.cinco.meta.core.utils.CincoUtil;

public class SampleSensorGenerator {
	private static Stream<Path> c;

	public static void generate(IPath targetDir, IProgressMonitor monitor) throws IOException, URISyntaxException {
		// String code = DockerComposeGeneratorTemplate.generate(generateStubSensor);
		IPath dir = targetDir.append("/sample-sensor/");
		dir.toFile().mkdir();
		
        Bundle bundle = Platform.getBundle("dev.schoki.metricarchitect");
        
        URL fileURL = bundle.getEntry("./");
        
        c = Files.list(Paths.get(fileURL.getPath()));
		
		c.forEach( p -> System.out.println(p.toString()));
		Files.list(Paths.get("src/main/resources/sample-sensor")).forEach( p -> {
			//Path copied = dir.append("package.json").toFile().toPath();
			//Path originalPath = Paths.get("src/main/resources/package.json");
			System.out.println(p.toString());
			try {
				Files.copy(p, dir.append((IPath) p.getFileName()).toFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
	  private static File[] getResourceFolderFiles (String folder) {
		    ClassLoader loader = Thread.currentThread().getContextClassLoader();
		    URL url = loader.getResource(folder);
		    String path = url.getPath();
		    return new File(path).listFiles();
		  }
}