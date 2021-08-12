package ru.job4j.shortcut.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.service.SiteService;

@RestController
@RequestMapping("/site")
public class SiteController {
    private final SiteService service;

    public SiteController(SiteService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<Site> create(@RequestBody Site site) {
        return new ResponseEntity<Site>(
                this.service.save(site),
                HttpStatus.CREATED
        );
    }
}
