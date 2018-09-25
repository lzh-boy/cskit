package com.cskit.utils.kafkautils;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * @author Micro
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/8/9 10:25
 */
@ConditionalOnProperty(name = "spring.kafka.producer.client-id")
@Component
public class KafkaProducerClient<K> {
    private KafkaProducer<K, String> producer;
    private ProducerRecord<K, String> producerRecord;
    private Properties props;

    private ProducerConfig producerConfig;

    @Autowired
    public KafkaProducerClient(ProducerConfig producerConfig) {
        this.producerConfig = producerConfig;
        init();
    }

    void init() {
        props = new Properties();
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerConfig.getBootstrapservers());
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.CLIENT_ID_CONFIG, producerConfig.getClientid());
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.BATCH_SIZE_CONFIG, String.valueOf(producerConfig.getBatchsize()));
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.RETRIES_CONFIG, String.valueOf(producerConfig.getRetries()));
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.BUFFER_MEMORY_CONFIG, String.valueOf(producerConfig.getBuffermemory()));
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerConfig.getKeyserializer());
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerConfig.getValueserializer());
        props.setProperty(org.apache.kafka.clients.producer.ProducerConfig.ACKS_CONFIG, producerConfig.getAcks());
        producer = new KafkaProducer<K, String>(props);
    }

    /**
     * 发送数据
     *
     * @param topic
     * @param content
     */
    public void send(String topic, K key, String content) {
        producerRecord = new ProducerRecord<K, String>(topic, key, content);
        producer.send(producerRecord);
    }

    /**
     * 发送数据并处理返回值
     *
     * @param topic
     * @param content
     * @param consumer
     */
    public void send(String topic, String content, Consumer<Future<RecordMetadata>> consumer) {
        producerRecord = new ProducerRecord<K, String>(topic, content);
        consumer.accept(producer.send(producerRecord));
    }

    /**
     * 发送数据并Callback
     *
     * @param topic
     * @param content
     * @param callback
     */
    public void send(String topic, String content, Callback callback) {
        producerRecord = new ProducerRecord<K, String>(topic, content);
        producer.send(producerRecord, callback);
    }

    public void close() {
        producer.close();
    }

}
