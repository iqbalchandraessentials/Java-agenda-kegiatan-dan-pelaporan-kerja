/*
 * ChartRenderer.java
 *
 * Created on 10 July 2006, 15:45
 *
 */

package net.sf.jschart.render;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.beans.Beans;
import java.io.File;
import java.io.OutputStream;
import java.net.URI;
import javax.faces.application.FacesMessage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.print.attribute.standard.Severity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import org.jfree.data.general.Dataset;
import net.sf.jschart.component.Chart;
import net.sf.jschart.listener.ImagePhaseListener;
import net.sf.jschart.servlet.Chartlet;
import net.sf.jschart.util.ChartBuilder;

/**
 *
 * @author als
 */
public class ChartRenderer extends AbstractRenderer
{
    private static Log log = LogFactory.getLog(ChartRenderer.class);
    
    private static final String CHART_JS = Chart.WEB_RESOURCE_PREFIX
            + "chart.js";
    
    private static final String CHART_CSS = Chart.WEB_RESOURCE_PREFIX
            + "chart.css";
    
    private static final String RENDER_ONCE_KEY = Chart.class.getName()
    + "renderOnce";
    
    /** Creates a new instance of ChartRenderer */
    public ChartRenderer()
    {
    }
    
    // @Override
    public boolean getRendersChildren()
    {
        // Our encodeChildren() method will be called if true
        return false;
    }
    
    /**
     * <p>
     * Render the start of the element.
     * </p>
     */
    protected void renderStart(FacesContext context, UIComponent component,
            ResponseWriter writer) throws IOException
    {
        // Render markup only once
        Map requestMap = context.getExternalContext().getRequestMap();
        Boolean alreadyRendered = (Boolean) requestMap.get(RENDER_ONCE_KEY);
        if (alreadyRendered == null)
        {
            writer.startElement("link", component);
            writer.writeAttribute("href", CHART_CSS, null);
            writer.writeAttribute("type", "text/css", null);
            writer.writeAttribute("rel", "stylesheet", null);
            writer.endElement("link");
            
            // Write out supporting javascript
/*            String url = context.getApplication().getViewHandler()
            .getResourceURL(context, CHART_JS);
            renderScriptInclude(writer, component, url);
 */
            requestMap.put(RENDER_ONCE_KEY, Boolean.TRUE);
        }
    }
    
    /**
     * <p>
     * Render the end of the element.
     * </p>
     */
    protected void renderEnd(FacesContext context, UIComponent component,
            ResponseWriter writer) throws IOException
    {
        ChartRenderingInfo info = null;
        String path = context.getExternalContext().getRequestContextPath();
        Map session = context.getExternalContext().getSessionMap();
        
        String key = context.getViewRoot().getViewId() + component.getClientId(context);
        String id = String.valueOf(key.hashCode());
        
        log.debug("Chart id=" + id);
        
        try
        {
            writer.startElement("img", component);
            
            if (component == null)
            {
                Throwable thr = new Throwable("ChartRenderer.renderEnd: component==null");
                thr.printStackTrace();
                return;
            }
            
            if (!(component instanceof Chart))
            {
                log.error("ChartRenderer.renderEnd: Invalid component: " +
                        component.getClass().getName());
                
                writer.writeAttribute("width", new Integer(400), "width");
                writer.writeAttribute("height", new Integer(300), "height");
                writer.writeAttribute("alt", "Invalid chart component: " +
                        component.getClass().getName() , null);
                
                return;
            }
            
            Chart chart = (Chart)component;
            Dataset ds = null;
            
            if (!Beans.isDesignTime())
            {
                ds = chart.getDatasource();
                
                if (log.isTraceEnabled())
                    log.trace("renderEnd> datasource: " + ds);
            }
            
            int width = chart.getWidth();
            int height = chart.getHeight();
            
            writer.writeAttribute("id", id, "id");
            writer.writeAttribute("width", new Integer(width), "width");
            writer.writeAttribute("height", new Integer(height), "height");
            
            JFreeChart jfc = ChartBuilder.buildChart(chart, ds);
            OutputStream out = null;
            URI chartUri = null;
            if (Beans.isDesignTime())
            {
                String tmpdir = System.getProperty("java.io.tmpdir");
                File base = new File(tmpdir);
                String username = System.getProperty("user.name");
                String fname = username + id + ".png";
                File chartFile = new File(base, fname);
                out = new FileOutputStream(chartFile);
                chartUri = chartFile.toURI();
            }
            else
            {
                out = new ByteArrayOutputStream();
                chartUri = new URI(path + (chart.isUseChartlet() ?
                    Chartlet.IMAGE_URL : ImagePhaseListener.IMAGE_URL)
                    + "?" + Chart.CHART_ID + "=" + id);
            }
            
            if (chart.isTooltips())
                info = new ChartRenderingInfo();
            
            ChartUtilities.writeChartAsPNG(out, jfc, width, height, info);
            
            if (Beans.isDesignTime())
            {
                out.close();
            }
            else
            {
                ByteArrayOutputStream ba = (ByteArrayOutputStream)out;
                session.put(id, ba.toByteArray());
            }

            log.debug("src="+chartUri.toString());

            writer.writeAttribute("src", chartUri.toString(), null);
            
            writer.writeAttribute("alt",
                    "Chart: " + chart.getTitle(), null);
            
            StringBuilder sb = new StringBuilder("chart");
            String styleClass = chart.getStyleClass();
            if (styleClass != null && styleClass.length() > 0)
            {
                sb.append(' ');
                sb.append(styleClass);
            }
            writer.writeAttribute("class", sb.toString(), "styleClass");
            
            String style = chart.getStyle();
            if (style != null && style.length() > 0)
            {
                writer.writeAttribute("style", style, "style");
            }
            
            if (chart.getBorder() > 0)
                writer.writeAttribute("border", chart.getBorder(), "border");
            
            if (!Beans.isDesignTime() && info != null)
                writer.writeAttribute("usemap", "#map" + id, null);
        }
        catch (Exception e)
        {
            log.error("ChartRenderer.renderEnd: ", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Unexpected exception!", e.toString()));
        }
        finally
        {
            writer.endElement("img");
        }
        
        if (!Beans.isDesignTime() && info != null)
        {
            writer.write("\n");
            String imageMap = ChartUtilities.getImageMap("map" + id, info);
            writer.write(imageMap);
            writer.write("\n");
        }
    }
}
