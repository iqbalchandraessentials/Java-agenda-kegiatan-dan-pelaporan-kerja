package net.sf.jschart.util;

import java.util.ResourceBundle;
import com.sun.rave.designtime.CategoryDescriptor;

public class CategoryDescriptors
{
    
    private static final ResourceBundle bundle = DesignUtil.getInstance()
            .getBundle(CategoryDescriptors.class);
    
    public static final CategoryDescriptor GENERAL = new CategoryDescriptor(
            bundle.getString("gen"), bundle.getString("genCatDesc"), true);
    
    public static final CategoryDescriptor APPEARANCE = new CategoryDescriptor(
            bundle.getString("appear"), bundle.getString("appearCatDesc"), true);
    
    public static final CategoryDescriptor DATA = new CategoryDescriptor(bundle
            .getString("data"), bundle.getString("dataCatDesc"), true);
    
    public static final CategoryDescriptor EVENTS = new CategoryDescriptor(
            bundle.getString("ev"), bundle.getString("evCatDesc"), true);
    
    public static final CategoryDescriptor JAVASCRIPT = new CategoryDescriptor(
            bundle.getString("js"), bundle.getString("jsCatDesc"), false);
    
    public static final CategoryDescriptor ADVANCED = new CategoryDescriptor(
            bundle.getString("adv"), bundle.getString("advCatDesc"), false);
    
    public static final CategoryDescriptor INTERNAL = new CategoryDescriptor(
            bundle.getString("intern"), bundle.getString("internCatDesc"),
            false);
    
    public static final CategoryDescriptor RESOURCE = new CategoryDescriptor(
            bundle.getString("resource"), bundle.getString("resourceCatDesc"),
            false);
    
    private static CategoryDescriptor defaultCategoryDescriptors[] = { GENERAL,
    APPEARANCE, DATA, EVENTS, JAVASCRIPT, ADVANCED, INTERNAL, RESOURCE };
    
    public static CategoryDescriptor[] getDefaultCategoryDescriptors()
    {
        return defaultCategoryDescriptors;
    }
}
