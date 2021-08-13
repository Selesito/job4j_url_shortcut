package ru.job4j.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Link> convert(@RequestBody Link link, Principal principal) {
        return new ResponseEntity<Link>(
                this.service.save(link, principal),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/redirect")
    public ResponseEntity<String> redirect(@RequestParam String code) {
        Link link = service.searchCode(code);
        return link != null
                ? new ResponseEntity<>(link.getUrl(), HttpStatus.resolve(302))
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
