package br.com.comicskafka.consumerapi.modulos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
public class ComicsListener {

    @KafkaListener(groupId = "comics.topic", topics = "${kafka.topic.comics}")
    public void receberComics(ComicsDTO comicsDTO) {
        log.info("Recebido o objeto '{}'", comicsDTO);
    }
}
