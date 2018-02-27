package bootwildfly.kafka.Producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.Scanner;




/**
 * Created by Msuser on 2/7/2018.
 */
@Component
public class KafkaProducerClass {


    private static Properties configProperties;
    private static org.apache.kafka.clients.producer.Producer producer;



    private final static String TOPIC = "my-example-topic";
    private final static String BOOTSTRAP_SERVERS = "k-server:9092";


    public static void main(String... args) throws Exception {
        if (args.length == 0) {
            runProducerSecond();
        } else {
            runProducerSecond();
        }

    }


//    private static Producer<Long, String> createProducer() {
//        Properties props = new Properties();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
//        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class.getName());
//        return new KafkaProducer<>(props);
//    }
//
//    static void runProducer(final int sendMessageCount) throws Exception {
//        final Producer<Long, String> producer = createProducer();
//        long time = System.currentTimeMillis();
//
//        try {
//            for (long index = time; index < time + sendMessageCount; index++) {
//
//                // this is where the actual kafka messages are created
//                final ProducerRecord<Long, String> record =
//                        new ProducerRecord<>(TOPIC, index,
//                                "Hello Mom " + index);
//
//                RecordMetadata metadata = producer.send(record).get();
//
//                long elapsedTime = System.currentTimeMillis() - time;
//                System.out.printf("sent record(key=%s value=%s) " +
//                                "meta(partition=%d, offset=%d) time=%d\n",
//                        record.key(), record.value(), metadata.partition(),
//                        metadata.offset(), elapsedTime);
//
//            }
//        } finally {
//            producer.flush();
//            producer.close();
//        }
//
//
//    }

    public static void runProducerSecond(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "http://k-server-kafka-project-1.b9ad.pro-us-east-1.openshiftapps.com:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for(int i = 0; i < 100; i++)
            producer.send(new ProducerRecord<String, String>("my-example-topic", Integer.toString(i), Integer.toString(i)));

        producer.close();
    }




}