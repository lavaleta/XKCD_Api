package mojagrupicaza.mingdji.service;

import mojagrupicaza.mingdji.model.XKCD_infoObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class XKCD_service {

    private static final String MEME_URL = "https://xkcd.com/info.0.json";

    private final RestTemplate restTemplate;

    public XKCD_service(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("XKCD")
    public XKCD_infoObject getMEME() {
        try {
            URI url = new URI(MEME_URL);
            return invoke(url, XKCD_infoObject.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T invoke(URI url, Class<T> responseType) {
        RequestEntity<?> request = RequestEntity.get(url)
                .accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate
                .exchange(request, responseType);
        return exchange.getBody();
    }
}
