package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import de.jabc.cinco.meta.core.utils.CincoUtil;
import de.jabc.cinco.meta.core.utils.MGLUtil;
import dev.schoki.metricarchitect.model.floor.floormodel.Sensor;
import mgl.ModelElement;
import mgl.Node;
import style.Appearance;
import style.NodeStyle;
import style.impl.AppearanceImpl;

public class ImageAppearanceProvider implements StyleAppearanceProvider<Sensor> {

	@Override
	public Appearance getAppearance(Sensor arg0, String arg1) {
		Appearance a = new App();
		
		NodeStyle style = CincoUtil.getStyleForNode((Node) arg0, CincoUtil.getStyles(MGLUtil.getGraphModel((ModelElement) arg0 )));
		style.
		Appearance app = new SensorAppearance();
		return null;
		
	}

	private class App extends AppearanceImpl {
		
	}
}
