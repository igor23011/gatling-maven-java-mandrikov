package otus;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static otus.Feeder.*;

// Класс хранит в себе сценарий запуска

public class CommonScenario {

    protected static final ScenarioBuilder scenario = scenario("Scenario")
            .feed(usersFeeder())
            .feed(departureFeeder())
            .feed(arriveFeeder())
            .exec(Action.getMainPage())
            .pause(4)
            .exec(Action.getUserSession())
            .exec(Action.postLogin())
            .pause(4)
            .exec(Action.getPageFlight())
           // .exec(Action.getCityFlights)
            .exec(Action.postCheckCityFlight())
            .pause(3)
            .exec(Action.postCheckFlight())
            .exec(Action.postPaymentFlight())
            .exec(Action.postInvoiceFlight())
            .exec(Action.getHomePage());

 }
