const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');

// ===================================================
// Creación de consultorios en un centro de atención
// ===================================================
Given(
    'que existe un centro de atención llamado {string}',
    function() {

    }
);

When(
    'se registra un consultorio con el número {int} y el nombre {string}',
    function() {
        
    }
);

Then(
    'rta-test-08: el sistema responde con status {int} y message {string}',
    function(expectedStatus, expectedMessage) {
        assert.strictEqual(this.response.status, expectedStatus);
        assert.strictEqual(this.response.message, expectedMessage);
    }
);

// ================================================
// Listar consultorios de un centro de atención
// ================================================
Given(
    'que existe un centro de atención llamado "Centro Médico Esperanza"',
    function() {

    }
);

When(
    'se solicita la lista de consultorios del centro',
    function() {
        
    }
);

Then(
    'la lista contiene los siguientes consultorios:',
    function(dataTable) {
        const expected = dataTable.hashes();

        expected.foreach(exp => {
            const consultorio = this.response.data.find(c =>
                c.numero === exp.numero &&
                c.nombre === exp.nombre_consultorio
            );

            assert.ok(consultorio, `No se encontro el consultorio ${exp.nombre_consultorio}`);
        });
    }
);

// ================================================
// Intentar listar consultorios de un centro inexistente
// ================================================
Given(
    'que el centro de atención llamado {string} no está registrado',
    function(nombreCentroInexistente) {
        
    }
);

When(
    'se solicita la lista de consultorios del centro {string}',
    function(nombreCentroInexistente) {
        
    }
);

Then(
    'la lista de consultorios está vacía',
    function() {
        assert.ok(Array.isArray(this.response.data));
        assert.strictEqual(this.response.data.length, 0);
    }
);

// ================================================
// Listar todos los centros con sus consultorios
// ================================================
Given(
    'que existen múltiples centros de atención registrados',
    function() {

    }
);

When(
    'se solicita la lista completa de centros con sus consultorios',
    function() {
        
    }
);

Then(
    function(dataTable) {
        const expected = dataTable.hashes();

        expected.foreach(exp => {
            const centro = this.response.data.find( c =>
                c.centro_atencion === exp.nombre
            );
            assert.ok(centro, `No se encontró el centro ${exp.centro_atencion}`);

            const consultorio = centro.consultorios.find(c =>
                c.numero === exp.numero &&
                c.nombre === exp.nombre_consultorio
            );
            assert.ok(consultorio, `No se encontro el consultorio ${exp.nombre_consultorio}`);
        });
    }
);