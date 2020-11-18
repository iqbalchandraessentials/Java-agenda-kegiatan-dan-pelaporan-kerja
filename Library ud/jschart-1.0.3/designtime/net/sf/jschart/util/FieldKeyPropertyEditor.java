package net.sf.jschart.util;

import com.sun.data.provider.FieldKey;
import com.sun.data.provider.TableDataProvider;
import com.sun.rave.designtime.DesignBean;
import com.sun.rave.designtime.DesignProperty;

import org.netbeans.modules.visualweb.propertyeditors.PropertyEditorBase;
//import com.sun.rave.propertyeditors.PropertyEditorBase;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class FieldKeyPropertyEditor extends PropertyEditorBase
{
//    private static Log log = LogFactory.getLog(FieldKeyPropertyEditor.class);
    
    public String getAsText()
    {
        Object value = getValue();
        if (value == null)
            return "";
        return String.valueOf(value);
    }
    
    public void setAsText(String text) throws java.lang.IllegalArgumentException
    {
        if (text == null || text.trim().length() == 0)
            setValue(null);
        else
            setValue(text);
    }
    
    public String[] getTags()
    {
        DesignBean editingBean = getDesignProperty().getDesignBean();
        
        DesignProperty dataProviderProp = editingBean.getProperty("dataProvider");
        if (dataProviderProp != null && TableDataProvider.class.isAssignableFrom(
                dataProviderProp.getPropertyDescriptor().getPropertyType()))
        {
            Object otdp = dataProviderProp.getValue();
            if (otdp instanceof TableDataProvider)
            {
                TableDataProvider tdp = (TableDataProvider)otdp;
                FieldKey[] fields = tdp.getFieldKeys();
                String[] ret = new String[fields.length];
                for (int i = 0; i<fields.length; i++)
                    ret[i] = fields[i].getFieldId();
                return ret;
            }
        }
        return null;
    }
}
