package com.github.olegik1719.learnup.operasales.lesson17.aspects;

import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.OperaEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Aspect
public class EmailNotifier {
    /**
     * 1) Покупка билета
     * 2) Анонс мероприятия
     * 3) Перенос мероприятия
     */

    @Pointcut("execution(public * com.github.olegik1719.learnup.operasales.lesson17.services.DbSalesService.buyTicket(..))")
    public void ticketBuyNotifier() {
    }


    @Pointcut("@annotation(com.github.olegik1719.learnup.operasales.lesson17.annotations.Notifiable)")
    public void anyNotifiable() {
    }

    @Around("ticketBuyNotifier()")
    public Long aroundBuyTicket(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName() + "()";
        sendMail("Готовимся к выполнению метода " + methodName);

        try {
            Long idTicket = (Long) point.proceed();
            sendMail("Продан билет с номером " + idTicket);
            return idTicket;
        } catch (Throwable throwable) {
            sendMail("Не удалось продать билет: " + throwable.getMessage());
            throw new RuntimeException(throwable);
        }
    }

    @After("anyNotifiable()")
    public void afterNotifiable(JoinPoint point) {
        if (point.getSignature().getName() == "modifyOpera") {
            Object[] args = point.getArgs();
            sendMail("Была изменена опера:\n" +
                    "Название: " + args[0] + "\n" +
                    "Автор: " + args[1]);
        } else if (point.getSignature().getName() == "addEvent") {
            Object[] args = point.getArgs();
            sendMail("Было добавлено мероприятие:\n" +
                    "Название: " + ((OperaEntity) args[0]).getName() + "\n" +
                    "Автор: " + ((OperaEntity) args[0]).getAuthor() + "\n" +
                    "Дата: " + args[1]);
        } else {
            sendMail("Была вызван метод:\n" +
                    point.getSignature() + "\n" +
                    "С параметрами: " + Arrays.stream(point.getArgs()).map(Objects::toString).collect(Collectors.joining(";")));
        }
    }

    private void sendMail(String msg) {
        System.out.println("Отправлено письмо: " + msg);
    }
}
