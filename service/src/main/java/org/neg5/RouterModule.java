package org.neg5;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.neg5.routers.Neg5EnumRouter;
import org.neg5.routers.Router;
import org.neg5.routers.SystemStatusRouter;
import org.neg5.routers.TournamentRouter;
import org.neg5.routers.TournamentStatsRouter;

public class RouterModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Router> multibinder = Multibinder.newSetBinder(binder(), Router.class);

        multibinder.addBinding().to(SystemStatusRouter.class);
        multibinder.addBinding().to(TournamentRouter.class);
        multibinder.addBinding().to(Neg5EnumRouter.class);
        multibinder.addBinding().to(TournamentStatsRouter.class);
    }
}
