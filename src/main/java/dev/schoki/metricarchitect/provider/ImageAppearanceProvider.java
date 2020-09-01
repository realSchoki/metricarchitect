package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import dev.schoki.metricarchitect.model.floor.floormodel.Sensor;
import style.Appearance;
import style.StyleFactory;

public class ImageAppearanceProvider implements StyleAppearanceProvider<Sensor> {

	@Override
	public Appearance getAppearance(Sensor sensor, String element) {
		// element can be ignored here, as there are no named inner elements in the simpleArrow style
		System.out.println(element);
		Appearance appearance = StyleFactory.eINSTANCE.createAppearance();
		style.Color col = StyleFactory.eINSTANCE.createColor();
		col.setR(255);
		appearance.setLineWidth(2);
		appearance.setBackground(col);
		return appearance;
	}
}
