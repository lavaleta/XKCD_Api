package mojagrupicaza.mingdji.controller;

import mojagrupicaza.mingdji.model.XKCD_infoObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mojagrupicaza.mingdji.service.XKCD_service;

@RequestMapping("api/XKCD")
@RestController
public class XKCD_controller {

    private final XKCD_service xkcd_service;

    public XKCD_controller(XKCD_service xkcd_service) {
        this.xkcd_service = xkcd_service;
    }

    @GetMapping()
    public XKCD_infoObject getMEME(){
        System.out.println("Zapravo zahtev radi kako treba? what?");

        return  xkcd_service.getMEME();
    }

}
