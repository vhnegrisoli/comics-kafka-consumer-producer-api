package br.com.comicskafka.consumerapi.config;

import br.com.comicskafka.consumerapi.modulos.ComicsDTO;
import br.com.comicskafka.consumerapi.modulos.ComicsListener;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        var propriedades = new HashMap<String, Object>();
        propriedades.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        propriedades.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        propriedades.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return propriedades;
    }

    @Bean
    public ConsumerFactory<String, ComicsDTO> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
            consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(ComicsDTO.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ComicsDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ComicsDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ComicsListener listener() {
        return new ComicsListener();
    }
}
