package net.sf.jschart.util;

public class DesignUtil extends L10nUtil
{
    private static DesignUtil INSTANCE = new DesignUtil();
    
    /**
     * Returns singleton which should only be used with design-time code
     *
     * @return Design-time utility singleton
     */
    public static DesignUtil getInstance()
    {
        return INSTANCE;
    }
    
    // @Override
    public String getBundleUnqualifiedBaseName()
    {
        return "Bundle-dt";
    }
    
    /**
     * @return a String which capitalizes the first letter of the string
     */
    public static String capitalize(String name)
    {
        if (name == null || name.length() == 0)
        {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
    
    /**
     * @return the root name of a class
     */
    public static String unqualifiedClassName(Class type)
    {
        String name = type.getName();
        return name.substring(name.lastIndexOf('.') + 1);
    }
}
