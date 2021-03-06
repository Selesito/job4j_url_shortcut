package ru.job4j.shortcut.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.SiteRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SiteService {
    private static final Logger LOG = LoggerFactory.getLogger(SiteService.class.getName());
    private final SiteRepository siteRepository;
    private final BCryptPasswordEncoder encoder;

    public SiteService(SiteRepository siteRepository, BCryptPasswordEncoder encoder) {
        this.siteRepository = siteRepository;
        this.encoder = encoder;
    }

    public Site save(Site site) {
        try {
            site.setRegistration(true);
            site.setLogin(UUID.randomUUID().toString());
            String password = UUID.randomUUID().toString();
            site.setPassword(encoder.encode(password));
            siteRepository.save(site);
            site.setPassword(password);
        } catch (DataIntegrityViolationException e) {
            LOG.error("Учетная запись существует с таким именем!", e.getMessage());
            e.printStackTrace();
            return null;
        }
        return site;
    }

    public List<Link> findAll(Principal principal) {
        Site site = siteRepository.findByLogin(principal.getName());
        if (site != null) {
            return new ArrayList<Link>(site.getLinks());
        }
        return null;
    }
}
