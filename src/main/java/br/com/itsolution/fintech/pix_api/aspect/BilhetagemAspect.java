package br.com.itsolution.fintech.pix_api.aspect;

import br.com.itsolution.fintech.pix_api.annotation.Bilhetar;
import br.com.itsolution.fintech.pix_api.dto.bilhetagem.Bilhetavel;
import br.com.itsolution.fintech.pix_api.dto.bilhetagem.BillingMessageDto;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

@Aspect
@Component
public class BilhetagemAspect {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.billing.queue}")
    private String billingQueue;

    /*

    @Pointcut("execution(* *(.., br.com.itsolution.fintech.pix_api.dto.bilhetagem.Bilhetavel, ..)) && @annotation(bilhetar)")
    public void bilhetavelMethod(Bilhetar bilhetar) {}

    @AfterReturning(value = "bilhetavelMethod(bilhetar)", argNames = "bilhetar,dto", returning = "retorno")
    public void enviarParaFila(Bilhetar bilhetar, Bilhetavel dto, Object retorno) {
        BillingMessageDto mensagem = new BillingMessageDto();
        mensagem.setCnpj(dto.getCnpj());
        mensagem.setAppId(dto.getAppId());
        mensagem.setEndpoint(dto.getEndpoint());

        System.out.println("Bilhetou...");

        rabbitTemplate.convertAndSend(billingQueue, mensagem);
    }
     */

    @AfterReturning(value = "@annotation(bilhetar)", returning = "retorno")
    public void enviarParaFila(JoinPoint joinPoint, Bilhetar bilhetar, Object retorno) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof Bilhetavel dto) {
                BillingMessageDto mensagem = new BillingMessageDto();
                mensagem.setCnpj(dto.getCnpj());
                mensagem.setAppId(dto.getAppId());
                mensagem.setEndpoint(dto.getEndpoint());
                rabbitTemplate.convertAndSend(billingQueue, mensagem);
            }
        }
    }
}
