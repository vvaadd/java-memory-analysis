package catmatrozkin.demo.memoryanalysis;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

/**
 * @author Alexander Sokolov
 */
public class CacheDemo implements Demo {

    private static ConcurrentHashMap<String, Heavy> cache = new ConcurrentHashMap<String, Heavy>();
    private static Semaphore s = new Semaphore(20);

    public static class Task implements Runnable {
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                new CacheDemo().businessMethod();
            } catch (Exception e) {
//                e.printStackTrace();
            } finally {
                double executionTime = (double) (System.currentTimeMillis() - startTime) / 1000;
                System.out.println("Free heap: " + Utils.freeHeapMemory() + "MB, Cache size: " + cache.size() + ", Execution time: " + executionTime + " sec");
                s.release();
            }
        }
    }

    @Override
    public void start() throws Exception {
        for (;;) {
            s.acquire();
            Utils.startThread(new Task());
            Thread.sleep(10);
        }
    }

    public void businessMethod() throws Exception {
        ExternalService service = ExternalService.getInstance();
        List<String> names = service.getNames();

        // do something
        Thread.sleep(new Random().nextInt(500));

        for (String name : names) {
            Heavy heavy;
            heavy = cache.get(name);
            if (heavy == null) {
                heavy = service.findHeavyByName(name);
                cache.putIfAbsent(name, heavy);
            }
        }

        // do something
        Thread.sleep(new Random().nextInt(500));

        for (String name : names) {
            cache.remove(name);
        }

    }

}
