package org.neg5;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.neg5.routers.Router;
import org.neg5.routers.SystemStatusRouter;

public class RouterModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Router> multibinder = Multibinder.newSetBinder(binder(), Router.class);

        multibinder.addBinding().to(SystemStatusRouter.class);
    }
}
