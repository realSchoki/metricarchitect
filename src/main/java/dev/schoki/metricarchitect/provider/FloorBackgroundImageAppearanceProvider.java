package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.FloorImage;
import style.Appearance;
import style.StyleFactory;

public class FloorBackgroundImageAppearanceProvider implements StyleAppearanceProvider<FloorImage> {

	@Override
	public Appearance getAppearance(FloorImage floorImage, String element) {
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		appearance.setImagePath(floorImage.getImage());
		return appearance;
	}
}
