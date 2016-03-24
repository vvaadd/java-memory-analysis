package catmatrozkin.demo.memoryanalysis;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.util.VMSupport;

import static java.lang.System.out;

/**
 * @author Alexander Sokolov
 */
public class JolDemo implements Demo {

    public void start() throws Exception {
        out.println(VMSupport.vmDetails());
        out.println(ClassLayout.parseInstance(new OopsDemo.Entity()).toPrintable());

//        out.println(ClassLayout.parseClass(OopsDemo.Entity.class, new HotSpotLayouter(new X86_32_DataModel())).toPrintable());
//        out.println(ClassLayout.parseClass(OopsDemo.Entity.class, new HotSpotLayouter(new X86_64_DataModel())).toPrintable());
//        out.println(ClassLayout.parseClass(OopsDemo.Entity.class, new HotSpotLayouter(new X86_64_COOPS_DataModel())).toPrintable());

        // сколько будут занимать объекты?
//        out.println(ClassLayout.parseInstance(1234).toPrintable());
//        out.println(ClassLayout.parseInstance(1234L).toPrintable());
//        out.println(ClassLayout.parseInstance(true).toPrintable());
//        out.println(ClassLayout.parseInstance(new TestClass()).toPrintable());
//        out.println(ClassLayout.parseInstance(new ObjectWithStatic()).toPrintable());
//        out.println(ClassLayout.parseInstance(Integer.class).toPrintable());
    }

    public static class ObjectWithStatic {
        static Integer i = 1234;

    }

    public static class TestClass {
        OopsDemo.Entity e = new OopsDemo.Entity();
    }

}
