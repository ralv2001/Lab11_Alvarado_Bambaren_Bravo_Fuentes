package edu.pucp.gtics.lab11_gtics_20232.Daos;


import edu.pucp.gtics.lab11_gtics_20232.entity.Generos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class GenerosDAO {

    public List<Generos> listaGenero(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Generos[]> response = restTemplate.getForEntity(
                "http://localhost:8080/generos/list", Generos[].class);

        return Arrays.asList(response.getBody());
    }
}
