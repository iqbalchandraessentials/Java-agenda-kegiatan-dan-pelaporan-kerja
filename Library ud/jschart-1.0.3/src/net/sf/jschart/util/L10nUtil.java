package net.sf.jschart.util;

import java.beans.Beans;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class L10nUtil
{
    private static L10nUtil INSTANCE = new L10nUtil();
    
    /**
     * Returns singleton usable from both runtime and design-time code
     *
     * @return Utility singleton
     */
    public static L10nUtil getInstance()
    {
        return INSTANCE;
    }
    
    /**
     * Return an current locale
     *
     * @return current locale
     */
    public static Locale getLocale()
    {
        Locale locale;
        if (Beans.isDesignTime())
        {
            locale = Locale.getDefault();
        }
        else
        {
            // TODO The following code is not simulated fully in rave yet.
            // Remove this workaround when it is fixed.
            UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
            locale = viewRoot.getLocale();
        }
        return locale;
    }
    
    /**
     * Return an appropriate ResourceBundle unqualified base name.
     *
     * @return unqualified base name
     */
    public String getBundleUnqualifiedBaseName()
    {
        return "Bundle";
    }
    
    /**
     * Get a ResourceBundle in the same package as a class in the locale
     * specified by the current ViewRoot
     *
     * @param classInSamePackage
     *            Class object in the same package as ResourceBundle named
     *            "Bundle"
     * @return ResourceBundle in the same package
     */
    public ResourceBundle getBundle(Class classInSamePackage)
    {
        return ResourceBundle.getBundle(
                classInSamePackage.getPackage().getName() +
                "." + getBundleUnqualifiedBaseName(), getLocale());
    }
    
    /**
     * Get a localized message from a ResourceBundle in the same package as a
     * class
     *
     * @param classInSamePackage
     *            Class object in the same package as ResourceBundle named
     *            "Bundle"
     * @param key
     *            message key
     * @return localized message
     */
    public String getMessage(Class classInSamePackage, String key)
    {
        return getBundle(classInSamePackage).getString(key);
    }
    
    /**
     * Get a localized and formatted message from a ResourceBundle in the same
     * package as a class. Format the message using the passed in args.
     *
     * @param classInSamePackage
     *            Class object in the same package as ResourceBundle named
     *            "Bundle"
     * @param key
     *            message key
     * @param args
     *            Object[] containing formatting args
     * @return localized message
     * @see MessageFormat
     */
    public String getFormattedMessage(Class classInSamePackage, String key,
            Object... args)
    {
        String pattern = getMessage(classInSamePackage, key);
        return MessageFormat.format(pattern, args);
    }
}
