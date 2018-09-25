package com.cskit.utils.kafkautils;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/8/9 10:26
 */
@ConditionalOnProperty(name = "spring.kafka.consumer.group-id")
@Component
public class KafkaConsumerClient<K> {
    private KafkaConsumer<K, String> kafkaConsumer;
    Properties props = null;
    private com.cskit.utils.kafkautils.ConsumerConfig consumerConfig;

    @Autowired
    public KafkaConsumerClient(com.cskit.utils.kafkautils.ConsumerConfig consumerConfig) {
        this.consumerConfig = consumerConfig;
        init();
    }

    void init() {
        props = new Properties();
        props.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerConfig.getBootstrapservers());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerConfig.getGroupid());
        props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerConfig.getEnableautocommit());
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerConfig.getAutooffsetreset());
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, consumerConfig.getKeydeserializer());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, consumerConfig.getValuedeserializer());
        props.setProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, String.valueOf(consumerConfig.getSessiontimeout()));
        props.setProperty(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, String.valueOf(consumerConfig.getRequesttimeout()));
        props.setProperty(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, String.valueOf(consumerConfig.getHeartbeatinterval().toMillis()));
        props.setProperty(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, String.valueOf(consumerConfig.getHeartbeatinterval().toMillis()));
        props.setProperty(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, String.valueOf(consumerConfig.getMaxpartitionfetchbytes()));
        kafkaConsumer = new KafkaConsumer<K, String>(props);
    }

    public void receive(List<String> topic, long timeOut, Consumer<ConsumerRecords<K, String>> predicate) {
        kafkaConsumer.subscribe(topic);
        while (true) {
            ConsumerRecords<K, String> records = kafkaConsumer.poll(timeOut);   //500
            if (!records.isEmpty()) {
                predicate.accept(records);
                kafkaConsumer.commitSync();
            }
        }
    }
}
