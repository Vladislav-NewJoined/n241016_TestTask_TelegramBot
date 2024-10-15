package com.example.commands;

import com.example.WeatherApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotCommonCommands implements AppBotCommand {

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Override
    public String processCommand(String command) {
        try {
            // Убираем команду "/weather" и оставляем только название города
            String city = command.replaceFirst("/weather\\s+", "").trim();

            if (city.isEmpty()) {
                return "Пожалуйста, укажите название города.";
            }

            // Получаем информацию о погоде
            return weatherApiClient.getWeather(city);

        } catch (Exception e) {
            return "Произошла ошибка при обработке команды.";
        }
    }
}
