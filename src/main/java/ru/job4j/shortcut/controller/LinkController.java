package ru.job4j.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.service.LinkService;

import java.security.Principal;

@RestController
@RequestMapping("/link")
public class LinkController {
    private final LinkService service;

    public LinkController(LinkService service) {
        this.service = service;
    }

    @PostMapping("/convert")
    public ResponseEntity<Link> create(@RequestBody Link link, Principal principal) {
        return new ResponseEntity<Link>(
                this.service.save(link, principal),
                HttpStatus.CREATED
        );
    }
}
