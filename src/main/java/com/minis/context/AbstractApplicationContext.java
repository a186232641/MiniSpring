package com.minis.context;

import com.minis.beans.BeanException;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.BeanPostProcessor;
import com.minis.beans.factory.support.BeanFactoryPostProcessor;
import com.minis.beans.factory.support.ConfigurableListableBeanFactory;
import com.minis.core.env.Environment;

import com.minis.event.ApplicationEventPublisher;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/***
 * @description : Todo
 * @author : DDDreame
 * @date : 2023/4/1 21:03 
 */
public abstract class AbstractApplicationContext implements ApplicationContext{
    private Environment environment;

    private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

    private long startupDate;

    private final AtomicBoolean activate = new AtomicBoolean();

    private final AtomicBoolean close = new AtomicBoolean();

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Environment getEnvionment() {
        return this.environment;
    }



    @Override
    public Object getBean(String beanName) throws BeanException, ClassNotFoundException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Boolean containsBean(String beanName) {
        return getBeanFactory().containsBean(beanName);
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {

    }

    @Override
    public boolean isSingleton(String beanName) {
        return getBeanFactory().isSingleton(beanName);
    }

    @Override
    public boolean isPrototype(String beanName) {
        return getBeanFactory().isPrototype(beanName);
    }

    @Override
    public Class<?> getType(String name) {
        return getBeanFactory().getType(name);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return getBeanFactory().containsSingleton(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        getBeanFactory().registerSingleton(beanName, singletonObject);
    }

    @Override
    public String[] getSingletonNames() {
        return getBeanFactory().getSingletonNames();
    }

    @Override
    public Object getSingleton(String beanName) {
        return getBeanFactory().getSingleton(beanName);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return getBeanFactory().containsBeanDefinition(beanName);
    }

    @Override
    public int getBeanDefinitionCount() {
        return getBeanFactory().getBeanDefinitionCount();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return getBeanFactory().getBeanNamesForType(type);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class type) throws BeanException, ClassNotFoundException {
        return getBeanFactory().getBeansOfType(type);
    }

    public List<BeanFactoryPostProcessor> getBeanFactoryPostProcessorList(){
        return this.beanFactoryPostProcessors;
    }

    public void refresh() throws BeanException, IllegalStateException {
        postProcessBeanFactory(getBeanFactory());
        registerBeanPostProcessors(getBeanFactory());
        initApplicationEventPublisher();
        onRefresh();
        registerListeners();
        finishRefresh();
    }

    abstract void registerListeners();
    abstract void initApplicationEventPublisher();
    abstract void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
    abstract void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory);
    abstract void onRefresh() throws BeanException;
    abstract void finishRefresh();



    @Override
    public String getApplicationName() { return ""; }

    @Override
    public long getStartupDate() {
        return this.startupDate;
    }

    @Override
    public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Environment getEnvironment() {
        return this.environment;
    }

    @Override
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {
        this.beanFactoryPostProcessors.add(postProcessor);
    }
    @Override
    public void close() { }
    @Override
    public boolean isActive(){
        return true;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        getBeanFactory().addBeanPostProcessor(beanPostProcessor);

    }

    @Override
    public int getBeanPostProcessorCount() {
        return getBeanFactory().getBeanPostProcessorCount();
    }

    @Override
    public void registerDependentBean(String beanName, String dependentBeanName) {
        getBeanFactory().registerDependentBean(beanName, dependentBeanName);
    }

    @Override
    public String[] getDependentBeans(String beanName) {
        return getBeanFactory().getDependentBeans(beanName);
    }

    @Override
    public String[] getDependenciesForBean(String beanName) {
        return getBeanFactory().getDependenciesForBean(beanName);
    }

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationEventPublisher;
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

}
