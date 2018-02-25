package bootwildfly;

import bootwildfly.kafka.Consumer.KafkaConsumerClass;
import bootwildfly.kafka.KafkaBroker;
import bootwildfly.kafka.Producer.KafkaProducerClass;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWildFlyController {


    @RequestMapping("hello")
    public String sayHello(){
        return ("Hello, SpringBoot on Wildfly");
    }


    @RequestMapping("initializeKafkaConsumer")
    public String initializeKafkaConsumer() throws Exception {

        // wrap this in a try catch
        // passing in hardcoded values for now
        KafkaConsumerClass.main();



        return ("Kafka consumer thread initialized!!");
    }



    @RequestMapping("produceKafkaMessage")
    public String produceKafkaMessage() throws Exception {

        // wrap this in a try catch
        // passing in hardcoded values for now
        KafkaProducerClass.main();



        return ("Trigger Kafka Message Producer!");
    }



    @RequestMapping("initializeZookeeperCurator")
    public String initializeZookeeperCurator() throws Exception {

        // wrap this in a try catch
        // passing in hardcoded values for now
        ZookeeperCurator.main();



        return ("Initialized Curator Zookeeper!");
    }

    @RequestMapping("initializeKafkaBroker")
    public String initializeKafkaBroker() throws Exception {

        // wrap this in a try catch
        // passing in hardcoded values for now
        KafkaBroker.main();



        return ("Initialized KafkaBroker!");
    }








}