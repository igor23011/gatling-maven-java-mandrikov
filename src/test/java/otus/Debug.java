package otus;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
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
//        setUp(scenario.injectOpen(
//                        rampUsersPerSec(0).to(6).during(1200))
//                .protocols(httpProtocol));
//    }

    // Тест производительности 80 % от найденного максимума
    {
        setUp(scenario.injectOpen(
                        rampUsersPerSec(0).to(4).during(60),
                 constantUsersPerSec(4).during(3600))
                .protocols(httpProtocol));
    }
}

