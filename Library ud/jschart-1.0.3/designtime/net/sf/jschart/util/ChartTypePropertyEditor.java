/*
 */

package net.sf.jschart.util;

import java.beans.PropertyEditorSupport;
import java.util.Map;
import java.util.TreeMap;
import net.sf.jschart.util.*;

public class ChartTypePropertyEditor extends PropertyEditorSupport
{
    
    // Init localized tag strings
    private static TreeMap<String,String> chartTypes= new TreeMap<String,String>();
    
    private static void addType(String newType)
    {
        chartTypes.put( newType, DesignUtil.getInstance().getMessage(
                ChartTypePropertyEditor.class, "ChartTypePropertyEditor." + newType ));
    }
    
    static
    {
        for(String t : new String[] { "bar", "stackedbar", "line", "area", "stackedarea",
        "waterfall", "gantt", "layeredbar","multipie", "pie", "ring", "timeseries",
        "xyline", "xybar", "polar", "scatter", "xyarea", "xysteparea", "xystep",
        "xytimediff", "xylinediff","bubble", "candlestick", "boxandwhisker",
        "highlow", "histogram", "wind" } )
            addType( t );
    }
    
    public String getAsText()
    {
        Object val = getValue();
        if (val instanceof String)
        {
            String strVal = chartTypes.get(val);
            if (strVal != null)
                return strVal;
        }
        // Default value
        return chartTypes.get(chartTypes.firstKey());
    }
    
    public void setAsText(String text) throws IllegalArgumentException
    {
        for (Map.Entry<String,String> e : chartTypes.entrySet())
        {
            if (e.getValue().equals(text))
            {
                setValue(e.getKey());
                return;
            }
        }
        // Default key
        setValue(chartTypes.firstKey());
    }
    
    public String[] getTags()
    {
        return (String[])chartTypes.values().toArray(new String[0]);
    }
}
