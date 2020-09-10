'use strict'

const express = require('express')
const Prometheus = require('prom-client')

const app = express()
const port = process.env.PORT || 3001
const metricsInterval = Prometheus.collectDefaultMetrics()
const checkoutsTotal = new Prometheus.Counter({
  name: 'checkouts_total',
  help: 'Total number of checkouts',
  labelNames: ['payment_method']
})

const offsetx = Math.random() * 100000;
const offsety = Math.random() * 10;
const temperaturex = Math.random() * 100000;
const temperaturey = Math.random() * 10;
const trafficx = Math.random() * 100000;
const trafficy = Math.random() * 10;
const amp = Math.random() * 100;
const powerconsumption = new Prometheus.Gauge({ name: 'powerconsumption', help: 'metric_help' });
const temperature = new Prometheus.Gauge({ name: 'temperature', help: 'metric_help' });
const traffic = new Prometheus.Gauge({ name: 'traffic', help: 'metric_help' });

app.get('/metrics', (req, res) => {
    powerconsumption.set(offsety + amp * Math.sin((Date.now() + offsetx) / 100000));
    temperature.set(temperaturey + amp * Math.sin((Date.now() + temperaturex) / 100000));
    traffic.set(trafficy + amp * Math.sin((Date.now() + trafficx) / 100000));
    res.set('Content-Type', Prometheus.register.contentType)
    res.end(Prometheus.register.metrics())
  })

const server = app.listen(port, () => {
    console.log(`Example app listening on port ${port}!`)
  })
  
  // Graceful shutdown
  process.on('SIGTERM', () => {
    clearInterval(metricsInterval)
  
    server.close((err) => {
      if (err) {
        console.error(err)
        process.exit(1)
      }
  
      process.exit(0)
    })
  })