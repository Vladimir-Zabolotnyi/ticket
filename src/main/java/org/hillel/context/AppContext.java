package org.hillel.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class AppContext {
    private static Map<String, Object> beanstorage = new HashMap<>();
    private static Properties properties = new Properties();
    private AppContext() {
    }

    public static void load(final String filename) throws IOException {
        if (filename.isBlank() || filename == null) throw new IllegalArgumentException("filename must be set");
        try (InputStream is = AppContext.class.getClassLoader().getResourceAsStream(filename)) {
            properties.load(is);
        }
    }

    public static <T> T getBean(final String beanName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (beanstorage.containsKey(beanName)) return (T) beanstorage.get(beanName);
        final String property = properties.getProperty(beanName);
        if (property == null) throw new IllegalArgumentException("bean with name " + beanName + "not found");
        final T bean = (T) Class.forName(property).newInstance();
        beanstorage.put(beanName, bean);
        return bean;
    }

}
