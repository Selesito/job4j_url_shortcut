package ru.job4j.url_shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.model.Link;
import ru.job4j.url_shortcut.repository.LinkRepository;
import ru.job4j.url_shortcut.repository.SiteRepository;

import java.util.UUID;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final SiteRepository siteRepository;


    public LinkService(LinkRepository linkRepository, SiteRepository siteRepository) {
        this.linkRepository = linkRepository;
        this.siteRepository = siteRepository;
    }

    public Link save(Link link) {
        if (!linkRepository.findByUrl(link.getUrl())) {
            link.setCode(UUID.randomUUID().toString());
        }
        return linkRepository.save(link);
        //добавить дабовления в set сайта
    }
}
