package bootwildfly.kafka.Consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.errors.WakeupException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;

public class KafkaConsumerClass {

    private final static String TOPIC = "my-example-topic";

    public static void main()throws Exception{
        runConsumer();
    }

    private static Consumer<Long, String> createConsumer(){
        Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "sampleGroupID");


        // Create the consumer using props.
        final Consumer<Long, String> consumer =
                new KafkaConsumer<>(configProperties);

        // Subscribe to the topic.
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer;
    }



    static void runConsumer() throws InterruptedException {
        final Consumer<Long, String> consumer = createConsumer();

        final int giveUp = 100;   int noRecordsCount = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords =
                    consumer.poll(1000);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }

            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
            });

            // this commits the processed record offset back to the broker
            consumer.commitAsync();
        }
        consumer.close();
        System.out.println("DONE");
    }



}