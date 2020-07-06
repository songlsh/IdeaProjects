package com.sls.springboot.testImport;

import com.sls.springboot.TestInface.ProductService;
import com.sls.springboot.TestInface.ProductServiceImpl;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ImportBeanDefinitionRegistrar
 */
public class TestBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(ProductServiceImpl.class);
        beanDefinitionRegistry.registerBeanDefinition("productor",rootBeanDefinition);
    }
}
