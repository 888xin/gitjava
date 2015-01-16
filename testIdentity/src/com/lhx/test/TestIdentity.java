package com.lhx.test;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.lifeix.identity.service.IdentityService;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by lhx on 15-1-16 上午10:13
 *
 * @project gitjava
 * @package com.lhx.test
 * @Description
 * @blog http://blog.csdn.net/u011439289
 * @email 888xin@sina.com
 * @github https://github.com/888xin
 */
public class TestIdentity {

    private static IdentityService service;
    private static int threadNum;
    private static long[] everyThreadTime ;

    public TestIdentity(int threadNum) {
        ReferenceConfig<IdentityService> reference = new ReferenceConfig<IdentityService>();
        ApplicationConfig application = new ApplicationConfig();
        application.setName("identity");
        // 连接注册中心配置
        List<RegistryConfig> rcList = new ArrayList<RegistryConfig>();
        String zkAddress = "192.168.2.144:2181";
        String[] addrArray = zkAddress.split(",");
        for (int i = 0; i < addrArray.length; i++) {
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(zkAddress);
            registry.setProtocol("zookeeper");
            registry.setTimeout(100000);
            rcList.add(registry);
        }
        // 引用远程服务
        reference.setTimeout(100000);
        reference.setApplication(application);
        reference.setRegistries(rcList); // 多个注册中心可以用setRegistries()
        reference.setInterface(IdentityService.class);
        reference.setVersion("1.0.0");
        // 和本地bean一样使用xxxService
        service = reference.get(); //
        //开启threadNum个线程
        this.threadNum = threadNum;
        everyThreadTime = new long[threadNum];
    }

    //循环次数
    static int loopNum = 100000 ;

    public static class Thread1 extends Thread {
        private CountDownLatch runningThreadNum;

        public Thread1(CountDownLatch runningThreadNum) {
            this.runningThreadNum = runningThreadNum;
        }

        @Override
        public void run() {

            long startTimeThread = System.currentTimeMillis() ;
            for (int i = 0; i < loopNum; i++) {
                String str = "first,service,name034" + i ;
                service.incrementAndGet(str).getData();
            }
            long endTimeThread = System.currentTimeMillis();
            long timeCast = endTimeThread - startTimeThread ;
            everyThreadTime[(int)runningThreadNum.getCount()-1] = timeCast ;
            runningThreadNum.countDown();//正在运行的线程数减一
        }
    }

    public static void printString(String str, String path){

        try {
            OutputStream os = new FileOutputStream(path);
            os.write(str.getBytes());
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        myRunTest(args[0],args[1]);
    }

    private static void myRunTest(String threadNumStr, String pathStr) {
        long starttime = System.currentTimeMillis();
        int threadNum = 1 ;
        if (!"".equals(threadNumStr) && threadNumStr != null){
            threadNum = Integer.valueOf(threadNumStr);
        }
        new TestIdentity(threadNum);
        long endTime1 = System.currentTimeMillis();
        CountDownLatch runningThreadNum = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread1(runningThreadNum).start();
        }
        try {
            runningThreadNum.await();
            long initTime = endTime1 - starttime;
            //总耗时
            long castTime = System.currentTimeMillis() - endTime1;
            //吞吐量
            double iops = (double)(loopNum * threadNum *1000) / castTime ;
            long sum = 0 ;
            for (long l : everyThreadTime) {
                sum += l ;
            }
            //平均时延
            double everyThreadRequestTime = (double)Math.round((double)sum/loopNum/threadNum*1000)/1000;
            String str = "initTime:" + initTime +" costAllTime:" + castTime +  "  avgTime:" + everyThreadRequestTime + "  iops:" + iops ;
            printString(str,pathStr);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
