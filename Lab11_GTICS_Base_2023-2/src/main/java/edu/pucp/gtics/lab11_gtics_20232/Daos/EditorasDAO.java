package edu.pucp.gtics.lab11_gtics_20232.Daos;

import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class EditorasDAO {
    public List<Editoras> listaJuegos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Juegos[]> response = restTemplate.getForEntity(
                "http://localhost:8080/editoras/list", Juegos[].class);
        return Arrays.asList(response.getBody());
    }

}
