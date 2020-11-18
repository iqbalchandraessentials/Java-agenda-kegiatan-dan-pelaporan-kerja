/*
 */
package net.sf.jschart.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.jschart.component.Chart;

/**
 * Servlet that responses a chart image
 */
public class Chartlet extends HttpServlet
{
    private static Log log = LogFactory.getLog(Chartlet.class);

    public final static String IMAGE_SERVLET = "Chartlet";
    public final static String IMAGE_URL = "/servlet/" + IMAGE_SERVLET;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        log.trace("doGet started");
        
        OutputStream out = response.getOutputStream();
        HttpSession session = request.getSession();
        
        String id = request.getParameter(Chart.CHART_ID);

        log.debug("Image id: " + id);

        byte[] chartImage = (byte[])session.getAttribute(id);
        
        if (chartImage == null)
        {
            log.error("No chart image specified. id="+id);
            throw new ServletException("No chart image specified. id=" + id);
        }
        
        try
        {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/png");
            response.setContentLength(chartImage.length);
            out.write(chartImage);
        }
        catch (Exception e)
        {
            log.error("doGet:", e);
        }
        finally
        {
            out.close();
            session.removeAttribute(id);
        }
        
        log.trace("doGet finished");
    }
}

