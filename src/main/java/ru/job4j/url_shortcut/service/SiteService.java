package ru.job4j.url_shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.model.Site;
import ru.job4j.url_shortcut.repository.SiteRepository;

import java.util.UUID;

@Service
public class SiteService {
    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Site save(Site site) {
        if (!siteRepository.findByName(site.getUsername())) {
            site.setLogin(UUID.randomUUID().toString());
            site.setPassword(UUID.randomUUID().toString());
            site.setRegistration(true);
        }
        return siteRepository.save(site);
    }
}
