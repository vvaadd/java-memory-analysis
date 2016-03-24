package catmatrozkin.demo.memoryanalysis;

import java.util.ArrayList;
import java.util.List;

import static catmatrozkin.demo.memoryanalysis.Utils.MEGABYTE;


/**
 * @author Alexander Sokolov
 */
public class FinalizeDemo implements Demo {

    private static List<Callback> list = new ArrayList<Callback>();

    public void start() throws Exception{
        for (;;) {
//            new SomeClass(); // Not OOM
            new SomeFinalizableClass(); // OOM
//            new SomeFinalizablePersistableClass(); // OOM
        }
    }

    static class SomeClass {
        byte[] data = new byte[50 * MEGABYTE];
    }

    static class SomeFinalizableClass {
        byte[] data = new byte[50 * MEGABYTE]; // OOM
//        byte[] data = new byte[5 * MEGABYTE]; // Not OOM

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

    static class SomeFinalizablePersistableClass {
        byte[] data = new byte[5 * MEGABYTE];

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            FinalizeDemo.save(new Callback() {
                public void businessMethod() {}
            });
        }
    }

    public static void save(Callback o) {
        FinalizeDemo.list.add(o);
    }

    public interface Callback {}

}
