/*
 * AbstractBeanInfo.java
 *
 * Created on 2 Aug 2006, 16:37
 */

package net.sf.jschart.component;

import java.awt.Image;
import java.beans.*;
import com.sun.rave.designtime.Constants;
import com.sun.rave.designtime.CategoryDescriptor;
import com.sun.rave.designtime.faces.FacetDescriptor;
import com.sun.rave.designtime.markup.AttributeDescriptor;
import java.util.ArrayList;
import java.util.Iterator;
import net.sf.jschart.util.CategoryDescriptors;
import net.sf.jschart.util.DesignUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author als
 */
public abstract class AbstractBeanInfo extends SimpleBeanInfo
{
    private static Log log = LogFactory.getLog(AbstractBeanInfo.class);

    protected static final int BOOL = 1;
    protected static final int HIDDEN = 2;
    protected static final int RO = 4;

    /** Creates a new instance of AbstractBeanInfo */
    public AbstractBeanInfo()
    {
        listPropDescriptors = new ArrayList<PropertyDescriptor>();
        defaultPropertyIndex = -2;
    }
    
    public void annotatePropertyDescriptors(PropertyDescriptor apropertydescriptor[])
    {
    }
    
    public void annotateBeanDescriptor(BeanDescriptor beanDescriptor)
    {
        beanDescriptor.setValue(Constants.BeanDescriptor.PROPERTY_CATEGORIES,
                CategoryDescriptors.getDefaultCategoryDescriptors());
    }
    
    protected String getMessage(String key)
    {
        return DesignUtil
                .getInstance()
                .getMessage(beanInfoClass, key);
    }
    
    /**
     * Adds new property descriptor to the bean info
     * @param propName propery name
     * @param editorClass Editor class name
     * @param category Category of the property
     * @return new added property descriptor
     */
    protected PropertyDescriptor addPropertyDescriptor(String propName,
            String editorClass, CategoryDescriptor category, int flags)
            throws IntrospectionException
    {
        PropertyDescriptor propDescriptor = null;
        try
        {
            propDescriptor = new PropertyDescriptor(propName,
                    beanClass,
                    ((flags & BOOL) != 0 ? "is" : "get") + DesignUtil.capitalize(propName),
                    (flags & RO) != 0 ? null : "set" + DesignUtil.capitalize(propName));
            
            // For example, "Component_prop"
            String prefix = DesignUtil.unqualifiedClassName(beanClass) + "_"
                    + propName;
            
            // Retrieve Strings from a resource bundle for I18N
            propDescriptor.setDisplayName(getMessage(prefix + "_DisplayName"));
            propDescriptor.setShortDescription(getMessage(prefix + "_Description"));
            
            if (editorClass != null)
            {
                propDescriptor.setPropertyEditorClass(
                        loadClass(editorClass.length() > 0 ?
                            editorClass : "com.sun.rave.propertyeditors.StringPropertyEditor"));
            }
            propDescriptor.setExpert(false);
            propDescriptor.setHidden((flags & HIDDEN) != 0);
            propDescriptor.setPreferred(false);
            propDescriptor.setValue(Constants.PropertyDescriptor.CATEGORY, category);
            
            listPropDescriptors.add(propDescriptor);
            propDescriptors = null;
        }
        catch (Exception e)
        {
            log.error("addPropertyDescriptor: ", e);
        }
        return propDescriptor;
    }
    
    /**
     * Adds new property descriptor to the bean info
     * @param propName propery name
     * @param editorClass Editor class name
     * @param category Category of the property
     * @param defaultValue Attribute default value
     * @return new added property descriptor
     */
    protected PropertyDescriptor addPropertyDescriptor(String propName,
            String editorClass, CategoryDescriptor category, int flags, String defaultValue)
            throws IntrospectionException
    {
        PropertyDescriptor pd = addPropertyDescriptor(propName, editorClass, category, flags);
        AttributeDescriptor attrib = new AttributeDescriptor(propName, false, defaultValue, true);
        pd.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR, attrib);
        return pd;
    }

    /**
     * <p>Return the <code>CategoryDescriptor</code> array for the property categories of this component.</p>
     */
    protected CategoryDescriptor[] getCategoryDescriptors()
    {
        return CategoryDescriptors.getDefaultCategoryDescriptors();
    }
    
    /**
     * <p>The cached facet descriptors.</p>
     */
    protected FacetDescriptor[] facetDescriptors;
    
    /**
     * <p>Return the <code>FacetDescriptor</code>s for this bean.</p>
     */
    public FacetDescriptor[] getFacetDescriptors()
    {
        if (facetDescriptors != null)
        {
            return facetDescriptors;
        }
        
        facetDescriptors = new FacetDescriptor[] {};
        return facetDescriptors;
    }
    
    public PropertyDescriptor[] getPropertyDescriptors()
    {
        if (propDescriptors == null && !listPropDescriptors.isEmpty())
            propDescriptors = listPropDescriptors.
                    toArray(new PropertyDescriptor[listPropDescriptors.size()]);
        return propDescriptors;
    }
    
    protected int findIndex(String propertyName)
    {
        if (!listPropDescriptors.isEmpty())
        {
            Iterator<PropertyDescriptor> it = listPropDescriptors.iterator();
            for(int i = 0; it.hasNext(); i++)
                if(it.next().getName().equals(propertyName))
                    return i;
        }
        return -1;
    }
    
    protected void removePropertyDescriptor(String propName)
    {
        int idx = findIndex(propName);
        if (idx >= 0)
        {
            listPropDescriptors.remove(idx);
            propDescriptors = null;
        }
    }
    
    protected PropertyDescriptor getPropertyDescriptor(String propName)
    {
        int idx = findIndex(propName);
        if (idx >= 0)
            return listPropDescriptors.get(idx);
        return null;
    }
    
    public int getDefaultPropertyIndex()
    {
        if(defaultPropertyIndex == -2)
        {
            if(defaultPropertyName == null)
                defaultPropertyIndex = -1;
            else
                defaultPropertyIndex = findIndex(defaultPropertyName);
        }
        return defaultPropertyIndex;
    }
    
    public String getDefaultPropertyName(String name)
    {
        return defaultPropertyName;
    }
    
    protected void setIcons(String prefix)
    {
        iconFileName_C16 = prefix + "_C16";
        iconFileName_C32 = prefix + "_C32";
        iconFileName_M16 = prefix + "_M16";
        iconFileName_M32 = prefix + "_M32";
    }
    
    public Image getIcon(int kind)
    {
        String name;
        switch (kind)
        {
            case ICON_COLOR_16x16:
                name = iconFileName_C16;
                break;
            case ICON_COLOR_32x32:
                name = iconFileName_C32;
                break;
            case ICON_MONO_16x16:
                name = iconFileName_M16;
                break;
            case ICON_MONO_32x32:
                name = iconFileName_M32;
                break;
            default:
                name = null;
                break;
        }
        if (name == null)
        {
            return null;
        }
        
        Image image = loadImage(name + ".png");
        if (image == null)
        {
            image = loadImage(name + ".gif");
        }
        return image;
        
    }
    
    protected Class loadClass(String name)
    {
        try
        {
            return Class.forName(name);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    protected Class beanClass;
    protected Class beanInfoClass;
    protected String iconFileName_C16;
    protected String iconFileName_C32;
    protected String iconFileName_M16;
    protected String iconFileName_M32;
    protected BeanDescriptor beanDescriptor;
    protected PropertyDescriptor propDescriptors[];
    protected MethodDescriptor methodDescriptors[];
    protected EventSetDescriptor eventSetDescriptors[];
    protected int defaultPropertyIndex;
    protected String defaultPropertyName;
    private ArrayList<PropertyDescriptor> listPropDescriptors;
}
