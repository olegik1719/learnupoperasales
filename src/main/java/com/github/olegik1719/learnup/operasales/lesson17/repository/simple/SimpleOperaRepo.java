package com.github.olegik1719.learnup.operasales.lesson17.repository.simple;

import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;
import com.github.olegik1719.learnup.operasales.lesson17.model.Ticket;
import com.github.olegik1719.learnup.operasales.lesson17.repository.OperaRepo;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleOperaRepo implements OperaRepo {

    private Set<Opera> operas = new HashSet<>();

    @Override
    public Collection<Opera> findByName(String name) {
        return operas.stream().filter(r -> r.getName().equals(name)).collect(Collectors.toSet());
    }

    @Override
    public Opera findByNameAuthor(String name, String author) {
        Opera opera = new Opera().setAuthor(author).setName(name);
        Optional<Opera> findOpera = operas.stream().filter(t -> t.equals(opera)).findAny();
        return findOpera.get();
    }

    @Override
    public Collection<Opera> getAll() {
        return new HashSet<>(operas);
    }

    @Override
    public boolean add(Opera opera) {
        return operas.add(opera);
    }

    @Override
    public boolean remove(Opera opera) {
        return operas.remove(opera);
    }

    @Override
    public boolean update(Opera oldOpera, Opera newOpera) {
        return operas.remove(oldOpera) && operas.add(newOpera);
    }
}
