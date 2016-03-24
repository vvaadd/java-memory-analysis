package catmatrozkin.demo.memoryanalysis;

/**
 * @author Alexander Sokolov
 */
public class InternStringDemo implements Demo {

    @Override
    public void start() throws Exception {
        for (;;) {
            Utils.getRandomString().intern();
        }
    }

}
