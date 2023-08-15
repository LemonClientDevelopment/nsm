package me.chirin.ysmdumper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    public static final Logger LOG = LoggerFactory.getLogger(ZipUtil.class);
    public static final long SESSION = System.currentTimeMillis();
    private static int incremental = 0;

    public static String generate(Map<String, byte[]> in) {
        String out = "%d-%d.zip".formatted(SESSION, incremental);
        incremental++;
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(out));
            for (Map.Entry<String, byte[]> e : in.entrySet()) {
                zos.putNextEntry(new ZipEntry(e.getKey()));
                zos.write(e.getValue());
                zos.closeEntry();
            }
            zos.close();
            LOG.info("Successfully dumped model: %s");
            return out;
        } catch (Exception e) {
            LOG.error("Error dumping model: " + e);
            return null;
        }
    }
}
