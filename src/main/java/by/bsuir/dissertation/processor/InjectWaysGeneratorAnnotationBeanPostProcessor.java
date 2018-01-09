package by.bsuir.dissertation.processor;

import by.bsuir.dissertation.annotation.InjectWaysGenerator;
import by.bsuir.dissertation.util.WaysGenerator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class InjectWaysGeneratorAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${ways-generator.quantity}")
    private int quantity;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            InjectWaysGenerator annotation = field.getAnnotation(InjectWaysGenerator.class);
            if (annotation != null) {
                field.setAccessible(true);
                List<WaysGenerator> waysGenerators = new ArrayList<>(quantity);
                for (int i = 0; i < quantity; i++) {
                    waysGenerators.add(applicationContext.getBean(WaysGenerator.class));
                }
                ReflectionUtils.setField(field, bean, waysGenerators);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
