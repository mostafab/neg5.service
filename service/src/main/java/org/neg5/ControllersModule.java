package org.neg5;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.neg5.controllers.Controller;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;

public class ControllersModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Controller> multibinder = Multibinder.newSetBinder(binder(), Controller.class);

        Reflections reflections = new Reflections("org.neg5.controllers");
        reflections.getSubTypesOf(Controller.class).stream()
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                .forEach(clazz -> multibinder.addBinding().to(clazz));
    }
}
