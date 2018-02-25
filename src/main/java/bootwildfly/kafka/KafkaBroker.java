package bootwildfly.kafka;


import kafka.admin.AdminClient;
import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.apache.kafka.clients.admin.*;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class KafkaBroker {


    // String[] args
    public static void main() {
        String zookeeperConnect = "zookeeper:2181";
        int sessionTimeoutMs = 10 * 1000;
        int connectionTimeoutMs = 8 * 10000;

        String topic = "my-example-topic";
        int partitions = 2;
        int replication = 1;
        Properties topicConfig = new Properties(); // add per-topic configurations settings here

        // Note: You must initialize the ZkClient with ZKStringSerializer.  If you don't, then
        // createTopic() will only seem to work (it will return without error).  The topic will exist in
        // only ZooKeeper and will be returned when listing topics, but Kafka itself does not create the
        // topic.
        ZkClient zkClient = new ZkClient(
                zookeeperConnect,
                sessionTimeoutMs,
                connectionTimeoutMs,
                ZKStringSerializer$.MODULE$);

        // Security for Kafka was added in Kafka 0.9.0.0
        boolean isSecureKafkaCluster = false;

        ZkUtils zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperConnect), isSecureKafkaCluster);
        AdminUtils.createTopic(zkUtils, topic, partitions, replication, topicConfig, RackAwareMode.Enforced$.MODULE$);
        zkClient.close();




    }


}




