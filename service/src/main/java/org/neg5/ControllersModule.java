package org.neg5;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.neg5.controllers.BaseController;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;

public class ControllersModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<BaseController> multibinder = Multibinder.newSetBinder(binder(), BaseController.class);

        Reflections reflections = new Reflections("org.neg5.controllers");
        reflections.getSubTypesOf(BaseController.class).stream()
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                .forEach(clazz -> multibinder.addBinding().to(clazz));
    }
}
