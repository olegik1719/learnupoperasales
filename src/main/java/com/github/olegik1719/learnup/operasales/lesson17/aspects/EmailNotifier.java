package com.github.olegik1719.learnup.operasales.lesson17.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmailNotifier {
    /**
     * 1) Покупка билета
     * 2) Анонс мероприятия
     * 3) Перенос мероприятия
     */

    @Pointcut("execution(public * com.github.olegik1719.learnup.operasales.lesson17.services.SalesService.buyTicket(..))")
    public void ticketBuyNotifier() {}


    @Around("ticketBuyNotifier()")
    public Integer aroundBuyTicket(ProceedingJoinPoint point) {
        String methodName = point.getSignature().getName() + "()";
        sendMail("Готовимся к выполнению метода " + methodName);

        try {
            Integer idTicket = (Integer) point.proceed();
            sendMail("Продан билет с номером " + idTicket);
            return idTicket;
        } catch (Throwable throwable) {
            sendMail("Не удалось продать билет: " + throwable.getMessage());
            throw new RuntimeException(throwable);
        }
    }

    private void sendMail(String msg){
        System.out.println("Отправлено письмо: " + msg);
    }
}
