package org.maral.eshop.watcheseshop;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maral.eshop.watcheseshop.api.ui.controllers.EshopStockItemController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class WatchesEshopApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private EshopStockItemController controller;

    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String createStockItemUrl;

    @BeforeEach
    public void runBeforeAllTestMethods() {
        restTemplate = new RestTemplate();
        createStockItemUrl = "http://localhost:" + port + "/stockItems/watches";
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void whenCreateNewStockItemShouldReturnNotEmptyId() throws JSONException, JsonProcessingException {
        JsonNode response = objectMapper.readTree(
            restTemplate.postForObject(
                createStockItemUrl,
                getRequestWithJson(getStockItemTestData()),
                String.class
            )
        );

        assertThat(response.get("watchId")).isNullOrEmpty();
    }

    private String getStockItemTestData() throws JSONException {
        JSONObject stockItem = new JSONObject();
        stockItem.put("title", "Watch-213");
        stockItem.put("price", "3820");
        stockItem.put("description", "Very nice watches.");
        stockItem.put("image",
            "iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkSPxfDwADqgHh5Lh3ywAAAABJRU5ErkJggg==");
        return stockItem.toString();
    }

    private HttpEntity<String> getRequestWithJson(String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(json, headers);
    }
}
