const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const { json } = require('stream/consumers');
const request = require('sync-request');

When(
    'el administrador crea una especialidad con el nombre {string} y la descripción {string}',
    function(nombre, descripcion) {
        
        this.aNewEspecialida = {
            nombre: nombre,
            descripcion: descripcion
        };
        
        this.postResponse = request(
            'POST',
            'http://backend:8080/especialidad',
            {
                json: this.aNewEspecialida
            }
        );

        this.response = JSON.parse(this.postResponse.getBody('utf8'));

    }
);

Then(
    'el sistema responde con codigo: {int} y mensaje: {string}',
    function(expectedStatus, expectedMessage) {
        assert.strictEqual(this.response.status, expectedStatus);
        assert.strictEqual(this.response.message, expectedMessage);

    }
);