package edu.pucp.gtics.lab11_gtics_20232.Daos;

import edu.pucp.gtics.lab11_gtics_20232.entity.Generos;
import edu.pucp.gtics.lab11_gtics_20232.entity.Plataformas;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Component
public class PlataformasDAO {

    public List<Plataformas> listaPlataformas(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Plataformas[]> response = restTemplate.getForEntity(
                "http://localhost:8081/plataformas", Plataformas[].class);

        return Arrays.asList(response.getBody());
    }
}
