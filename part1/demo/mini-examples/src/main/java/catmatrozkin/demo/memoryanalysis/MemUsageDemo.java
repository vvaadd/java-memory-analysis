package catmatrozkin.demo.memoryanalysis;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import static catmatrozkin.demo.memoryanalysis.Utils.MEGABYTE;

/**
 * @author Alexander Sokolov
 */
public class MemUsageDemo implements Demo {

    @Override
    public void start() throws Exception {
        byte[] intArray = new byte[MEGABYTE * 100];

        Runtime runtime = Runtime.getRuntime();
        System.out.println("Runtime maxMemory: " + runtime.maxMemory() / MEGABYTE);
        System.out.println("Runtime totalMemory(): " + runtime.totalMemory() / MEGABYTE);
        System.out.println("Runtime freeMemory(): " + runtime.freeMemory() / MEGABYTE);

        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memBean.getNonHeapMemoryUsage();

        System.out.println("JMX heap init: " + heapMemoryUsage.getInit() / MEGABYTE);
        System.out.println("JMX heap max: " + heapMemoryUsage.getMax() / MEGABYTE);
        System.out.println("JMX heap committed: " + heapMemoryUsage.getCommitted() / MEGABYTE);
        System.out.println("JMX heap used: " + heapMemoryUsage.getUsed() / MEGABYTE);

        System.out.println("JMX non-heap init: " + nonHeapMemoryUsage.getInit() / MEGABYTE);
        System.out.println("JMX non-heap max: " + nonHeapMemoryUsage.getMax() / MEGABYTE);
        System.out.println("JMX non-heap committed: " + nonHeapMemoryUsage.getCommitted() / MEGABYTE);
        System.out.println("JMX non-heap used: " + nonHeapMemoryUsage.getUsed() / MEGABYTE);
    }
}
