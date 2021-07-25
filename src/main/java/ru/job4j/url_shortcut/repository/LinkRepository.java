package ru.job4j.url_shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.model.Link;

public interface LinkRepository extends CrudRepository<Link, Integer> {
    boolean findByUrl(String url);
}
