package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Link;

import javax.transaction.Transactional;

@Transactional
public interface LinkRepository extends CrudRepository<Link, Integer> {
    Link findByCode(String code);

    @Modifying
    @Query("update Link x set x.count = x.count + 1 where x.id = ?1")
    void incrementCount(int id);
}
