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
const amp = Math.random() * 100;
const gauge = new Prometheus.Gauge({ name: 'sample_metric', help: 'metric_help' });

app.get('/metrics', (req, res) => {
    gauge.set(offsety + amp * Math.sin((Date.now() + offsetx) / 100000));
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