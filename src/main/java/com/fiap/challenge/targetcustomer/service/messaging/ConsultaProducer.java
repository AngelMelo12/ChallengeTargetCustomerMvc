package com.fiap.challenge.targetcustomer.service.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.challenge.targetcustomer.model.Consulta;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void notificar(Consulta consulta) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "notificar-consulta-exchange",
                "admin",
                objectMapper.writeValueAsString(consulta)
        );
    }
}
