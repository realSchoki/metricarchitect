package dev.schoki.metricarchitect.provider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.FloorImage;
import style.Appearance;
import style.StyleFactory;

public class FloorBackgroundImageAppearanceProvider implements StyleAppearanceProvider<FloorImage> {

	@Override
	public Appearance getAppearance(FloorImage floorImage, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		String platformString = floorImage.eResource().getURI().toPlatformString(true);
		IFile dummyFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString));
		if(dummyFile != null) {
			appearance.setImagePath(dummyFile.getProject().getFile(floorImage.getImage()).getLocation().toString());
		}
		return appearance;
	}
}
