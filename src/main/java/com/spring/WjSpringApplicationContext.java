package com.spring;

import com.spring.annotation.Component;
import com.spring.annotation.ComponentScan;
import com.spring.annotation.Scope;
import com.spring.base.BeanDefinition;
import com.wenjing.config.AppConfig;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/2/28 12:39 上午
 */

public class WjSpringApplicationContext {

    /**
     * 配置类
     */
    private Class configClass;

    /**
     * bean定义
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 单例池
     */
    private Map<String, Object> singletonObjects = new HashMap<>();

    public WjSpringApplicationContext(Class<com.wenjing.config.AppConfig> configClass) {
        this.configClass = configClass;

        // 扫描
        scan(configClass);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals(BeanDefinition.ScopeType.SINGLETON)) {
                // 单例
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            } else {
                // 原型
            }
        }
    }



    public Object getBean(String beanName){
        // beanName --> Bean
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new RuntimeException("没有这个Bean");
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition.getScope().equals(BeanDefinition.ScopeType.SINGLETON)) {
            // 单例
            return singletonObjects.get(beanName);
        } else {
            // 原型
            return createBean(beanName, beanDefinition);
        }
    }

    /**
     * 创建Bean
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();

        Object instance = null;

        try {
            instance = clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return instance;
    }

    /**
     * 扫描
     * @param configClass
     */
    private void scan(Class<AppConfig> configClass) {
        // 扫描并判断 有没有 ComponentScan注解
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan configClassAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = configClassAnnotation.value();
            // com/wenjing/config
            // 转换为扫描路径
            path = path.replace(".", "/");

            // WjSpringApplicationContext 对应的类加载器是 AppClassLoader, 拿到扫描路径下的源文件
            ClassLoader classLoader = WjSpringApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            // 找到目录
            File file = new File(resource.getFile());
            // 是否是一个目录
            if (file.isDirectory()) {
                // 遍历目录下的文件
                for (File listFile : file.listFiles()) {
                    // 得到文件绝对路径
                    String absolutePath = listFile.getAbsolutePath();
                    // 再转回为相对路径并处理为 com.xxx.xxx.class
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("/", ".");

                    try {
                        // 把类加载进来
                        Class<?> clazz = classLoader.loadClass(absolutePath);
                        // 判断有没有 Component 注解
                        if (clazz.isAnnotationPresent(Component.class)) {

                            // 从 Component 注解中拿到 BeanName
                            Component componentAnnotation = clazz.getAnnotation(Component.class);
                            String beanName = componentAnnotation.value();

                            // 构造 BeanDefinition
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(clazz);

                            // 从 scope 注解中拿到 Bean 类型
                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                String scope = scopeAnnotation.value();
                                beanDefinition.setScope(scope);
                            } else {
                                // 单例
                                beanDefinition.setScope(BeanDefinition.ScopeType.SINGLETON);
                            }
                            beanDefinitionMap.put(beanName, beanDefinition);
                            // 创建 Bean
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
