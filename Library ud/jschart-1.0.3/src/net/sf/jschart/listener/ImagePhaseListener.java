/*
 * ImagePhaseListener.java
 *
 * Created on 19 Dec 2006, 14:42
 */

package net.sf.jschart.listener;

import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.jschart.component.Chart;

/**
 *
 * @author als
 */
public class ImagePhaseListener implements PhaseListener
{
    private final static Log log = LogFactory.getLog(ImagePhaseListener.class);
    
    /** Creates a new instance of ImagePhaseListener */
    public ImagePhaseListener()
    {
    }
    
    public final static String IMAGE_VIEW_ID = "ChartBuilder";
    public final static String IMAGE_URL = "/faces/" + IMAGE_VIEW_ID;
    
    public void afterPhase(PhaseEvent event)
    {
        FacesContext context = event.getFacesContext();
        String viewId = context.getViewRoot().getViewId();
        
        if (viewId.indexOf(IMAGE_VIEW_ID) != -1)
        {
            handleImageRequest(context);
        }
    }
    
    public void beforePhase(PhaseEvent event)
    {
        //Do nothing here...
    }
    
    public PhaseId getPhaseId()
    {
        return PhaseId.RESTORE_VIEW;
    }
    
    private void handleImageRequest(FacesContext context)
    {
        ExternalContext ec = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)ec.getRequest();
        HttpServletResponse response = (HttpServletResponse)ec.getResponse();
        HttpSession session = request.getSession();
        
        String id = request.getParameter(Chart.CHART_ID);
        
        log.debug("Image id: " + id);

        byte[] chartImage = (byte[])session.getAttribute(id);
        
        if (chartImage == null)
        {
            log.error("No chart image specified. id="+id);
            context.addMessage(null,
                    new FacesMessage("No chart image specified. id=" + id));
        }
        
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");
        response.setContentLength(chartImage.length);
        
        try
        {
            OutputStream out = response.getOutputStream();
            out.write(chartImage);
            out.close();
        }
        catch (Exception exception)
        {
            log.error("handleImageRequest", exception);
        }
        finally
        {
            session.removeAttribute(id);
        }
        
        context.responseComplete();
    }
    
}
