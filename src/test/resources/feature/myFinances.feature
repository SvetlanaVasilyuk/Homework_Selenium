# language: ru

Функционал: Финансы в личном кабинете

  Сценарий: Проверка финансовой свободы в ЛК
    Дано открывается страница сайта для авторизации
    И пользователь "demo" авторизуется в ЛК с паролем "demo"
    И вводит одноразовый пароль "0000"
    Когда переходит на вкладку "Обзор"
    Тогда отображается блок с названием Финансовая свобода
    И отображается сумма финансов пользователя
    И при наведении курсора отображается всплывающая информация о финансах