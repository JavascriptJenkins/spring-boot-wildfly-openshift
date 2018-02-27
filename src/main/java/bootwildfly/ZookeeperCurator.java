package bootwildfly;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ZookeeperCurator {


    private static CuratorFramework client;



    public static void main(String... args) throws Exception {

        // initialize the client object for later use
         client = initializeCuratorBuilder();

    }

    /* This method will successfully initiate a Zookeeper curator client
    *
    * Apace Curator is designed to make the management of multiple zookeeper instances easier.
    * You can manage Znodes in your zookeeper cluster.
    *
    * */
    // https://curator.apache.org/getting-started.html
    // https://blog.cloudera.com/blog/2013/05/zookeeper-made-simpler/
    // https://medium.com/@Imesha94/apache-curator-in-5-minutes-8a2f91aff06f
    public static CuratorFramework initializeCuratorBuilder() throws Exception {

        final String connectString = "zookeeper-server.kafka-project-1.svc:2181"; // ex: zookeeper:2181

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
        client.start();


        return client;


    }


}
