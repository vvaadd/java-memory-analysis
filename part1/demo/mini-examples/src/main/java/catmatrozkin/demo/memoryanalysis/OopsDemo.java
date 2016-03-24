package catmatrozkin.demo.memoryanalysis;


import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexander Sokolov
 */
public class OopsDemo implements Demo {

    public static List<Entity> entities = new LinkedList<Entity>();

    @Override
    public void start() throws Exception {
        System.out.println("Heap size before: " + Utils.usedHeapMemory() + " MB");
        for (int i = 0; i < 10 * 1000 * 200; i++) {
            entities.add(new Entity());
        }
        System.out.println("Heap size after: " + Utils.usedHeapMemory() + " MB");
    }

    public static class Entity {
        public Integer i1 = 1;
        public Double d1;
        public String s1;
        public String s2;

        // Увеличиться ли размер heap?
//        public String s3;
    }

}