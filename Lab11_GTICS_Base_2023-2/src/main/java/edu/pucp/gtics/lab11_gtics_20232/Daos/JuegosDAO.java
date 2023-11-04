package edu.pucp.gtics.lab11_gtics_20232.Daos;

import edu.pucp.gtics.lab11_gtics_20232.entity.Juegos;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class JuegosDAO {

    public List<Juegos> listaJuegos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Juegos[]> response = restTemplate.getForEntity(
                "", Juegos[].class);

        return Arrays.asList(response.getBody());

    }
    public void guardarJuego(Juegos juegos) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "";
        HttpEntity<Juegos> httpEntity = new HttpEntity<>(juegos, headers);

        RestTemplate restTemplate = new RestTemplate();
        if (juegos.getIdjuego() > 0) {
            restTemplate.put(url, httpEntity, Juegos.class);
        } else {
            restTemplate.postForEntity(url, httpEntity, Juegos.class);
        }

    }

}
