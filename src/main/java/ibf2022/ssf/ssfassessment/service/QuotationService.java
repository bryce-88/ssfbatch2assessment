package ibf2022.ssf.ssfassessment.service;

import java.io.StringReader;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import ibf2022.ssf.ssfassessment.model.Quotation;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class QuotationService {
    
    public Quotation getQuotations(List<String> items) throws Exception {
        
        JsonArrayBuilder jsonArr = Json.createArrayBuilder();
        for (int i = 0; i < items.size(); i++) {
            jsonArr.add(items.get(i));
        }
        jsonArr.build();

        String url = "https://quotation.chuklee.com";

        RequestEntity<String> req = RequestEntity.post(url).contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON)
                                    .body(jsonArr.toString(), String.class);
                                    
        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = null;

        String payload = "";
        int statusCode = 500;
        try {
            resp = template.exchange(req, String.class);
            payload = resp.getBody();
            statusCode = resp.getStatusCode().value();

        } catch (HttpClientErrorException ex) {
            payload = ex.getResponseBodyAsString();
            statusCode = ex.getStatusCode().value();
            return null;
        } finally {
            System.out.printf(">>> status code: %d\n", statusCode);
            System.out.printf(">>> payload: \n%s\n", payload);
        }

        JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject quoteJson = reader.readObject();
		JsonObject id = quoteJson.getJsonObject("quoteId");
        JsonObject quotations = quoteJson.getJsonObject("quotations");
        

        return null;
    }
}
