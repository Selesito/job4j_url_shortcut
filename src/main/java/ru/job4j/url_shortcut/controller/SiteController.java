package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.model.Site;
import ru.job4j.url_shortcut.service.SiteService;

@RestController
@RequestMapping("/site")
public class SiteController {
    private final SiteService service;

    public SiteController(SiteService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Site> create(@RequestBody Site site) {
        return new ResponseEntity<Site>(
                this.service.save(site),
                HttpStatus.CREATED
        );
    }
}
