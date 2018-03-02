package top.yeonon.security.core.social;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 16:52
 **/
public class YeononConnectionView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        if (model.get("connections") == null) {
            response.getWriter().write("<h3>解绑成功<h3>");
        } else {
            response.getWriter().write("<h3>绑定成功<h3>");
        }
    }
}
