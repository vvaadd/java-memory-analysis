package catmatrozkin.demo.memoryanalysis;

import static catmatrozkin.demo.memoryanalysis.Utils.MEGABYTE;

/**
 * @author Alexander Sokolov
 */
public class Heavy {

    private byte[] data;

    public Heavy() {
        this(false);
    }

    public Heavy(boolean reduce) {
        try {
            data = new byte[MEGABYTE];
        } catch (OutOfMemoryError e) {
            if (reduce) {
                data = new byte[MEGABYTE / 100];
            } else {
                throw e;
            }
        }
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
