package otus;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static otus.Feeder.*;

// Класс хранит в себе все HTTP Запросы

public class Action {

    // Запрос GET для загрузки начальной страницы
    public static HttpRequestActionBuilder getMainPage() {
        return http("getMainPage")
                .get("/webtours/")
                .check(status().is(200));
    }


    //Запрос GET для получения userSession
    public static HttpRequestActionBuilder getUserSession() {
        return http("getUserSession")
                .get("/cgi-bin/nav.pl")
                .queryParam("in", "home")
                .check(css("input[name='userSession']", "value").saveAs("userSession"))
                .check(status().is(200));
    }


    // Запрос POST для аутентификации
    public static HttpRequestActionBuilder postLogin() {
        return http("login")
                .post("/cgi-bin/login.pl")
                .formParam("username", "#{username}")
                .formParam("password", "#{password}")
                .formParam("userSession", "#{userSession}")
                .formParam("login.x", "53")
                .formParam("login.y", "8")
                .formParam("JSFormSubmit", "off")
                .check(status().is(200));
    }


    //Запрос GET для открытия первой страницы Flight
    public static HttpRequestActionBuilder getPageFlight() {
        return http("getPageFlight")
                .get("/cgi-bin/nav.pl")
                .queryParam("page", "menu")
                .queryParam("in", "flights")
                .queryParam("userSession", "#{userSession}")
                .check(status().is(200));
    }


    //Запрос GET для полчуние списка городов Flight
    public static HttpRequestActionBuilder getCityFlights() {
        return http("getCityFlights1")
                .get("/cgi-bin/reservations.pl")
                .queryParam("page", "welcom")
                .queryParam("userSession", "#{userSession}")
                .check(
                        regex("<option value=\"([^\"]*)\">").findAll().saveAs("cities"))
                .check(status().is(200));
    }


    //Запрос POST для выбора города отправление и прибытия
    public static HttpRequestActionBuilder postCheckCityFlight() {
        return http("postCheckCityFlight")
                .post("/cgi-bin/reservations.pl")
                .formParam("advanceDiscount", "0")
                .formParam("depart", "#{departureCity}")
                .formParam("departDate", LocalDateTime.now().plusDays(2).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .formParam("arrive", "#{arriveCity}")
                .formParam("returnDate", LocalDateTime.now().plusDays(3).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))
                .formParam("numPassengers", "1")
                .formParam("seatPref", "None")
                .formParam("seatType", "Coach")
                .formParam("findFlights.x", "32")
                .formParam("findFlights.y", "6")
                .formParam(".cgifields", "roundtrip")
                .formParam(".cgifields", "seatType")
                .formParam(".cgifields", "seatPref")
                .check(
                        regex("<input.*?name=\"outboundFlight\".*?value=\"([^\"]*)\".*?[checked].*>").saveAs("selectedFlight")
                )
                .check(status().is(200));
    }


    //Запрос POST для выбора рейса
    public static HttpRequestActionBuilder postCheckFlight() {
        return http("postCheckCityFlight")
                .post("/cgi-bin/reservations.pl")
                .formParam("advanceDiscount", "0")
                .formParam("outboundFlight", "#{selectedFlight}")
                .formParam("numPassengers", "1")
                .formParam("seatPref", "None")
                .formParam("seatType", "Coach")
                .formParam("reserveFlights.x", "52")
                .formParam("reserveFlights.y", "6")
                .check(status().is(200));
    }


    //Запрос POST для оплаты
    public static HttpRequestActionBuilder postPaymentFlight() {
        return http("postPaymentFlight")
                .post("/cgi-bin/reservations.pl")
                .formParam("firstName", "Igor")
                .formParam("lastName", "Man")
                .formParam("address1", "Red")
                .formParam("address2", "Moscow")
                .formParam("pass1", "Igor Man")
                .formParam("creditCard", "111122223333")
                .formParam("expDate", "2026")
                .formParam("oldCCOption", "")
                .formParam("numPassengers", "1")
                .formParam("seatType", "Coach")
                .formParam("seatPref", "None")
                .formParam("outboundFlight", "#{selectedFlight}")
                .formParam("advanceDiscount", "0")
                .formParam("returnFlight", "")
                .formParam("JSFormSubmit", "off")
                .formParam("buyFlights.x", "54")
                .formParam(".cgifields", "saveCC")
                .check(status().is(200));
    }


    //Запрос POST для Invoice
    public static HttpRequestActionBuilder postInvoiceFlight() {
        return http("postInvoiceFlight")
                .post("/cgi-bin/reservations.pl")
                .formParam("Book Another.x", "26")
                .formParam("Book Another.y", "4")
                .check(status().is(200));
    }


    //Запрос GET для перехода станицу Home
    public static HttpRequestActionBuilder getHomePage() {
        return http("getHomePage")
                .get("/cgi-bin/nav.pl")
                .queryParam("page", "menu")
                .queryParam("in", "home")
                .check(status().is(200));
    }

}



