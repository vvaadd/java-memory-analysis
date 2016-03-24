package catmatrozkin.demo.memoryanalysis;

/**
 * Based on @link https://habrahabr.ru/company/maxifier/blog/218313/
 */
public class InnerThreadLocalDemo implements Demo {

    private Heavy heavy = new Heavy();

    private ThreadLocal<Inner> holder = new ThreadLocal<Inner>(); // OOM
//    private static ThreadLocal<Inner> holder = new ThreadLocal<Inner>(); // Not OOM

    public class Inner {} // OOM
//    public interface Inner {} // OOM
//    public static class Inner {} // Not OOM

    @Override
    public void start() throws Exception {
        for (;;) {
//            new InnerThreadLocalDemo();
            new InnerThreadLocalDemo().getInstance();
        }
    }

    public Inner getInstance() {
        Inner inner = holder.get();
        if (inner == null) {
            inner = new Inner();
//            inner = new Inner() {}; // OOM with static Inner
            holder.set(inner);
        }
        return inner;
    }

}
