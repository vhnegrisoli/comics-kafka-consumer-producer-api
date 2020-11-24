package br.com.comicskafka.producerapi.modulos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
public class ComicsSender {

    @Value("${kafka.topic.comics}")
    private String comicsTopic;

    @Autowired
    private KafkaTemplate<String, ComicsDTO> kafkaTemplate;

    public void enviarComics(ComicsDTO comicsDTO) {
        log.info("Enviando o objeto '{}'", comicsDTO.toString());
        try {
            kafkaTemplate.send(comicsTopic, comicsDTO);
            log.info("Enviado com sucesso!");
        } catch (Exception ex) {
            log.error("Erro ao enviar para o o Kafka.", ex);
        }
    }
}
