package com.github.olegik1719.learnup.operasales.lesson17;

import com.github.olegik1719.learnup.operasales.lesson17.model.Event;
import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;
import com.github.olegik1719.learnup.operasales.lesson17.model.Ticket;
import com.github.olegik1719.learnup.operasales.lesson17.repository.EventRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.OperaRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.TicketRepo;
import com.github.olegik1719.learnup.operasales.lesson17.services.SalesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class OperaApplication {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(OperaApplication.class, args);
        final SalesService salesService = ctx.getBean(SalesService.class);

        final OperaRepo operaRepo = ctx.getBean(OperaRepo.class);
        final TicketRepo ticketRepo = ctx.getBean(TicketRepo.class);
        final EventRepo eventRepo = ctx.getBean(EventRepo.class);

        // Добавлять новые премьеры (с указанием названия, подробного описания, возрастной категории и количество доступных мест)
        salesService.addOpera("Opera_1", "author_1", "Test opera 1", Opera.Category.OVER_03, 10);
        salesService.addOpera("Opera_2", "author_2", "Test opera 2", Opera.Category.OVER_06, 2);
        salesService.addOpera("Opera_3", "author_3", "Test opera 3", Opera.Category.OVER_16, 5);
        salesService.addOpera("Opera_4", "author_4", "Test opera 4", Opera.Category.OVER_18, 7);
        salesService.addOpera("Opera_5", "author_5", "Test opera 5", Opera.Category.NO_LIMITS, 8);
        salesService.addOpera("Opera_6", "author_6", "Test opera 6", Opera.Category.OVER_03, 1);
        salesService.addOpera("Opera_7", "author_7", "Test opera 7", Opera.Category.OVER_06, 4);

        System.out.println();
        System.out.println(operaRepo.getAll().stream().map(Opera::toString).collect(Collectors.joining("\n")));

        // Изменять показатели премьеры
        salesService.modifyOpera("Opera_1", "author_1", "Opera_1_modify", null, null, null, -1);
        salesService.modifyOpera("Opera_2", "author_2", null, "author_2_modify", null, null, -1);

        System.out.println();
        System.out.println(operaRepo.getAll().stream().map(Opera::toString).collect(Collectors.joining("\n")));

        Date april18 = new GregorianCalendar(2022, Calendar.APRIL, 18).getTime();
        Date april20 = new GregorianCalendar(2022, Calendar.APRIL, 20).getTime();

        salesService.addEvent(operaRepo.findByNameAuthor("Opera_3", "author_3"), april18);
        salesService.addEvent(operaRepo.findByNameAuthor("Opera_4", "author_4"), april18);
        salesService.addEvent(operaRepo.findByNameAuthor("Opera_4", "author_4"), april20);

        //Просматривать список всех мероприятий
        System.out.println();
        System.out.println(eventRepo.getAll().stream().map(Event::toString).collect(Collectors.joining("\n")));
        System.out.println();
        System.out.println(eventRepo.findByDate(april18).stream().map(Event::toString).collect(Collectors.joining("\n")));

        //Удалять мероприятие
        salesService.removeEvent(operaRepo.findByNameAuthor("Opera_4", "author_4"), april18);

        System.out.println();
        System.out.println(eventRepo.getAll().stream().map(Event::toString).collect(Collectors.joining("\n")));

        //Просматривать какого-то конкретного (с подробным описанием)
        System.out.println();
        System.out.println(eventRepo.getEvent("Opera_3", "author_3", april18));

        //Покупать билет на мероприятие и сдавать его (если вдруг надо)
        List<Integer> tickets = new ArrayList<>();
        System.out.println(">>>>>>>");
        System.out.println(eventRepo.getEvent("Opera_3", "author_3", april18));
        System.out.println("<<<<<<<");
        for (int i = 0; i < 7; i++) {
            try {
                tickets.add(salesService.buyTicket(operaRepo.findByNameAuthor("Opera_3", "author_3"), april18));
            } catch (Exception e){
                System.out.println("Не удалось продать билет: " + e.getMessage());
            }
            System.out.println();
            System.out.println(ticketRepo.getTicketsByEvent(eventRepo.getEvent("Opera_3", "author_3", april18)).stream().map(Ticket::toString).collect(Collectors.joining("\n")));
        }


        for (int idTicket:tickets) {
            salesService.refundTicket(idTicket);
            System.out.println();
            System.out.println(ticketRepo.getTicketsByEvent(eventRepo.getEvent("Opera_3", "author_3", april18)).stream().map(Ticket::toString).collect(Collectors.joining("\n")));
        }
    }
}
