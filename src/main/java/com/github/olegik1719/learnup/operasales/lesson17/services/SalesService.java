package com.github.olegik1719.learnup.operasales.lesson17.services;

import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;

import java.util.Date;

/**
 * 1) Добавлять новые премьеры (с указанием названия, подробного описания, возрастной категории и количество доступных мест)
 * 2) Изменять показатели премьеры
 * 3) Удалять мероприятие
 * 4) Просматривать список всех мероприятий и какого-то конкретного (с подробным описанием)
 * 5) Покупать билет на мероприятие и сдавать его (если вдруг надо)
 */

public interface SalesService {
    boolean addOpera(String name, String author, String description, Opera.Category category, int capacity);

    Boolean modifyOpera(String oldName, String oldAuthor, String newName, String newAuthor, String newDescription, Opera.Category newCategory, int capacity);

    boolean removeOpera(String name, String author);

    Boolean addEvent(Opera opera, Date date);

    Boolean removeEvent(Opera opera, Date date);

    Integer buyTicket(Opera opera, Date date);

    boolean refundTicket(int id);
}
