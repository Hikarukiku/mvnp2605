package n1;


import freemarker.cache.FileTemplateLoader;
import freemarker.core.Configurable;
import freemarker.template.Configuration;
import freemarker.template.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 14.04.2017.
 */
public class TestServlet extends HttpServlet {

    private Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
    private ToDoList list = new ToDoList();
    {list.add("");
    try{
        cfg.setTemplateLoader(new FileTemplateLoader(new File(".")));
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String what=req.getParameter("task");
        list.add(what);
        resp.sendRedirect("/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Template t = cfg.getTemplate("todo.html");
        StringBuilder buf= new StringBuilder();
        List<Smth> items = list.view();
        for (Smth item : items) {
            buf.append("<li>"+item.what+"</li>\n");
        }

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<html>\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Список дел</title>\n" +
                "    <body>\n" +
                "<form method=\"post\" action=\"add\">\n" +
                "    Задача: <input name=\"task\">\n" +
                "    <input type=\"submit\" value=\"добавить\">\n" +
                "</form>\n" +
                "<ol>\n" +buf+
                "\n" +
                "</ol>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</head>\n" +
                "</html>");
    }


}
