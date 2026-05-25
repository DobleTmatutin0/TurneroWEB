const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');

// ===================================================
// Creación de consultorios en un centro de atención
// ===================================================
Given(
    'que existe un centro de atención llamado {string}',
    function(centro_atencion) {
        const getResponse = request(
            'GET',
            `http://backend:8080/centros-de-atencion/${centro_atencion}`
        );

        this.centroToModify = JSON.parse(getResponse.getBody('utf8')).data;

        assert.ok(this.centroToModify, `No se encontró ${centro_atencion}`);
    }
);

When(
    'se registra un consultorio con el número {int} y el nombre {string}',
    function(numero, nombre_consultorio) {
        const newConsultorio = {
            numero: numero,
            nombre: nombre_consultorio
        }

        this.centroToModify.consultorios.push(newConsultorio);

        const putResponse = request(
            'PUT',
            `http://backend:8080/centros-de-atencion/${this.centroToModify.id}`,
            {
                json: this.centroToModify
            }
        );

        this.response = JSON.parse(putResponse.getBody('utf8'));
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
    'que existe un centro de atención llamado {string}',
    function(centro_atencion) {
        this.nombreCentro = centro_atencion;
    }
);

When(
    'se solicita la lista de consultorios del centro',
    function() {
        const getResponse = request(
            'GET',
            `http://backend:8080/centros-de-atencion/${this.nombreCentro}/consultorios`
        );

        this.response = JSON.parse(getResponse.getBody('utf8'));
    }
);

Then(
    'la lista contiene los siguientes consultorios:',
    function(dataTable) {
        const expected = dataTable.hashes();

        expected.forEach(exp => {
            const consultorio = this.response.data.find(c =>
                Number(c.numero) === Number(exp.numero) &&
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
        this.nombreCentro = nombreCentroInexistente;
    }
);

When(
    'se solicita la lista de consultorios del centro {string}',
    function(_nombreCentroInexistente) {
        const getResponse = request(
            'GET',
            `http://backend:8080/centros-de-atencion/${this.nombreCentro}/consultorios`
        );

        this.response = JSON.parse(getResponse.getBody('utf8'));
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
        const getResponse = request(
            'GET',
            'http://backend:8080/centros-de-atencion',
        );

        this.response = JSON.parse(getResponse.getBody('utf8'));
    }
);

Then(
    'la lista contiene los siguientes centros y consultorios:',
    function(dataTable) {
        const expected = dataTable.hashes();

        expected.forEach(exp => {
            const centro = this.response.data.find( c =>
                c.nombre === exp.centro_atencion
            );
            assert.ok(centro, `No se encontró el centro ${exp.centro_atencion}`);

            assert.ok(Array.isArray(centro.consultorios));
            const consultorio = centro.consultorios.find(c =>
                Number(c.numero) === Number(exp.numero) &&
                c.nombre === exp.nombre_consultorio
            );
            assert.ok(consultorio, `No se encontró el consultorio ${exp.nombre_consultorio} del centro ${exp.centro_atencion}`);
        });
    }
);




// ================================================
// Editar consultorios de un centro de atencion
// ================================================
Given(
    'que existe el centro {string} cargado en el sistema',
    function(centro_atencion) {
        const getResponse = request(
            'GET',
            `http://backend:8080/centros-de-atencion/${centro_atencion}`
        );

        this.centroToModify = JSON.parse(getResponse.getBody('utf8')).data;

        assert.ok(this.centroToModify, `No se encontró ${centro_atencion}`);
    }
);

When(
    'se modifica el consultorio Nº {int} y nombre {string} del centro {string} con el num {int} y el nombre {string}',
    function(oldNum, oldNombre, centroAtencion, newNum, newNombre) {
        
        const consultorioToModify =
            this.centroToModify.consultorios.find(c =>
                Number(c.numero) === Number(oldNum) &&
                c.nombre === oldNombre
            );

        assert.ok(
            consultorioToModify,
            `No se encontró el consultorio ${oldNombre}`
        );

        consultorioToModify.numero = newNum;
        consultorioToModify.nombre = newNombre;

        const putResponse = request(
            'PUT',
            `http://backend:8080/centros-de-atencion/${this.centroToModify.id}`,
            {
                json: this.centroToModify
            }
        );

        this.response = JSON.parse(
            putResponse.getBody('utf8')
        );
    }
);
