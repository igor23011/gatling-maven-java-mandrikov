package otus;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.recorder.internal.bouncycastle.oer.its.ieee1609dot2.basetypes.Duration.minutes;
import static otus.CommonScenario.scenario;
import static otus.Otus.*;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;

import io.gatling.javaapi.core.Simulation;

//Точка запуска сценария

public class Debug extends Simulation {

    // Режим Debug
    //    {
//        setUp(scenario
//                .injectOpen(atOnceUsers(4)))
//                .assertions(assertion)
//                .protocols(httpProtocol);
//    }

// Cтупенчатый тест от 0 до 100% (деградация системы)

//    {
//        setUp(
//                scenario
//                        .injectOpen(incrementUsersPerSec(1.0)
//                                .times(6)
//                                .eachLevelLasting(150)
//                                .separatedByRampsLasting(10)
//                                .startingFrom(1))
//                        // .assertions(assertion)
//                                .protocols(httpProtocol)); // Double
//
//
//    }


    // Тест производительности 80 % от найденного максимума
    {
        setUp(scenario.injectOpen(
                        rampUsersPerSec(0).to(2).during(60),
                 constantUsersPerSec(2).during(3600))
                .protocols(httpProtocol));
    }

}

