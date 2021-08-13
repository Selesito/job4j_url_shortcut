package ru.job4j.shortcut.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.service.SiteService;

import java.security.Principal;

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

    @GetMapping("/statistic")
    public Iterable<Link> findAll(Principal principal) {
        return this.service.findAll(principal);
    }
}
