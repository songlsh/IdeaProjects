package com.sls.springboot.testImport;

import com.sls.springboot.TestInface.ProductServiceImpl;
import com.sls.springboot.config.AppConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class TestSelect implements ImportSelector, BeanFactoryAware{

    private  BeanFactory beanFactory;

    /**
     *
     * @param annotationMetadata
     * @return 返回值为 对应导入importSelect类的Configuration类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

        annotationMetadata.getAnnotationTypes().forEach(System.out::println);
        System.out.println("beanfacotry is :"+beanFactory);
        return new String[]{
                AppConfig.class.getName()
        };

        // 把ProductServiceImpl类注册到bean中去了
/*        return new String[]{
            ProductServiceImpl.class.getName()
        };*/
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
