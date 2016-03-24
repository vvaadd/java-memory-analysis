package catmatrozkin.demo.memoryanalysis;

public class App {

    public static void main(String[] args) throws Exception {
        createDemoApp().start();
    }

    public static Demo createDemoApp() {
        return new OopsDemo(); //-Xmx1g, -Xmx33gb
//        return new JolDemo(); // -Xmx1g, -Xmx5g, -XX:-UseCompressedOops
//        return new CacheDemo(); // -Xmx1g, use VisualVM
//        return new InnerThreadLocalDemo(); // -Xmx1g

        // unused
//        return new MemUsageDemo(); // -Xmx1g
//        return new FinalizeDemo(); // -Xmx100m
//        return new InternStringDemo(); // Java 6 and Java 7
//        return new NativeErrorDemo();
//        return new HeapDumpDemo();
    }

}
