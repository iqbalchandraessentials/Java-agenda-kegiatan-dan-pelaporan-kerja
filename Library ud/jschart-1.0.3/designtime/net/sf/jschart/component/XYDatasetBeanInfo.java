package net.sf.jschart.component;

import java.beans.BeanDescriptor;

import com.sun.rave.designtime.Constants;
import java.beans.PropertyDescriptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.jschart.util.CategoryDescriptors;

public class XYDatasetBeanInfo extends AbstractBeanInfo
{
    private static Log log = LogFactory.getLog(XYDatasetBeanInfo.class);
    
    public XYDatasetBeanInfo()
    {
        try
        {
            beanClass = XYDataset.class;
            beanInfoClass = XYDatasetBeanInfo.class;
            setIcons("XYDataset");
            PropertyDescriptor pd;
            pd = addPropertyDescriptor("byColumn", null,
                    CategoryDescriptors.APPEARANCE, BOOL);
            pd = addPropertyDescriptor("lowerRangeBound", null,
                    CategoryDescriptors.DATA, 0);
            pd = addPropertyDescriptor("upperRangeBound", null,
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
            beanDescriptor.setDisplayName(getMessage("XYDataset_DisplayName"));
            beanDescriptor.setShortDescription(getMessage("XYDataset_Description"));
            beanDescriptor.setExpert(false);
            beanDescriptor.setHidden(false);
            beanDescriptor.setPreferred(false);
            beanDescriptor.setValue(Constants.BeanDescriptor.FACET_DESCRIPTORS, getFacetDescriptors());
            beanDescriptor.setValue(Constants.BeanDescriptor.INSTANCE_NAME, "xyDataset");
            beanDescriptor.setValue(Constants.BeanDescriptor.IS_CONTAINER, Boolean.FALSE);
            beanDescriptor.setValue(Constants.BeanDescriptor.PROPERTY_CATEGORIES, getCategoryDescriptors());

        }
        return beanDescriptor;
    }
}
