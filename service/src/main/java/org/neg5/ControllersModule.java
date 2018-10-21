package org.neg5;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import org.neg5.controllers.Neg5EnumController;
import org.neg5.controllers.Controller;
import org.neg5.controllers.SystemStatusController;
import org.neg5.controllers.TournamentController;
import org.neg5.controllers.TournamentStatsController;

public class ControllersModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Controller> multibinder = Multibinder.newSetBinder(binder(), Controller.class);

        multibinder.addBinding().to(SystemStatusController.class);
        multibinder.addBinding().to(TournamentController.class);
        multibinder.addBinding().to(Neg5EnumController.class);
        multibinder.addBinding().to(TournamentStatsController.class);
    }
}
