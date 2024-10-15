

package com.example;

import org.springframework.stereotype.Component;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

@Component
public class WeatherApiClient {

    private static final String API_KEY = "be15f3b3570b07a63433286f93894d81";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public String getWeather(String city) {
        try {
            // Кодируем название города в правильный формат для URL
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

            // Формируем URL для запроса
            String url = String.format(API_URL, encodedCity, API_KEY);
            System.out.println("Отправляем запрос по URL: " + url); // Логируем URL

            // Открываем соединение и отправляем запрос
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // Получаем код ответа от сервера
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                return "Ошибка запроса: код ответа " + responseCode;
            }

            // Читаем ответ сервера с использованием UTF-8
            StringBuilder inline = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    inline.append(line);
                }
            }

            // Парсим JSON-ответ
            JSONObject json = new JSONObject(inline.toString());
            double temp = json.getJSONObject("main").getDouble("temp");
            int humidity = json.getJSONObject("main").getInt("humidity");
            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");

            // Формируем ответ
            return String.format("Погода в %s: %.1f°C, %s, влажность: %d%%", city, temp, description, humidity);

        } catch (Exception e) {
            e.printStackTrace();  // Печатаем ошибку для отладки
            return "Произошла ошибка при получении погоды.";
        }
    }
}







//package com.example;
//
//import org.springframework.stereotype.Component;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.Scanner;
//import org.json.JSONObject;
//
//@Component
//public class WeatherApiClient {
//
//    private static final String API_KEY = "babbde291bf07ad7bcf5e8a82048dfe2";
//    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
//
//    public String getWeather(String city) {
//        try {
//            // Формируем URL для запроса
//            String url = String.format(API_URL, city, API_KEY);
//
//            // Открываем соединение и отправляем запрос
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//
//            // Получаем код ответа от сервера
//            int responseCode = connection.getResponseCode();
//            if (responseCode != 200) {
//                return "Ошибка запроса: код ответа " + responseCode;
//            }
//
//            // Читаем ответ сервера с использованием UTF-8
//            StringBuilder inline = new StringBuilder();
//            try (BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    inline.append(line);
//                }
//            }
//
//            // Парсим JSON-ответ
//            JSONObject json = new JSONObject(inline.toString());
//            double temp = json.getJSONObject("main").getDouble("temp");
//            int humidity = json.getJSONObject("main").getInt("humidity");
//            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
//
//            // Формируем ответ
//            return String.format("Погода в %s: %.1f°C, %s, влажность: %d%%", city, temp, description, humidity);
//
//        } catch (Exception e) {
//            // Обработка исключений
//            return "Произошла ошибка при получении погоды.";
//        }
//    }
//}










//package com.example;
//
//import org.springframework.stereotype.Component;
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Scanner;
//import org.json.JSONObject;
//
//@Component
//public class WeatherApiClient {
//
//    private static final String API_KEY = "babbde291bf07ad7bcf5e8a82048dfe2";
//    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
//
//    public String getWeather(String city) throws IOException {
//        // Формируем URL для запроса
//        String url = String.format(API_URL, city, API_KEY);
//
//        // Открываем соединение и отправляем запрос
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("GET");
//        connection.connect();
//
//        // Получаем код ответа от сервера
//        int responseCode = connection.getResponseCode();
//        if (responseCode != 200) {
//            throw new IOException("Ошибка запроса: код ответа " + responseCode);
//        }
//
//        // Читаем ответ сервера
//        StringBuilder inline = new StringBuilder();
//        try (Scanner scanner = new Scanner(connection.getInputStream())) {
//            while (scanner.hasNext()) {
//                inline.append(scanner.nextLine());
//            }
//        }
//
//        // Парсим JSON-ответ
//        JSONObject json = new JSONObject(inline.toString());
//        double temp = json.getJSONObject("main").getDouble("temp");
//        int humidity = json.getJSONObject("main").getInt("humidity");
//        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
//
//        // Формируем ответ
//        return String.format("Погода в %s: %.1f°C, %s, влажность: %d%%", city, temp, description, humidity);
//    }
//}









//package com.example;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class WeatherApiClient {
//
//    public String getWeather(String city) throws Exception {
//        // Логика получения погоды
//        return "Погода в " + city + ": 20°C, ясно";
//    }
//}








//package com.example;
//
//import org.apache.hc.client5.http.fluent.Request;
//import org.json.JSONObject;
//
//public class WeatherApiClient {
//
//    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";
//    private final String apiKey;
//
//    public WeatherApiClient(String apiKey) {
//        this.apiKey = apiKey;
//    }
//
//    public String getWeather(String city) throws Exception {
//        String url = String.format(API_URL, city, apiKey);
//        String response = Request.get(url).execute().returnContent().asString();
//
//        // Парсим ответ JSON
//        JSONObject json = new JSONObject(response);
//        double temp = json.getJSONObject("main").getDouble("temp");
//        int humidity = json.getJSONObject("main").getInt("humidity");
//        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
//
//        return String.format("Погода в %s: %s, температура: %.1f°C, влажность: %d%%", city, description, temp, humidity);
//    }
//}














//package com.example;
//
//import org.apache.hc.client5.http.fluent.Request;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//
//@Component
//public class WeatherApiClient {
//
//    @Value("${weather.api.key}")
//    private String apiKey;
//
//    @Value("${weather.api.url}")
//    private String apiUrl;
//
//    public String getWeather(String city) {
//        String url = String.format(apiUrl, city, apiKey);
//        try {
//            String response = Request.get(url).execute().returnContent().asString();
//            return parseWeatherResponse(response);
//        } catch (IOException e) {
//            return "Error fetching weather data: " + e.getMessage();
//        }
//    }
//
//    private String parseWeatherResponse(String response) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(response);
//        String temperature = root.path("main").path("temp").asText();
//        String humidity = root.path("main").path("humidity").asText();
//        String description = root.path("weather").get(0).path("description").asText();
//
//        return String.format("Temperature: %s°C\nHumidity: %s%%\nDescription: %s", temperature, humidity, description);
//    }
//}
