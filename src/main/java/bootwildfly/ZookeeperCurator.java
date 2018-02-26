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


// https://curator.apache.org/getting-started.html
    public static CuratorFramework initializeCuratorBuilder() throws Exception {

        final String connectString = "zookeeper-server.kafka-project-1.svc:2181"; // ex: zookeeper:2181

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retryPolicy);
        client.start();


        return client;


    }


}
