package net.sf.jschart.component;

import com.sun.rave.designtime.Constants;
import java.beans.BeanDescriptor;
import java.beans.PropertyDescriptor;
import net.sf.jschart.util.CategoryDescriptors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ChartBeanInfo extends AbstractBeanInfo
{
    private static Log log = LogFactory.getLog(ChartBeanInfo.class);
    
    public ChartBeanInfo()
    {
        beanClass = Chart.class;
        beanInfoClass = ChartBeanInfo.class;
        setIcons("Chart");
        try
        {
            PropertyDescriptor pd;
            pd = addPropertyDescriptor("type", "net.sf.jschart.util.ChartTypePropertyEditor",
                    CategoryDescriptors.APPEARANCE, 0, "\"bar\"");
            pd = addPropertyDescriptor("title", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("subtitle", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("xlabel", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("ylabel", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("depth", null,
                    CategoryDescriptors.APPEARANCE, 0, "15");
            pd = addPropertyDescriptor("height", null,
                    CategoryDescriptors.APPEARANCE, 0, "300");
            pd = addPropertyDescriptor("width", null,
                    CategoryDescriptors.APPEARANCE, 0, "400");
            pd = addPropertyDescriptor("alpha", null,
                    CategoryDescriptors.APPEARANCE, 0, "100");
            pd = addPropertyDescriptor("antialias", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "true");
            pd = addPropertyDescriptor("background", "",
                    CategoryDescriptors.APPEARANCE, 0, "\"white\"");
            pd = addPropertyDescriptor("border", null,
                    CategoryDescriptors.APPEARANCE, 0, "0");
            pd = addPropertyDescriptor("chart3d", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "true");
            pd = addPropertyDescriptor("colors", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("dateFormat", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("dateTick", "net.sf.jschart.util.DateTickPropertyEditor",
                    CategoryDescriptors.APPEARANCE, 0, "\"auto\"");
            pd = addPropertyDescriptor("dateTickCount", null,
                    CategoryDescriptors.APPEARANCE, 0, "1");
            pd = addPropertyDescriptor("domainNumberTick", null,
                    CategoryDescriptors.APPEARANCE, 0, "0.0");
            pd = addPropertyDescriptor("rangeNumberTick", null,
                    CategoryDescriptors.APPEARANCE, 0, "0.0");
            pd = addPropertyDescriptor("includeZero", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "true");
            pd = addPropertyDescriptor("intervalMarker", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("gradient", null,
                    CategoryDescriptors.APPEARANCE, 0, "100");
            pd = addPropertyDescriptor("gradientType", "net.sf.jschart.util.GradientTypePropertyEditor",
                    CategoryDescriptors.APPEARANCE, 0, "\"vertical\"");
            pd = addPropertyDescriptor("label", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "true");
            pd = addPropertyDescriptor("labelAngle", null,
                    CategoryDescriptors.APPEARANCE, 0, "0");
            pd = addPropertyDescriptor("labelFormat", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("verticalDateTicks", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "false");
            pd = addPropertyDescriptor("legend", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "true");
            pd = addPropertyDescriptor("legendLabelFormat", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("limitPercent", null,
                    CategoryDescriptors.APPEARANCE, 0, "0");
            pd = addPropertyDescriptor("limitPercentTitle", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("legendPosition", "net.sf.jschart.util.PositionPropertyEditor",
                    CategoryDescriptors.APPEARANCE, 0, "\"bottom\"");
            pd = addPropertyDescriptor("orientation", "net.sf.jschart.util.OrientationPropertyEditor",
                    CategoryDescriptors.APPEARANCE, 0, "\"vertical\"");
            pd = addPropertyDescriptor("outline", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "false");
            pd = addPropertyDescriptor("periodAxisFormat", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("startAngle", null,
                    CategoryDescriptors.APPEARANCE, 0, "90");
            pd = addPropertyDescriptor("style", "com.sun.jsfcl.std.css.CssStylePropertyEditor",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("styleClass", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("tooltipFormat", "",
                    CategoryDescriptors.APPEARANCE, 0, null);
            pd = addPropertyDescriptor("tooltipXDate", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "false");
            pd = addPropertyDescriptor("tooltipYDate", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "false");
            pd = addPropertyDescriptor("tooltips", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "true");
            pd = addPropertyDescriptor("visibleShapes", null,
                    CategoryDescriptors.APPEARANCE, BOOL, "true");
            pd = addPropertyDescriptor("useChartlet", null,
                    CategoryDescriptors.INTERNAL, BOOL);

            pd = addPropertyDescriptor("datasource", "com.sun.rave.propertyeditors.SelectOneDomainEditor",
                    CategoryDescriptors.DATA, 0, null);
            pd.setValue("com.sun.rave.propertyeditors.DOMAIN_CLASS",
                    loadClass("com.sun.rave.propertyeditors.domains.InstanceVariableDomain"));
            pd = addPropertyDescriptor("converter", "com.sun.jsfcl.std.ConverterPropertyEditor",
                    CategoryDescriptors.DATA, 0, null);
            pd = addPropertyDescriptor("categoryToPieByRow", null,
                    CategoryDescriptors.DATA, BOOL, "false");
            pd = addPropertyDescriptor("value", "com.sun.jsfcl.std.ValueBindingPropertyEditor",
                    CategoryDescriptors.DATA, 0, null);
            pd.setValue("ignoreIsBound", "true");

            pd = addPropertyDescriptor("id", null,
                    CategoryDescriptors.GENERAL, HIDDEN, null);
            pd = addPropertyDescriptor("rendered", null,
                    CategoryDescriptors.ADVANCED, BOOL, null);

            pd = addPropertyDescriptor("attributes", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
            pd = addPropertyDescriptor("childCount", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
            pd = addPropertyDescriptor("children", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
            pd = addPropertyDescriptor("facets", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
            pd = addPropertyDescriptor("family", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
            pd = addPropertyDescriptor("localValue", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
            pd = addPropertyDescriptor("parent", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
            pd = addPropertyDescriptor("rendererType", null,
                    CategoryDescriptors.INTERNAL, HIDDEN);
            pd = addPropertyDescriptor("rendersChildren", null,
                    CategoryDescriptors.INTERNAL, RO|HIDDEN);
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
            beanDescriptor.setDisplayName(getMessage("Chart_DisplayName"));
            beanDescriptor.setShortDescription(getMessage("Chart_Description"));
            beanDescriptor.setExpert(false);
            beanDescriptor.setHidden(false);
            beanDescriptor.setPreferred(false);
            beanDescriptor.setValue(Constants.BeanDescriptor.FACET_DESCRIPTORS, getFacetDescriptors());
            beanDescriptor.setValue(Constants.BeanDescriptor.INSTANCE_NAME, "chart");
            beanDescriptor.setValue(Constants.BeanDescriptor.IS_CONTAINER, Boolean.FALSE);
            beanDescriptor.setValue(Constants.BeanDescriptor.PROPERTY_CATEGORIES,
                    getCategoryDescriptors());
            beanDescriptor.setValue(Constants.BeanDescriptor.TAG_NAME,"chart");
            beanDescriptor.setValue(Constants.BeanDescriptor.TAGLIB_PREFIX,"jschart");
            beanDescriptor.setValue(Constants.BeanDescriptor.TAGLIB_URI,"http://net.sf.jschart/jschart");
            beanDescriptor.setValue(Constants.BeanDescriptor.RESIZE_CONSTRAINTS,
                    new Integer(Constants.ResizeConstraints.NONE));
        }
        return beanDescriptor;
    }
}
