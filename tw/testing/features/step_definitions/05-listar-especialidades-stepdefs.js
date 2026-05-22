const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');
const request = require('sync-request');

Given(
    'que existen 19 especialidades registradas en el sistema',
    function() {

    }
);

When(
    'un usuario del sistema solicita la lista de especialidades',
    function() {
        this.getAllResponse = request(
            'GET',
            'http://backend:8080/especialidad'
        );

        this.response = JSON.parse(this.getAllResponse.getBody('utf8'));
    }
);

Then(
    'rta-test-05: el sistema responde con status_code 200 y status_text OK',
    function() {
        assert.strictEqual(this.response.status, 200);
        assert.strictEqual(this.response.message, "OK");
    }
);

Then(
    'la lista contiene las siguientes especialidades:',
    function(dataTable) {
        const expected = dataTable.hashes();

        expected.forEach(exp => {
            const encontrado = this.response.data.find(e =>
                e.nombre === exp.nombre &&
                e.descripcion === exp.descripcion
            );
            
            assert.ok(encontrado, `No se encontro la especialidad ${exp.nombre}`);
        });
    }
);