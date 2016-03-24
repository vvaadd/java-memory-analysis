package catmatrozkin.demo.memoryanalysis;

import com.sun.management.HotSpotDiagnosticMXBean;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;

/**
 * @link https://blogs.oracle.com/sundararajan/entry/programmatically_dumping_heap_from_java
 */
public class HeapDumpDemo implements Demo {

    private static final String HOTSPOT_BEAN_NAME = "com.sun.management:type=HotSpotDiagnostic";

    @Override
    public void start() throws Exception {
        for (int i = 0; i < 10000; i++) {
            new String(Utils.getRandomString());
        }
        HotSpotDiagnosticMXBean hotspotMBean = getHotspotMBean();
        hotspotMBean.dumpHeap("demo.hprof", true); // second parameter - dump only the live objects
    }

    private static HotSpotDiagnosticMXBean getHotspotMBean() {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            return ManagementFactory.newPlatformMXBeanProxy(server, HOTSPOT_BEAN_NAME, HotSpotDiagnosticMXBean.class);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception exp) {
            throw new RuntimeException(exp);
        }
    }

}
