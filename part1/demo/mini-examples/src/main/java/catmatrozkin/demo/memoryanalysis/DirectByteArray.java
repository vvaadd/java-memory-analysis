package catmatrozkin.demo.memoryanalysis;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

class DirectByteArray {
        private final static int BYTE = 1;
        private long size;
        private long address;

        public DirectByteArray(long size) {
            this.size = size;
            address = getUnsafe().allocateMemory(size * BYTE);
            getUnsafe().setMemory(address, size * BYTE, (byte) 0);
        }

        public void set(long index, byte value) {
            getUnsafe().putByte(address + index * BYTE, value);
        }

        public int get(long index) {
            return getUnsafe().getByte(address + index * BYTE);
        }

        public long size() {
            return size;
        }

        public void destroy() {
            getUnsafe().freeMemory(address);
        }

        private Unsafe getUnsafe() {
            try {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }