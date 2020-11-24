package br.com.comicskafka.consumerapi.modulos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class ComicsListener {

    @KafkaListener(
        groupId = "kafka_group",
        topics = "${kafka.topic.comics}",
        containerFactory = "kafkaListenerContainerFactory"
    )
    public void receberComics(ComicsDTO comicsDTO) {
        try {
            log.info("Recebido o objeto '{}'", comicsDTO);
        } catch (Exception ex) {
            log.error("Erro ao receber dados do tópico.", ex);
        }
    }
}
