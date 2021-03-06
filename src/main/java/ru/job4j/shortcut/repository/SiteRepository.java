package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {

    Site findByUsername(String username);

    Site findByLogin(String login);
}
