package com.itorii.kafkaDemo.producerApp;

import com.itorii.kafkaDemo.producerApp.events.TemperatureRecordPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext) SpringApplication.run(ProducerApplication.class, args);
        TemperatureRecordPublisher temperatureRecordPublisher = ctx.getBean(TemperatureRecordPublisher.class);
        DisposableServer temperatureServer =
                TcpServer.create()
                        .host("localhost")
                        .port(8989)
                        .wiretap(true)
                        .handle(
                                (i, o) -> i.receive().asString()
                                        .doOnEach(s -> temperatureRecordPublisher.fireEvent(s.get()))
                                        .then())//todo fire event and push to kafka
                        .bindNow();

        temperatureServer.onDispose()
                .block();
    }
}
