package org.jamayoral.minsait.infrastructure.adapters.out.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Test de Integración sobre la API REST")
class PriceRestAdapterIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void getPriceForProduct35455_Brand1_AtDate20200614_1000_ExpectPrice35() {
        String expected = "{\"productId\":35455,\"brandId\":1,\"priceList\":1,\"startDate\":\"2020-06-14T00:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\",\"price\":35.50}";
        String url = "http://localhost:" + port + "/v1/prices/?date=2020-06-14T10:00:00&productId=35455&brandId=1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void getPriceForProduct35455_Brand1_AtDate20200614_1600_ExpectPrice25() {
        String expected = "{\"productId\":35455,\"brandId\":1,\"priceList\":2,\"startDate\":\"2020-06-14T15:00:00\"," +
                "\"endDate\":\"2020-06-14T18:30:00\",\"price\":25.45}";
        String url = "http://localhost:" + port + "/v1/prices/?date=2020-06-14T16:00:00&productId=35455&brandId=1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void getPriceForProduct35455_Brand1_AtDate20200614_2100_ExpectPrice35() {
        String expected = "{\"productId\":35455,\"brandId\":1,\"priceList\":1,\"startDate\":\"2020-06-14T00:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\",\"price\":35.50}";
        String url = "http://localhost:" + port + "/v1/prices/?date=2020-06-14T21:00:00&productId=35455&brandId=1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void getPriceForProduct35455_Brand1_AtDate20200615_1000_ExpectPrice30() {
        String expected = "{\"productId\":35455,\"brandId\":1,\"priceList\":3,\"startDate\":\"2020-06-15T00:00:00\"," +
                "\"endDate\":\"2020-06-15T11:00:00\",\"price\":30.50}";
        String url = "http://localhost:" + port + "/v1/prices/?date=2020-06-15T10:00:00&productId=35455&brandId=1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    void getPriceForProduct35455_Brand1_AtDate20200616_2100_ExpectPrice38() {
        String expected = "{\"productId\":35455,\"brandId\":1,\"priceList\":4,\"startDate\":\"2020-06-15T16:00:00\"," +
                "\"endDate\":\"2020-12-31T23:59:59\",\"price\":38.95}";
        String url = "http://localhost:" + port + "/v1/prices/?date=2020-06-16T21:00:00&productId=35455&brandId=1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    @DisplayName("Test: Obtener precio para un producto inexistente debería devolver código 204")
    void getPriceForNonExistentProduct_ExpectStatusCode204() {
        int nonExistentProductId = 999999;

        String url = "http://localhost:" + port + "/v1/prices/?date=2020-01-01T00:00:00&productId="
                + nonExistentProductId + "&brandId=1";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @DisplayName("Test: Obtener precio con parámetros incompletos debería devolver error 400")
    void getPriceWithIncompleteParameters_ExpectError400() {
        String url = "http://localhost:" + port + "/v1/prices/";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
