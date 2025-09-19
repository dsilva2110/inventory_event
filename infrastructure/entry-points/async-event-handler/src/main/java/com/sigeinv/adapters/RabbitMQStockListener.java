package com.sigeinv.adapters;

import com.sigeinv.handlers.SalesCreatedHandler;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Component
public class RabbitMQStockListener {

    private final SalesCreatedHandler salesCreatedHandler;

    @RabbitListener(queues = "inventory.stock.queue")
    public Mono<Void> handleStockEvent(String messageBody, @Header(AmqpHeaders.RECEIVED_ROUTING_KEY) String routingKey ) {

        System.out.println("-> Evento Recibido: [" + routingKey + "]");
        System.out.println("   Cuerpo del mensaje: " + messageBody);

        switch (routingKey) {
            case "ventas.ordenes.creadas":
                System.out.println("Creadas");
                return salesCreatedHandler.reduceStockStore(messageBody).then();
            case "inventario.ajuste.stock":
                System.out.println("ajuste realizado");
                //llamado a handler
                return Mono.empty();
            case "inventario.recepcion.productos":
                System.out.println("rcepcion");
                //llamado a handler
                return Mono.empty();
            default:
                System.out.println("ERROR: Routing Key no reconocida: " + routingKey);
                return Mono.empty();
        }

    }
}
