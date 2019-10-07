package com.itorii.kafkaDemo.producerApp;

import com.itorii.kafkaDemo.producerApp.events.SensorDataPublisher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

@SpringBootApplication
public class ProducerApplication {
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext ctx = (AnnotationConfigServletWebServerApplicationContext) SpringApplication.run(ProducerApplication.class, args);
        SensorDataPublisher sensorDataPublisher = ctx.getBean(SensorDataPublisher.class);
        DisposableServer temperatureServer =
                TcpServer.create()
                        .host("localhost")
                        .port(8989)
                        .wiretap(true)
                        .handle(
                                (i, o) -> i.receive().asString()
                                        .doOnEach(s -> sensorDataPublisher.fireEvent(s.get()))
                                        .then())
                        .bindNow();

        temperatureServer.onDispose()
                .block();
    }
}
