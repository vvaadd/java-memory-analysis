package catmatrozkin.demo.memoryanalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alexander Sokolov
 */
public class ExternalService {

    private static AtomicInteger callCount = new AtomicInteger();

    public List<String> getNames() {
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < new Random().nextInt(1000); i++) {
            names.add(String.valueOf(new Random().nextInt(5000)));
        }
        return names;
    }

    public Heavy findHeavyByName(String name) {
        try {
            Thread.sleep(new Random().nextInt(50) * callCount.getAndIncrement());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Heavy heavy = new Heavy();
        // Если не уменьшать heavy, тогда размер cache не будет увеличиваться из-за постоянного OOM
        Heavy heavy = name.length() == 2 ? null : new Heavy(true);
        callCount.getAndDecrement();
        return heavy;
    }

    public static ExternalService getInstance() {
        return new ExternalService();
    }

}
