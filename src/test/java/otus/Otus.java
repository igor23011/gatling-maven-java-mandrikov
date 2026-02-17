package otus;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

// Класс хранит в себе все переменные, константы и т.д

public class Otus {

    // Load VU count from system properties
    // Reference: https://docs.gatling.io/guides/passing-parameters/
    protected static final int vu = Integer.getInteger("vu", 1);

    // Define HTTP configuration
    // Reference: https://docs.gatling.io/reference/script/protocols/http/protocol/
    protected static final HttpProtocolBuilder httpProtocol = http.baseUrl("http://webtours.load-test.ru:1080")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .userAgentHeader(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
            .header("Accept-Encoding", "gzip, deflate")
            .header("Accept-Language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
            .header("Connection", "keep-alive")

            .header("Host", "webtours.load-test.ru:1080")
            .header("Origin", "http://webtours.load-test.ru:1080")
            .header("Priority", "u=4")
            .header("Referer", "http://webtours.load-test.ru:1080/cgi-bin/reservations.pl?page=welcome")
            .header("Upgrade-Insecure-Requests", "1");

    // Define assertions
    // Reference: https://docs.gatling.io/reference/script/core/assertions/
    protected static final Assertion assertion = global().failedRequests().count().lt(1L);

    // Define injection profile and execute the test
    // Reference: https://docs.gatling.io/reference/script/core/injection/

}
