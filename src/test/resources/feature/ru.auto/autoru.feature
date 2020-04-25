# language: ru
@all @homework28
Функционал: Навигация по объявлениям

  Структура сценария: Сравнение количества объявлений в списке фильтра и на кнопке
    * открывается страница авто.ру "https://auto.ru/"
    * в списке марок отображается количество объявлений по марке <марка>
    * пользователь переходит на страницу с объявлениями по марке <марка>
    * ожидается появление кнопки "^Показать .* предложени[йяе]$" с отображаемым количеством объявлений с погрешностью 50
    * в списке моделей отображается количество объявлений по модели <модель>
    * пользователь переходит на страницу с объявлениями по модели <модель>
    * ожидается появление кнопки "^Показать .* предложени[йяе]$" с отображаемым количеством объявлений с погрешностью 50

    Примеры:
    | марка | модель |
    | Honda | Crosstour |
    | Citroen | C3 |
    | Citroen | C4 Aircross |
    | LADA (ВАЗ) | Granta |
