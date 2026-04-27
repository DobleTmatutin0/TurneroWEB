const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');

Given(
    'que existe un sistema de centros de atencion',
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
        dataTable.hashes().forEach(dataTableElement => {

            request(
                'POST',
                'http://backend:8080/centros-de-atencion',
                {
                    json: {
                        "nombre": dataTableElement.Nombre,
                        "direccion": dataTableElement.Dirección,
                        "localidad": dataTableElement.Localidad,
                        "provincia": dataTableElement.Provincia,
                        "coordenadas": {
                            "latitud": dataTableElement.Coordenadas.split(',')[0].trim(),
                            "longitud": dataTableElement.Coordenadas.split(',')[1].trim()
                        }
                    }
                }
            );
        })
    }
);

When(
    'el usuario solicita la lista de centros de atención',
    function() {
        this.backendRawResponse = request(
            'GET',
            'http://backend:8080/centros-de-atencion',
        );

        this.response = JSON.parse(this.backendRawResponse.getBody('utf8'));

    }
);

Then(
    'el sistema responde con status_code {int} y status_text {string}',
    function(expectedStatus, expectedMessage) {
        assert.strictEqual(this.response.status, expectedStatus);
        assert.strictEqual(this.response.message, expectedMessage);
            
    }
);

Then(
    'la lista contiene los siguientes centros de atención:',
    function(dataTable) {
        const expected = dataTable.hashes();

        expected.forEach(exptd => {
            const [lat, long] = exptd.Coordenadas.split(',').map(v => v.trim());

            const encontrado = this.response.data.find(e => 
                e.nombre === exptd.Nombre &&
                e.localidad === exptd.Localidad &&
                e.direccion === exptd.Dirección &&
                e.provincia === expected.Provincia &&
                Number(e.coordenadas.latitud) === Number(lat) &&
                Number(e.coordenadas.longitud) === Number(long)
            );

            assert.ok(encontrado, `No se encontro el centro ${exptd.Nombre}`)
        });
    }
);
