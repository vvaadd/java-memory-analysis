package catmatrozkin.demo.memoryanalysis;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.UUID;

/**
 * @author Alexander Sokolov
 */
public class Utils {

    public static final int MEGABYTE = 1024 * 1024;

    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }

    public static void startThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof Error) {
                    e.printStackTrace();
//                    System.exit(1);
                }
            }
        });
        thread.start();
    }

    public static long freeHeapMemory() {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean() ;
        MemoryUsage heap = memBean.getHeapMemoryUsage();
        return  (heap.getMax() - heap.getUsed()) / MEGABYTE;
    }

    public static long usedHeapMemory() {
        MemoryMXBean memBean = ManagementFactory.getMemoryMXBean() ;
        return memBean.getHeapMemoryUsage().getUsed() / MEGABYTE;
    }

}
