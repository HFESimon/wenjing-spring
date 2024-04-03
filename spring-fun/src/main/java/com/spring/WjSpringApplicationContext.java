package com.spring;

import com.spring.annotation.Autowired;
import com.spring.annotation.Component;
import com.spring.annotation.ComponentScan;
import com.spring.annotation.Scope;
import com.spring.beans.BeanDefinition;
import com.spring.beans.factory.BeanNameAware;
import com.spring.beans.factory.BeanPostProcessor;
import com.spring.beans.factory.InitializingBean;
import com.wenjing.config.AppConfig;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    /**
     * bean后置处理器
     */
    List<BeanPostProcessor> beanPostProcessors = new LinkedList<>();


    public WjSpringApplicationContext(Class<AppConfig> configClass) {
        this.configClass = configClass;

        // 扫描
        scan(configClass);

        // 创建单例bean
        createBean();
    }

    /**
     * 从 context 中获取bean
     * @param beanName
     * @return
     */
    public Object getBean(String beanName){
        // beanName --> Bean
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new RuntimeException("没有这个Bean");
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition.getScope().equals(BeanDefinition.ScopeType.SINGLETON)) {
            // 单例
            Object singletonBean = singletonObjects.get(beanName);
            // 依赖注入的时候可能单例Bean由于创建顺序问题在单例池中没有
            if (singletonBean == null) {
                singletonBean = doCreateBean(beanName, beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            // 原型
            return doCreateBean(beanName, beanDefinition);
        }
    }

    /**
     * 创建单例bean
     */
    private void createBean() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals(BeanDefinition.ScopeType.SINGLETON)) {
                // 单例
                Object bean = doCreateBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    /**
     * 创建Bean
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getType();

        Object instance = null;

        try {
            // 获取无参构造并构造普通对象
            instance = clazz.getConstructor().newInstance();

            // 依赖注入(spring 的依赖注入也是通过postProcessor来实现的，这里为了简单直接遍历属性了)
            for (Field field : clazz.getDeclaredFields()) {
                // 有@Autowired注解`
                if (field.isAnnotationPresent(Autowired.class)) {
                    // 反射
                    field.setAccessible(true);
                    // 注入bean对象(注：spring是先getByType再getByName的，这里为了实现简单直接根据属性名去找bean了)
                    field.set(instance, getBean(field.getName()));
                }
            }

            // Aware
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setBeanName(beanName);
            }

            // BeanPostProcessor —— before
            for (BeanPostProcessor processor : beanPostProcessors) {
                instance = processor.postProcessBeforeInitialization(instance, beanName);
            }

            // initializingBean
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }

            // BeanPostProcessor —— after
            for (BeanPostProcessor processor : beanPostProcessors) {
                // 可用来实现 AOP
                instance = processor.postProcessAfterInitialization(instance, beanName);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    /**
     * 扫描配置类配置的包结构下的所有class并创建
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

                            // 判断类有没有实现 BeanPostProcessor 接口
                            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                // BeanPostProcessor
                                BeanPostProcessor postProcessor = (BeanPostProcessor) clazz.getConstructor().newInstance();
                                beanPostProcessors.add(postProcessor);
                            } else {
                                // 从 Component 注解中拿到 BeanName
                                String beanName = generateBeanName(clazz);

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
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 计算beanName
     * @param clazz
     * @return
     */
    public String generateBeanName(Class<?> clazz) {
        // 从 Component 注解中拿到 BeanName
        Component componentAnnotation = clazz.getAnnotation(Component.class);
        String beanName = componentAnnotation.value();

        if (!"".equals(beanName)) {
            return beanName;
        }
        // 生成默认名字
        return Introspector.decapitalize(clazz.getSimpleName());
    }
}
