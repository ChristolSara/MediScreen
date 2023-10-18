package webApp.Gateway;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
@Component
public class DiabeteGateway {

    private final RestTemplate restTemplate;



    public ResponseEntity<String> getDiabete(Integer id) {

        return restTemplate.getForEntity("http://localhost:8020/diabete/{id}", String.class,id);
    }
}
