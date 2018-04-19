package net.roonten.stub.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.roonten.stub.utils.IOUtils;

@Controller
public class StubController {

    private static Log log = LogFactory.getLog(StubController.class);

    private static Properties properties = new Properties();

    private static final Object lock = new Object();

    private static boolean reloading = false;

    static {
        new Thread() {
            public void run() {
                while (true) {
                    synchronized (lock) {
                        reloading = true;
                        try {
                            properties.clear();
                            properties.load(StubController.class.getResourceAsStream("/conf/stub-config.properties"));
                        } catch (Throwable ex) {
                        }
                        reloading = false;
                        lock.notifyAll();
                    }
                    try {
                        // 5秒更新一次配置
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();
    }

    @RequestMapping("/stub/{i}")
    public void doStubIndex(@PathVariable("i") String i, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (reloading) {
            synchronized (lock) {
                while (reloading) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }
        }

        String contentType = properties.getProperty("stub[" + i + "].response.contentType").intern();
        if (contentType == null || contentType.trim().intern().length() == 0) {
            response.sendError(404, "stub[" + i + "].response.contentType 未配置，请检查 conf/stub-config.properties");
            return;
        }
        String data = properties.getProperty("stub[" + i + "].response.data");
        if (data == null || data.trim().length() == 0) {
            response.sendError(404, "stub[" + i + "].response.data 未配置，请检查 conf/stub-config.properties");
            return;
        }
        log.info("stub/" + i + " 收到一个请求： response.contentType=" + contentType + ", response.data=" + data);

        byte[] b;
        if (data.startsWith("[BINARY:]")) {
            b = Base64.decodeBase64(data.substring(9).trim());
        } else {
            b = data.getBytes(Charsets.UTF_8);
        }

        response.setContentType(contentType);
        try {
            IOUtils.output(b, response.getOutputStream());
        } catch (Throwable ex) {
            log.warn(ex.getMessage(), ex);
        }
    }
}
