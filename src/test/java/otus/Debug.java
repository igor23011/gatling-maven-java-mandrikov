package otus;
import static otus.CommonScenario.scenario;
import static otus.Otus.*;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
import io.gatling.javaapi.core.Simulation;

//Точка запуска сценария

public class Debug extends Simulation {
    {
        setUp(scenario.injectOpen(atOnceUsers(1))).assertions(assertion).protocols(httpProtocol);
    }
}

