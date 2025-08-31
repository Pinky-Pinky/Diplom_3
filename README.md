# Diplom_3 - Автотесты для Stellar Burgers

Это проект дипломной работы Яндекс.Практикум, представляющий собой набор автотестов для веб-приложения Stellar Burgers с использованием Page Object модели.

## Технологии
| Название            | Версия/Описание                     |
|---------------------|-------------------------------------|
| Java                | 11                                  |
| JUnit               | 4.13.2                              |
| Maven               | 3.9.0                               |
| Selenium WebDriver  | 4.20.0                              |
| WebDriverManager    | 5.8.0                               |
| Allure Framework    | 2.29.0                              |
| AspectJ             | 1.9.22.1                            |

## Настройка
1. Убедись, что установлен Java 11 и Maven 3.9.0.
2. Клонируй репозиторий: https://github.com/Pinky-Pinky/Diplom_3.git
3. Перейди в директорию проекта: cd Diplom_3
4. Установи зависимости: mvn clean install

## Как запускать
1. Запусти тесты в Chrome (по умолчанию) или Yandex: mvn clean test
- Для Yandex укажи параметр: mvn clean test -Dbrowser=yandex
2. Сгенерируй и открой Allure-отчёт: allure serve target/allure-results


## Дополнительно
- Тесты покрывают регистрацию, вход и разделы конструктора Stellar Burgers.
- Подробности о запуске и конфигурации доступны в pom.xml.
- Для оформления Markdown использованы рекомендации из (https://blog.jetbrains.com/idea/2022/08/markdown-support-improvements/).