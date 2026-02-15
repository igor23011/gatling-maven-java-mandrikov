package otus;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.pause;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
import static otus.Otus.*;

// Класс хранит в себе сценарий запуска

public class CommonScenario {

    protected static final ScenarioBuilder scenario = scenario("Scenario")
            .exec(Action.getMainPage)
            .exec(Action.getUserSession)
            .exec(pause(3)
            .exec(Action.postLogin)
            .exec(Action.getPageFlight)
            .exec(Action.postCheckCityFlight)
            .exec(pause(3))
            .exec(Action.postCheckFlight)
            .exec(Action.postPaymentFlight)
            .exec(Action.postInvoiceFlight)
            .exec(Action.getHomePage));

 }
