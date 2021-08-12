package ru.job4j.shortcut.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.SiteRepository;

import java.util.UUID;

@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final BCryptPasswordEncoder encoder;

    public SiteService(SiteRepository siteRepository, BCryptPasswordEncoder encoder) {
        this.siteRepository = siteRepository;
        this.encoder = encoder;
    }

    public Site save(Site site) {
        if (siteRepository.findByUsername(site.getUsername()) == null) {
            site.setRegistration(true);
            site.setLogin(UUID.randomUUID().toString());
            String password = UUID.randomUUID().toString();
            site.setPassword(encoder.encode(password));
            siteRepository.save(site);
            site.setPassword(password);
        }
        return site;
    }
}
