package dev.schoki.metricarchitect.hooks.grafanamodel;

import java.util.List;
import java.util.stream.Collectors;

import de.jabc.cinco.meta.runtime.hook.CincoPostCreateHook;
import dev.schoki.metricarchitect.codegen.Utils;
import dev.schoki.metricarchitect.model.grafana.grafanamodel.Dashboard;

public class DashboardPostCreateSetUniqueId extends CincoPostCreateHook<Dashboard> {

	@Override
	public void postCreate(Dashboard arg0) {
		List<String> allExistingUIDs = arg0.getRootElement().getDashboards().stream()
			.map( p -> p.getInternal_id())
			.collect(Collectors.toList());
		
		String uid;
		while(allExistingUIDs.contains((uid = Utils.randomString(10)))) {}
		arg0.setInternal_id(uid);
	}

}
