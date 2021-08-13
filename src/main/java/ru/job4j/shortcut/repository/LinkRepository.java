package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Link;

public interface LinkRepository extends CrudRepository<Link, Integer> {
    Link findByUrl(String url);

    Link findByCode(String code);
}
