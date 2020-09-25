package org.bipolis.tests.servicepropcoll.itest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.bipolis.tests.servicepropcoll.Condition;
import org.bipolis.tests.servicepropcoll.Example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.test.common.annotation.InjectBundleContext;
import org.osgi.test.junit5.context.BundleContextExtension;
import org.osgi.test.junit5.service.ServiceExtension;
import org.osgi.util.tracker.ServiceTracker;

@ExtendWith({ BundleContextExtension.class, ServiceExtension.class })
public class ExampleIntegrationTest
{

    @InjectBundleContext
    BundleContext context = null;

    @BeforeEach
    public void before(TestInfo testInfo)
    {
        System.out.println("|||||||||||||||||||||||||||||||||||");
        System.out.println("|||||          START         ||||||");
        System.out.println(
            "||||| " + testInfo.getTestMethod().get().getName() + " ||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||");
    }

    @AfterEach
    public void after(TestInfo testInfo)
    {
        System.out.println("|||||||||||||||||||||||||||||||||||");
        System.out.println("|||||          STOP         ||||||");
        System.out.println(
            "||||| " + testInfo.getTestMethod().get().getName() + " ||||||");
        System.out.println("|||||||||||||||||||||||||||||||||||");
    }

    /**
     * @throws InterruptedException
     */
    @Test
    public void testExampleWorking1() throws InterruptedException
    {
        List<String> propList = new LinkedList<>();

        // we wrap the prop list into an new arraylist
        Dictionary<String, Object> properties = getProperties(new ArrayList<>(propList));
        ServiceRegistration<Condition> registration = context.registerService(
            Condition.class, Condition.INSTANCE, properties);

        Thread.sleep(1000l);

        ServiceTracker<Example, Example> exampleTracker = new ServiceTracker<>(context,
            Example.class, null);
        exampleTracker.open(true);
        assertThat(exampleTracker.getTrackingCount()).isEqualTo(0);

        propList.add("A");
        properties = getProperties(new ArrayList<>(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(1);
        Example example = exampleTracker.getService();
        assertThat(example).isNotNull();

        propList.clear();
        properties = getProperties(new ArrayList<>(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        example = exampleTracker.getService();
        assertThat(example).isNull();

    }

    /**
     * getProperties(new HashSet<String>(propList));
     * @throws InterruptedException
     */
    @Test
    public void testExampleWorking2() throws InterruptedException
    {
        List<String> propList = new LinkedList<>();

        Dictionary<String, Object> properties = getProperties(new HashSet<>(propList));
        ServiceRegistration<Condition> registration = context.registerService(
            Condition.class, Condition.INSTANCE, properties);

        Thread.sleep(1000l);

        ServiceTracker<Example, Example> exampleTracker = new ServiceTracker<>(context,
            Example.class, null);
        exampleTracker.open(true);
        assertThat(exampleTracker.getTrackingCount()).isEqualTo(0);

        propList.add("A");
        properties = getProperties(new HashSet<>(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(1);
        Example example = exampleTracker.getService();
        assertThat(example).isNotNull();

        propList.clear();
        properties = getProperties(new HashSet<>(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        example = exampleTracker.getService();
        assertThat(example).isNull();

    }

    /**
     * properties = getProperties(new LinkedList<String>(propList));
     * @throws InterruptedException
     */
    @Test
    public void testExampleWorking3() throws InterruptedException
    {
        List<String> propList = new LinkedList<>();

        Dictionary<String, Object> properties = getProperties(new LinkedList<>(propList));
        ServiceRegistration<Condition> registration = context.registerService(
            Condition.class, Condition.INSTANCE, properties);

        Thread.sleep(1000l);

        ServiceTracker<Example, Example> exampleTracker = new ServiceTracker<>(context,
            Example.class, null);
        exampleTracker.open(true);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(0);

        propList.add("A");
        properties = getProperties(new LinkedList<>(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(1);
        Example example = exampleTracker.getService();
        assertThat(example).isNotNull();

        propList.clear();
        properties = getProperties(new LinkedList<>(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        example = exampleTracker.getService();
        assertThat(example).isNull();

    }

    /**
     * properties = getProperties(propList);
     * @throws InterruptedException
     */
    @Test
    public void testExampleNotWorking1() throws InterruptedException
    {
        List<String> propList = new LinkedList<>();

        Dictionary<String, Object> properties = getProperties(propList);
        ServiceRegistration<Condition> registration = context.registerService(
            Condition.class, Condition.INSTANCE, properties);

        Thread.sleep(1000l);

        ServiceTracker<Example, Example> exampleTracker = new ServiceTracker<>(context,
            Example.class, null);
        exampleTracker.open(true);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(0);

        propList.add("A");
        properties = getProperties(propList);
        registration.setProperties(properties);
        Thread.sleep(1000l);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(1);
        Example example = exampleTracker.getService();
        assertThat(example).isNotNull();

        propList.clear();
        properties = getProperties(propList);
        registration.setProperties(properties);
        Thread.sleep(1000l);

        example = exampleTracker.getService();
        assertThat(example).isNull();
    }

    /**
     * properties = getProperties(Collections.synchronizedCollection(propList));
     * @throws InterruptedException
     */
    @Test
    public void testExampleNotWorking2() throws InterruptedException
    {

        List<String> propList = new LinkedList<>();

        Dictionary<String, Object> properties = getProperties(
            Collections.synchronizedCollection(propList));
        ServiceRegistration<Condition> registration = context.registerService(
            Condition.class, Condition.INSTANCE, properties);

        Thread.sleep(1000l);

        ServiceTracker<Example, Example> exampleTracker = new ServiceTracker<>(context,
            Example.class, null);
        exampleTracker.open(true);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(0);

        propList.add("A");
        properties = getProperties(Collections.synchronizedCollection(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        assertThat(exampleTracker.getTrackingCount()).isEqualTo(1);
        Example example = exampleTracker.getService();
        assertThat(example).isNotNull();

        propList.clear();
        properties = getProperties(Collections.synchronizedCollection(propList));
        registration.setProperties(properties);
        Thread.sleep(1000l);

        example = exampleTracker.getService();
        assertThat(example).isNull();

    }

    private Dictionary<String, Object> getProperties(Collection<String> propList)
    {
        Dictionary<String, Object> properties = new Hashtable<>();
        properties.put(Condition.CONDITION_ID, "demo");
        properties.put("prop", propList);
        return properties;
    }

}