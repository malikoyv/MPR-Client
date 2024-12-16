package pjwstk.edu.pl.mpr.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import pjwstk.edu.pl.mpr.models.Cat;
import java.util.List;

@Service
public class CatService {
    @Value("${uriBase}")
    private String uriBase;

    private final RestClient restClient = RestClient.create();

    public List<Cat> getAll(){
        return restClient.get()
                .uri(uriBase + "/getAllCats")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public List<Cat> getByName(final String name) {
        return restClient.get()
                .uri(uriBase + "/getByName/" + name)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Void> addCat(Cat cat) {
        cat.setIdentificator();

        return restClient.post()
                .uri(uriBase + "/addCat")
                .contentType(MediaType.APPLICATION_JSON)
                .body(cat)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<Void> deleteByName(final String name) {
        return restClient.delete()
                .uri(uriBase + "/deleteCat/" + name)
                .retrieve()
                .toBodilessEntity();
    }

    public Cat getById(Long id){
        return restClient.get()
                .uri(uriBase + "/getById/" + id)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Void> updateCat(Cat cat){
        return restClient.post()
                .uri(uriBase + "/updateCat/" + cat.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .body(cat)
                .retrieve()
                .toBodilessEntity();
    }

}
