package repository;


import domain.Entity;

import java.util.HashMap;

public interface Repository<ID, E extends Entity<ID>> {

    E findOne(ID id);

    HashMap<Integer, E> findAll();

    E save(E entity);

    E delete(ID id);

    E update(E entity);
}

