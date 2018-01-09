package by.bsuir.dissertation.processor;

import by.bsuir.dissertation.annotation.InjectGraph;
import by.bsuir.dissertation.service.GraphService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class InjectGraphAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private GraphService graphService;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            InjectGraph annotation = field.getAnnotation(InjectGraph.class);
            if (annotation != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, graphService.getGraph());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
