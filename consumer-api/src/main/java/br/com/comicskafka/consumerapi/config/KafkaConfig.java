package br.com.comicskafka.consumerapi.config;

import br.com.comicskafka.consumerapi.modulos.ComicsDTO;
import br.com.comicskafka.consumerapi.modulos.ComicsListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        var propriedades = new HashMap<String, Object>();
        propriedades.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        propriedades.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka_group");
        propriedades.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return propriedades;
    }

    @Bean
    public ConsumerFactory<String, ComicsDTO> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
            consumerConfigs(), new StringDeserializer(), getJsonDeserializerConfig()
        );
    }

    private JsonDeserializer<ComicsDTO> getJsonDeserializerConfig() {
        var jsonDeserializer = new JsonDeserializer<>(ComicsDTO.class);
        jsonDeserializer.setRemoveTypeHeaders(true);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeMapperForKey(true);
        return jsonDeserializer;
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
