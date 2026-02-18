package otus;

import io.gatling.javaapi.core.FeederBuilder;
import static io.gatling.javaapi.core.CoreDsl.csv;

public class Feeder {

    // Фидер для данных пользователей (логин и пароль)
    public static FeederBuilder<String> usersFeeder() {
        return csv("users.csv").random();
    }

    // Фидер для городов отправления
    public static FeederBuilder<String> departureFeeder() {
        return csv("departureCity.csv").random();
    }

    // Фидер для городов прибытия
    public static FeederBuilder<String> arriveFeeder() {
        return csv("arriveCity.csv").random();
    }
}
