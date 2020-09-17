package dev.schoki.metricarchitect.codegen

import dev.schoki.metricarchitect.model.grafana.grafanamodel.Dashboard
import dev.schoki.metricarchitect.model.grafana.grafanamodel.Panel
import dev.schoki.metricarchitect.model.grafana.grafanamodel.GraphQueryPanel
import org.eclipse.emf.common.util.EList
import dev.schoki.metricarchitect.model.project.projectmodel.ProjectModel

class GrafanaConfigGeneratorTemplate {
	static var i = 0;
	
	def static String generate() {
	}
	
	def static String getDatasource() {
		var String template = '''
			# config file version
			apiVersion: 1
			
			# list of datasources to insert/update depending
			# what's available in the database
			datasources:
			  # <string, required> name of the datasource. Required
			  - name: Prometheus
			    # <string, required> datasource type. Required
			    type: prometheus
			    # <string, required> access mode. proxy or direct (Server or Browser in the UI). Required
			    access: proxy
			    # <int> org id. will default to orgId 1 if not specified
			    orgId: 1
			    # <string> custom UID which can be used to reference this datasource in other parts of the configuration, if not specified will be generated automatically
			    uid: prometheus
			    # <string> url
			    url: http://prometheus:9090
			    # <string> Deprecated, use secureJsonData.password
			    #password:
			    # <string> database user, if used
			    #user:
			    # <string> database name, if used
			    #database:
			    # <bool> enable/disable basic auth
			    #basicAuth:
			    # <string> basic auth username
			    #basicAuthUser:
			    # <string> Deprecated, use secureJsonData.basicAuthPassword
			    #basicAuthPassword:
			    # <bool> enable/disable with credentials headers
			    #withCredentials:
			    # <bool> mark as default datasource. Max one per org
			    isDefault: true
			    # <map> fields that will be converted to json and stored in jsonData
			    jsonData:
			      graphiteVersion: '1.1'
			      tlsAuth: false
			      tlsAuthWithCACert: false
			    # <string> json object of data that will be encrypted.
			    #secureJsonData:
			    #  tlsCACert: '...'
			    #  tlsClientCert: '...'
			    #  tlsClientKey: '...'
			    #  # <string> database password, if used
			    #  password:
			    #  # <string> basic auth password
			    #  basicAuthPassword:
			    version: 1
			    # <bool> allow users to edit datasources from the UI.
			    editable: false

		'''
		return template;
	}
	
	def static String getDashboards(ProjectModel project){
		var template = '''
		apiVersion: 1
		
		providers:
		  # <string> an unique provider name. Required
		  - name: '«project.companyName»'
		    # <int> Org id. Default to 1
		    orgId: 1
		    # <string> name of the dashboard folder.
		    folder: ''
		    # <string> folder UID. will be automatically generated if not specified
		    folderUid: ''
		    # <string> provider type. Default to 'file'
		    type: file
		    # <bool> disable dashboard deletion
		    disableDeletion: false
		    # <bool> enable dashboard editing
		    editable: true
		    # <int> how often Grafana will scan for changed dashboards
		    updateIntervalSeconds: 10
		    # <bool> allow updating provisioned dashboards from the UI
		    allowUiUpdates: false
		    options:
		      # <string, required> path to dashboard files on disk. Required when using the 'file' type
		      path: /var/lib/grafana/dashboards/general
		      # <bool> use folder names from filesystem to create folders in Grafana
		      foldersFromFilesStructure: true
		'''
		return template
	}
	
	def static String getGraph(Dashboard d, String uid) {
		i = 0
		var template = '''
		{
		    "annotations": {
		      "list": [
		        {
		          "builtIn": 1,
		          "datasource": "-- Grafana --",
		          "enable": true,
		          "hide": true,
		          "iconColor": "rgba(0, 211, 255, 1)",
		          "name": "Annotations & Alerts",
		          "type": "dashboard"
		        }
		      ]
		    },
		    "editable": true,
		    "gnetId": null,
		    "graphTooltip": 0,
		    "links": [],
		    "panels": [
		    «FOR panel : d.panelPredecessors SEPARATOR ','» 
		    	«getPanel(6,6, (i%2)*6,(i/2)*6, panel)»
		    «ENDFOR»
		    ],
		    "schemaVersion": 26,
		    "style": "dark",
		    "tags": [],
		    "templating": {
		      "list": []
		    },
		    "time": {
		      "from": "now-6h",
		      "to": "now"
		    },
		    "timepicker": {},
		    "timezone": "",
		    "title": "«d.name»",
		    "uid": "«uid»",
		    "version": 2
		  }
		  '''
		  
		  return template
	}
	
	def static String getPanel(int h, int w, int x, int y, Panel p){
		i = i + 1
		return '''
				      {
				        "aliasColors": {},
				        "bars": false,
				        "dashLength": 10,
				        "dashes": false,
				        "datasource": null,
				        "fieldConfig": {
				          "defaults": {
				            "custom": {}
				          },
				          "overrides": []
				        },
				        "fill": 1,
				        "fillGradient": 0,
				        "gridPos": {
				          "h": «h»,
				          "w": «w»,
				          "x": «x»,
				          "y": «y»
				        },
				        "hiddenSeries": false,
				        "id": «i»,
				        "legend": {
				          "avg": false,
				          "current": false,
				          "max": false,
				          "min": false,
				          "show": true,
				          "total": false,
				          "values": false
				        },
				        "lines": true,
				        "linewidth": 1,
				        "nullPointMode": "null",
				        "percentage": false,
				        "pluginVersion": "7.1.0",
				        "pointradius": 2,
				        "points": false,
				        "renderer": "flot",
				        "seriesOverrides": [],
				        "spaceLength": 10,
				        "stack": false,
				        "steppedLine": false,
				        "targets": [
				        «getTargets(p.incomingGraphQueryPanels)»
				        ],
				        "thresholds": [],
				        "timeFrom": null,
				        "timeRegions": [],
				        "timeShift": null,
				        "title": "«p.name»",
				        "tooltip": {
				          "shared": true,
				          "sort": 0,
				          "value_type": "individual"
				        },
				        "type": "graph",
				        "xaxis": {
				          "buckets": null,
				          "mode": "time",
				          "name": null,
				          "show": true,
				          "values": []
				        },
				        "yaxes": [
				          {
				            "format": "short",
				            "label": null,
				            "logBase": 1,
				            "max": null,
				            "min": null,
				            "show": true
				          },
				          {
				            "format": "short",
				            "label": null,
				            "logBase": 1,
				            "max": null,
				            "min": null,
				            "show": true
				          }
				        ],
				        "yaxis": {
				          "align": false,
				          "alignLevel": null
				        }
				      }
		'''
	}
	
	def static getTargets(EList<GraphQueryPanel> gqp) {
		var x = 0;
		var char c = 'A'
		var res = ""
		for (graph : gqp) {
			res +='''
            {
              "expr": "«Utils.graphToString(graph)»",
              "interval": "",
              "legendFormat": "",
              "refId": "«((c as int) + x) as char»" 
            }«IF graph != gqp.last»,«ENDIF»
			'''
			x += 1
		}
		return res 
	}
}
