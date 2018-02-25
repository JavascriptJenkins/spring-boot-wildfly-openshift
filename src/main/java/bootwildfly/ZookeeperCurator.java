package bootwildfly;

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





    public static void main(String... args) throws Exception {

            initializeCuratorBuilder();

    }





    public static void initializeCuratorBuilder() throws Exception {


        // configure spring interface so these values can be passed in as an http payload
        final String zkUsername = "zookeeperUsername";
        final String zkPassword = "zookeeperPassword";
        final String connectString = "localhost:9092"; // ex: localhost:2181,127.0.0.1:2182,127.0.0.1:2183,127.0.0.1:2184
        final int retryInitialWaitMs = 10000;
        final int maxRetryCount = 10;
        final int connectionTimeoutMs = 50000;
        final int sessionTimeoutMs = 50000;




        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(new ExponentialBackoffRetry(retryInitialWaitMs, maxRetryCount))
                .connectionTimeoutMs(connectionTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs);
        /*
         * If authorization information is available, those will be added to the client. NOTE: These auth info are
         * for access control, therefore no authentication will happen when the client is being started. These
         * info will only be required whenever a client is accessing an already create ZNode. For another client of
         * another node to make use of a ZNode created by this node, it should also provide the same auth info.
         */
        if (zkUsername != null && zkPassword != null) {
            String authenticationString = zkUsername + ":" + zkPassword;
            builder.authorization("digest", authenticationString.getBytes())
                    .aclProvider(new ACLProvider() {
                        @Override
                        public List<ACL> getDefaultAcl() {
                            return ZooDefs.Ids.CREATOR_ALL_ACL;
                        }
                        @Override
                        public List<ACL> getAclForPath(String path) {
                            return ZooDefs.Ids.CREATOR_ALL_ACL;
                        }
                    });
        }

        CuratorFramework client = builder.build();

        client.start();

//        // create data storing node if you know the correct path
//        client.create().withMode(CreateMode.PERSISTENT).forPath("/your/ZNode/path");


        // create data node on the fly
        client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/your/ZNode/path");


//        client.setData().forPath("/your/ZNode/path",data);
//
//
//        client.getData().forPath("/path/to/ZNode");



    }


}
