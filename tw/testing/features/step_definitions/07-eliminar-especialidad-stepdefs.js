const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');

Given(
    'que la especialidad {string} existe en el sistema',
    function(nombre) {
        this.getResponse = request(
            'GET',
            `http://backend:8080/especialidad/${nombre}`
        );

        this.especialidadToDelete = JSON.parse(this.getResponse.getBody('utf8')).data;
    
        assert.ok(this.especialidadToDelete, `no se encontro ${nombre}`)
    }
);

When(
    'el administrador elimina la especialidad {string}',
    function() {
        this.deleteResponse = request(
            'DELETE',
            `http://backend:8080/especialidad/${this.especialidadToDelete.nombre}`
        );
    }
);

Then(
    'rta-test-07: el sistema responde con el codigo: {int} y mensaje: {string}',
    function(expectedStatus, expectedMessage) {
        assert.strictEqual(this.response.status, expectedStatus);
        assert.strictEqual(this.response.message, expectedMessage);
    }
);
