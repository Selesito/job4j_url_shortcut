package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.model.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    boolean findByName(String name);
}
