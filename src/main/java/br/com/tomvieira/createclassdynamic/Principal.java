package br.com.tomvieira.createclassdynamic;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.core.Predicate;

/**
 *
 * @author Wellington
 */
public class Principal {

    public static Object createBeanClass(
            /* fully qualified class name */
            final String className,
            /* bean properties, name -> type */
            final Map<String, Class<?>> properties) {

        final BeanGenerator beanGenerator = new BeanGenerator();

        /* use our own hard coded class name instead of a real naming policy */
        beanGenerator.setNamingPolicy(new NamingPolicy() {
            @Override
            public String getClassName(final String prefix,
                    final String source, final Object key, final Predicate names) {
                return className;
            }

        });
        BeanGenerator.addProperties(beanGenerator, properties);
        return  beanGenerator.create();
    }

    public static void main(final String[] args) throws Exception {
        final Map<String, Class<?>> properties
                = new HashMap<String, Class<?>>();
        properties.put("foo", Integer.class);
        properties.put("bar", String.class);
        properties.put("baz", int[].class);

        Object obj
                 = createBeanClass("Usuario", properties);        
        obj.getClass().getMethod("setFoo",Integer.class).invoke(obj,5);
        System.out.println(obj.getClass().getMethod("getFoo").invoke(obj));
       

    }
}
