package com.github.olegik1719.learnup.operasales.lesson17.repository;

import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;

import java.util.Collection;

public interface OperaRepo {

    Collection<Opera> findByName(String name);

    Opera findByNameAuthor(String name, String author);

    Collection<Opera> getAll();

    boolean add(Opera opera);

    boolean remove(Opera opera);

    boolean update(Opera oldOpera, Opera newOpera);
}
