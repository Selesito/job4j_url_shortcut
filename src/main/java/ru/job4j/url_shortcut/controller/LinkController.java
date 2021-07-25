package ru.job4j.url_shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.url_shortcut.model.Link;
import ru.job4j.url_shortcut.service.LinkService;
import ru.job4j.url_shortcut.service.SiteService;

@RestController
@RequestMapping("/link")
public class LinkController {
    private final LinkService service;

    public LinkController(LinkService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Link> create(@RequestBody Link link) {
        return new ResponseEntity<Link>(
                this.service.save(link),
                HttpStatus.CREATED
        );
    }
}
