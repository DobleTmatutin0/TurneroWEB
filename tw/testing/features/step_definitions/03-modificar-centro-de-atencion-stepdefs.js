const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');

Given(
    'que existe un sist de centros de atencion',
    function() {
        request(
            'DELETE',
            'http://backend:8080/test/delete-all'
        );
    }
);

Given(
    'los siguientes centros de atención han sido registrados:',
    function(dataTable) {
        dataTable.hashes().forEach(element => {
            request(
                'POST',
                'http://backend:8080/centros-de-atencion',
                {
                    json: {
                        "nombre": element.Nombre,
                        "direccion": element.Dirección,
                        "localidad": element.Localidad,
                        "provincia": element.Provincia,
                        "coordenadas": {
                            "latitud": element.Coordenadas.split(',')[0].trim(),
                            "longitud": element.Coordenadas.split(',')[1].trim()
                        }
                    }
                }
            );
        });
    }
);

When(
    'el administrador modifica los datos del centro de atención {string} con los siguientes atributos:',
    function(nombreOriginal, dataTable) {
        const data = dataTable.rowsHash();

        // GET all para buscar el id del centro a modificar
        const getResponse = request(
            'GET',
            'http://backend:8080/centros-de-atencion'
        );

        const allCentros = JSON.parse(getResponse.getBody('utf8')).data;
        const centroToModify = allCentros.find(c => c.nombre === nombreOriginal);
        
        assert.ok(centroToModify, `No se encontró ${nombreOriginal}`);

        const id = centroToModify.id;

        // PUT
        this.backendRawResponse = request(
            'PUT',
            `http://backend:8080/centros-de-atencion/${id}`,
            {
                json: {
                    nombre: data.nombre,
                    direccion: data.direccion,
                    localidad: data.localidad,
                    provincia: data.provincia,
                    coordenadas: {
                        latitud: data.coordenadas.split(',')[0].trim(),
                        longitud: data.coordenadas.split(',')[1].trim()
                    }
                }
            }
        );

        this.response = JSON.parse(this.backendRawResponse.getBody('utf8'));
    }
);

Then(
    'el sistema responde con {int} y {string}',
    function(expectedStatus, expectedMessage) {
        assert.strictEqual(this.response.status, expectedStatus);
        assert.strictEqual(this.response.message, expectedMessage);

    }
);