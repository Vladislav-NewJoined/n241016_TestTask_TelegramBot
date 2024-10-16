package com.example;

// Погоду на этом веб-сайте запрашивать:  https://home.openweathermap.org/api_keys

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestTask {
    public static void main(String[] args) {
        System.out.println("""
            Задание: Создать тестовый Telegram бот, который, при отправке в него команды
            с названием города, возвращает информацию о погоде на текущую дату в этом городе.
            
            Решение:
            """);

        System.out.println("""
            Для реализации данной задачи создан Telegram бот.
            Данные Telegram бота:
                botName: TestBot_005 или @TestBot_005
                userName: @kkkllll_005_bot
                token: 6882256834:AAH5Fg-wUdKw7Rdqj8s9kXDgVt0R08tDnlY
            
            Нужно зайти в этот чат-бот, отправить команду 'weather Moscow', где вместо Moscow
            можно вписать название любого другого города, например London. В ответ, в Telegram боте,
            придёт информация о погоде в этом городе на текущую дату.
            Данные о погоде берутся с этого API ресурса:
            https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric
            Для взаимодействия с этим API ресурсом используется следующий ключ:
            76daf4ddb08ab3fa0a9e6f456d4f53b6
            Необходимо оговориться, что этот API ресурс даёт данные о погоде бесплатно
            лишь по трём запросам в день (остальные запросы платные), либо речь идёт о трёх запросах
            с использованием одного и того же ключа.
            Вы можете сгенерировать свой собственный ключ, для этого нужно зайти на сайт
            со следующими регистрационными данными:
            Логин: sozin.vladislav3@inbox.ru
            Пароль: 12345678
            далее перейти на следующую вкладку сайта:
            https://home.openweathermap.org/api_keys
            и там сгенерировать новый ключ.
            При это (насколько мне удалось понять), прежние ключи не удаляются и не теряют своей валидности,
            т.е. ими тоже можно пользоваться. Ключ, в этом проекте, вставляется в соответствующее поле
            в строке 16 в классе WeatherApiClient.
            """);

        SpringApplication.run(TestTask.class, args);
    }
}
