const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');


Given(
    'que existe un sistema de gestión de centros de atención',
    function() {
        this.sistema = {
            centros: []
        }
        
    }
);

When(
    'el administrador ingresa los datos del centro de atención: {string}, {string}, {string}, {string} y {string}',
    function(nombre, direccion, localidad, provincia, coordenadas) {
        
        this.aNewCentroDeAtencion = {
            "nombre": nombre,
            "direccion": direccion,
            "localidad": localidad,
            "provincia": provincia,
            "coordenadas": {
                "latitud": coordenadas.split(',')[0].trim(),
                "longitud": coordenadas.split(',')[1].trim()
            }
        };

        this.backendResponse = request(
            'POST',
            'http://backend:8080/centros-de-atencion',
            {
                json: this.aNewCentroDeAtencion
            }
        );
        
        this.response = JSON.parse(this.backendResponse.getBody('utf8'));

    }
);

Then(
    'el sistema responde con {int} y {string}',
    function(expectedStatus, expectedMessage) {
        assert.strictEqual(this.response.status, expectedStatus);
        assert.strictEqual(this.response.message, expectedMessage);
        
    }
);