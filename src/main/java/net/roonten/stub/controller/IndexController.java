package net.roonten.stub.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private static Log log = LogFactory.getLog(IndexController.class);

    @RequestMapping("/index.html")
    public void openIndex(HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
        log.info("桩服务启动页 ");
        try {
            PrintWriter out = response.getWriter();
            out.println("<h1 style='color:#FF0000'>桩服务启动页</h1><br/>");
            out.println("<h3>CONTEXT_PATH/stub/{i}，i根据conf/stub-config.properties的配置，从0开始。</h3><br/>");
            out.println("<h3>详情请查看 README.md</h3><br/>");
        } catch (Throwable ex) {
            log.warn(ex.getMessage(), ex);
        }
    }
}
