const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');

// GIVEN ESCENARIO 1
Given(
    'que la especialidad {string} existe en el sistema con la descripción {string}',
    function(nombre_original, descripcion_original) {
        this.getByNameResponse = request(
            'GET',
            `http://backend:8080/especialidad/${encodeURIComponent(nombre_original)}`
        );

        this.especialidadToModify = JSON.parse(this.getByNameResponse.getBody('utf8')).data;
        
        assert.ok(this.especialidadToModify, `no se encontro ${nombre_original}`);
    }
);

// GIVENS ESCENARIO 2
Given(
    'otra especialidad con el nombre {string} ya está registrada',
    function(nombre_existente) {

    }
);

When(
    'el administrador edita la especialidad {string} cambiando su nombre a {string} y su descripción a {string}',
    function(_nombre_original, nombre_nuevo, descripcion_nueva) {
        this.putResponse = request(
            'PUT',
            `http://backend:8080/especialidad/${this.especialidadToModify.id}`,
            {
                json: {
                    id: this.especialidadToModify.id,
                    nombre: nombre_nuevo,
                    descripcion: descripcion_nueva
                }
            }
        );

        this.response = JSON.parse(this.putResponse.getBody('utf8'));
    }
);

// When escenario 2
When(
    'el administrador intenta cambiar el nombre de {string} a {string}',
    function(_nombre_original, nombre_existente) {
        this.putResponse = request(
            'PUT',
            `http://backend:8080/especialidad/${this.especialidadToModify.id}`,
            {
                json: {
                    id: this.especialidadToModify.id,
                    nombre: nombre_existente,
                    descripcion: this.especialidadToModify.descripcion
                }
            }
        );

        this.response = JSON.parse(this.putResponse.getBody('utf8'));
    }
);

Then(
    'rta-test-06: el sistema responde con el codigo {int} y el mensaje {string}',
    function(expectedStatus, expectedMessage) {
        assert.strictEqual(this.response.status, expectedStatus);
        assert.strictEqual(this.response.message, expectedMessage);
    }
);