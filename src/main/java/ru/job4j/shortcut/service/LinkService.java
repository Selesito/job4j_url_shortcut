package ru.job4j.shortcut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.LinkRepository;
import ru.job4j.shortcut.repository.SiteRepository;

import java.security.Principal;
import java.util.UUID;

@Service
public class LinkService {
    private static final Logger LOG = LoggerFactory.getLogger(SiteService.class.getName());
    private final LinkRepository linkRepository;
    private final SiteRepository siteRepository;

    public LinkService(LinkRepository linkRepository, SiteRepository siteRepository) {
        this.linkRepository = linkRepository;
        this.siteRepository = siteRepository;
    }

    public Link save(Link link, Principal principal) {
        try {
            link.setCode(UUID.randomUUID().toString());
            Site site = siteRepository.findByLogin(principal.getName());
            site.addLink(link);
            siteRepository.save(site);
            return link;
        } catch (DataIntegrityViolationException e) {
            LOG.error("Учетная запись существует с таким именем!", e);
            e.getMessage();
            return null;
        }
    }

    public Link searchCode(String code) {
        if (!code.isEmpty() && code != null) {
            Link link = linkRepository.findByCode(code);
            linkRepository.incrementCount(link.getId());
            return link;
        }
        return null;
    }
}
