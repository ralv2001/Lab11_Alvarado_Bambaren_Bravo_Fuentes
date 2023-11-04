package edu.pucp.gtics.lab11_gtics_20232.Daos;

import edu.pucp.gtics.lab11_gtics_20232.entity.Distribuidoras;
import edu.pucp.gtics.lab11_gtics_20232.entity.Generos;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class DistribuidorasDAO {

    public List<Distribuidoras> listaDistribuidoras(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Distribuidoras[]> response = restTemplate.getForEntity(
                "", Distribuidoras[].class);

        return Arrays.asList(response.getBody());
    }
}
