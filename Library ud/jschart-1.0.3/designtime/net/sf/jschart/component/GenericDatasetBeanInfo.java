package net.sf.jschart.component;

import com.sun.rave.designtime.Constants;
import java.beans.BeanDescriptor;
import java.beans.PropertyDescriptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.jschart.util.CategoryDescriptors;

public class GenericDatasetBeanInfo extends AbstractBeanInfo
{
    private static Log log = LogFactory.getLog(GenericDatasetBeanInfo.class);
    
    public GenericDatasetBeanInfo()
    {
        try
        {
            beanClass = GenericDataset.class;
            beanInfoClass = GenericDatasetBeanInfo.class;
            setIcons("GenericDataset");
            PropertyDescriptor pd;
            pd = addPropertyDescriptor("categoryField",
                    "net.sf.jschart.util.FieldKeyPropertyEditor",
                    CategoryDescriptors.DATA, 0);
            pd = addPropertyDescriptor("seriesField",
                    "net.sf.jschart.util.FieldKeyPropertyEditor",
                    CategoryDescriptors.DATA, 0);
            pd = addPropertyDescriptor("keyField",
                    "net.sf.jschart.util.FieldKeyPropertyEditor",
                    CategoryDescriptors.DATA, 0);
            pd = addPropertyDescriptor("domainField",
                    "net.sf.jschart.util.FieldKeyPropertyEditor",
                    CategoryDescriptors.DATA, 0);
            pd = addPropertyDescriptor("valueField",
                    "net.sf.jschart.util.FieldKeyPropertyEditor",
                    CategoryDescriptors.DATA, 0);
            pd = addPropertyDescriptor("rowField",
                    "net.sf.jschart.util.FieldKeyPropertyEditor",
                    CategoryDescriptors.DATA, 0);
            pd = addPropertyDescriptor("dataProvider",
                    "com.sun.rave.propertyeditors.SelectOneDomainEditor",
                    CategoryDescriptors.DATA, 0);
            pd.setValue("com.sun.rave.propertyeditors.DOMAIN_CLASS",
                    loadClass("com.sun.rave.propertyeditors.domains.InstanceVariableDomain"));
        }
        catch (Exception e)
        {
            log.error("Unexpected", e);
        }
    }
    
    public BeanDescriptor getBeanDescriptor()
    {
        if (beanDescriptor == null)
        {
            beanDescriptor = new BeanDescriptor(beanClass);
            beanDescriptor.setDisplayName(getMessage("GenericDataset_DisplayName"));
            beanDescriptor.setShortDescription(getMessage("GenericDataset_Description"));
            beanDescriptor.setExpert(false);
            beanDescriptor.setHidden(false);
            beanDescriptor.setPreferred(false);
            beanDescriptor.setValue(Constants.BeanDescriptor.FACET_DESCRIPTORS, getFacetDescriptors());
            beanDescriptor.setValue(Constants.BeanDescriptor.INSTANCE_NAME, "genericDataset");
            beanDescriptor.setValue(Constants.BeanDescriptor.IS_CONTAINER, Boolean.FALSE);
            beanDescriptor.setValue(Constants.BeanDescriptor.PROPERTY_CATEGORIES, getCategoryDescriptors());
        }
        return beanDescriptor;
        
    }
}
