const assert = require('assert');
const {Given, When, Then } = require('@cucumber/cucumber');

const request = require('sync-request');


Given(
    'que existe un sistema de gestión de centros de atención',
    function() {
        this.sistema = {
            centros: []
        }
        return 'pending';
    }
);

When(
    'Cuando el administrador ingresa los datos del centro de atención: {string}, {string}, {string}, {string} y {string}',
    function(nombre, dirección, localidad, provincia, coordenadasStr) {
        const [lat, lon] = coordenadasStr.split(',').map(c => {
            parseFloat(c.trim());
        });

        const coordenadas = {
            latitud = lat,
            longitud = lon
        };
        this.coordenadas = coordenadas;
        
        return 'pending';
    }
);

Then(
    'el sistema responde con {int} y {string}',
    function(status_code, status_text) {
        return 'pending';
    }
);