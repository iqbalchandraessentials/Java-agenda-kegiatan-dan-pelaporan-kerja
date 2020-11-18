/*
 */

package net.sf.jschart.util;

import java.beans.PropertyEditorSupport;
import java.util.Map;
import java.util.TreeMap;

public class DateTickPropertyEditor extends PropertyEditorSupport
{
    
    // Init localized tag strings
    private static TreeMap<String,String> chartTypes= new TreeMap<String,String>();
    
    private static void addType(String newType)
    {
        chartTypes.put( newType, DesignUtil.getInstance().getMessage(
                PositionPropertyEditor.class, "DateTickPropertyEditor." + newType ));
    }
    
    static
    {
        for(String t : new String[] { "auto", "year", "quarter", "month", "week", "day", "hour" } )
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
