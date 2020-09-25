package org.bipolis.tests.servicepropcoll;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(service = Example.class, reference = @Reference(name = "markerCondition", service = Condition.class, target = "(&("
    + Condition.CONDITION_ID + "=demo)(prop=A))"))
public class Example
{

    @Activate
    public void activate()
    {
        System.out.println("###############################");
        System.out.println("####### ACTIVATE EXAMPLE ######");
        System.out.println("###############################");
    }

    @Deactivate
    public void deactivate()
    {
        System.out.println("###############################");
        System.out.println("##### DE-ACTIVATE EXAMPLE #####");
        System.out.println("###############################");

    }

}
