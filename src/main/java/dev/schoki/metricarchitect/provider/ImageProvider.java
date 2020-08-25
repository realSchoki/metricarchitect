package dev.schoki.metricarchitect.provider;

import de.jabc.cinco.meta.core.ge.style.generator.runtime.appearance.StyleAppearanceProvider;
import de.jabc.cinco.meta.core.ge.style.services.StyleGrammarAccess.AppearanceElements;
import de.jabc.cinco.meta.core.utils.CincoUtil;
import de.jabc.cinco.meta.core.utils.MGLUtil;
import dev.schoki.metricarchitect.model.floor.floormodel.Sensor;
import mgl.ModelElement;
import mgl.Node;
import style.Appearance;
import style.NodeStyle;
import style.Style;

public class ImageProvider implements StyleAppearanceProvider<Sensor> {

	@Override
	public Appearance getAppearance(Sensor arg0, String arg1) {
		NodeStyle style = CincoUtil.getStyleForNode((Node) arg0, CincoUtil.getStyles(MGLUtil.getGraphModel((ModelElement) arg0 )));
		Appearance app = new SensorAppearance();
		return null;
		
	}

}
