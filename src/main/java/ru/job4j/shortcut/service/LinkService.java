package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.LinkRepository;
import ru.job4j.shortcut.repository.SiteRepository;

import java.security.Principal;
import java.util.UUID;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final SiteRepository siteRepository;

    public LinkService(LinkRepository linkRepository, SiteRepository siteRepository) {
        this.linkRepository = linkRepository;
        this.siteRepository = siteRepository;
    }

    public Link save(Link link, Principal principal) {
        if (linkRepository.findByUrl(link.getUrl()) == null) {
            link.setCode(UUID.randomUUID().toString());
            Site site = siteRepository.findByLogin(principal.getName());
            site.addLink(link);
            siteRepository.save(site);
            return link;
        }
        return null;
    }

    public Link searchCode(String code) {
        if (!code.isEmpty() && code != null) {
            Link link = linkRepository.findByCode(code);
            link.incrementCount();
            linkRepository.save(link);
            return link;
        }
        return null;
    }
}
