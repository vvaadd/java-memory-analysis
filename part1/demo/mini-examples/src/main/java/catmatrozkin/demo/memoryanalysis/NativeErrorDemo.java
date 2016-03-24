package catmatrozkin.demo.memoryanalysis;

/**
 * @author Alexander Sokolov
 */
public class NativeErrorDemo implements Demo {

    @Override
    public void start() throws Exception {
        DirectByteArray data = new DirectByteArray(Utils.MEGABYTE);
        data.destroy();
        data.get(10);
    }
}
