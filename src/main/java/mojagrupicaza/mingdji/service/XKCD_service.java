package mojagrupicaza.mingdji.service;

import mojagrupicaza.mingdji.dao.XKCDdao;
import mojagrupicaza.mingdji.model.XKCD_infoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class XKCD_service {

    private static final String MEME_URL_left = "https://xkcd.com/";
    private static final String MEME_URL_right = "/info.0.json";

    private final RestTemplate restTemplate;


    private final XKCDdao xkcDdao;

    @Autowired
    public XKCD_service(RestTemplate restTemplate, XKCDdao xkcDdao) {

        this.restTemplate = restTemplate;
        this.xkcDdao = xkcDdao;
    }

    public List<XKCD_infoObject> getMemeHistory(){
        return xkcDdao.findAll();
    }

    @Cacheable("XKCD")
    public String getMEME() {
        try {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 999 + 1;
            int randomInt = (int) randomDouble;

            URI url = new URI(MEME_URL_left + randomInt + MEME_URL_right );
            XKCD_infoObject o = invoke(url, XKCD_infoObject.class);
            xkcDdao.save(o);
            return o.getImg();
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
