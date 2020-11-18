/*
 * Chart.java
 *
 * Created on 10 July 2006, 15:32
 *
 */

package net.sf.jschart.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.general.Dataset;

/**
 *
 * @author als
 */
public class Chart extends ChartBase
{
    private static Log log = LogFactory.getLog(Chart.class);

    public static final String CHART_ID = "chartId";

    /** Top level webapp context relative path for our web resources */
    public static final String WEB_RESOURCE_PREFIX;
    static
    {
        // Compose a context relative resource path using our main package name
        String packageName = Chart.class.getPackage().getName();
        int lastDot = packageName.lastIndexOf('.');
        String mainPackage = packageName.substring(0, lastDot);
        // "/" means context relative + "resources/" + "net.sf.jschart" + "/"
        WEB_RESOURCE_PREFIX = "resources/" + mainPackage + "/";
    }
    
    /** Creates a new instance of Chart */
    public Chart()
    {
        log.trace("ctor");
    }
    
}
