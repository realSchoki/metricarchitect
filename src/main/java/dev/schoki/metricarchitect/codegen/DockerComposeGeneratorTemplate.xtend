package dev.schoki.metricarchitect.codegen

class DockerComposeGeneratorTemplate {
	
	def static String generate() {
		var String template = '''
		version: '3'
		services:
		  grafana:
		    ports:
		      - "127.0.0.1:3000:3000"
		    volumes:
		      - data-grafana:/var/lib/grafana
		      - ./grafana_prometheus_connection.yml:/etc/grafana/provisioning/datasources/grafana_prometheus_connection.yml
		      - ./dashboards_config.yml:/etc/grafana/provisioning/dashboards/dashboards_config.yml
		      - ./dashboards:/var/lib/grafana/dashboards/general
		    links:
		    - prometheus
		    image: "grafana/grafana"
		  prometheus:
		    ports:
		      - "127.0.0.1:9090:9090"
		    volumes:
		      - ./prometheus.yml:/etc/prometheus/prometheus.yml
		      - data-prometheus:/prometheus
		    image: "prom/prometheus"
		volumes:
		  data-prometheus:
		    driver: local
		  data-grafana:
		    driver: local
		'''
		return template;
	}
}