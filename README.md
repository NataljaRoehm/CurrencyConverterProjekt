# ProjectShop

            *Описание проекта*

При написании данного проекта мы старались соблюдать основные принципы ООП 
и максимально использовать знания полученные на курсе: BASIC COURSE JAVA.

Данный проект представляет собой интернет-магазин,
который специализируется на продаже книг, музыки и фильмов.

В магазине имеется склад для продукции, бухгалтерия,  
служба доставки товаров и конечно мы дорожим отзывами наших клиентов. 

Программа "Интернет магазин" разделяет функционал для администратора и пользователя.

В проекте реализованы следующие функциональные возможности:

### Функционал для администратора

При запуске программы для администратора необходимо ввести пароль "admin", 
после чего открывается меню, в котором доступны следующие функции:

**Добавление нового товара в базу данных**
**Удаление товара из базы данных**
**Обновление товара в базе данных**
**Генерация отчетов**
**Выход из программы**

### Функционал для пользователя

При запуске программы для пользователя требуется авторизация. 
Если у пользователя нет личного кабинета, он может его создать.
После авторизации доступны следующие функции:

**Просмотр продукции**
**Совершение покупки**
**Добавление отзыва**
**Выход из программы**

## Структура java-проекта

* `de.ait`
    * `app` - здесь размещается точка входа в приложение (`main`)
    * `models` - модели предметной области (User, Product, Book,  Film, Music, CashWarrant, DeliveryOffGoods,
                 Order, Review).
    * `repositories` - функционал, который отвечает за получение и сохранение данных. 
                       (UsersRepository, ProductsRepository, BookRepository, FilmsRepository, MusicsRepository, 
                        CashWarrantRepository, DeliveryOffGoodsRepository, OrderRepository, ReviewRepository). 
    * `services` - бизнес-логика проекта (UserService, ProductService, BookService, FilmService, MusicService, 
                  OrderService, CashWarrantService,  DeliveryOffGoodsService,  ReviewService). 
    *  `test`   - тесты для методов бизнес-логики.

### Архитектура.

В проекте мы придерживались принципа разделения логики:

Класс Main имеет возможность взаимодействовать только с классами группы Services, а классы группы Services 
имеют возможность взаимодействовать только с классами группы Repositories, что в максимальной мере обеспечивает
инкапсуляцию.

Создание новых продуктов обеспечивают классы  **Product, Book,  Film, Music**.

Отношение между данными классами определяет композиция.

Класс Product содержит общие поля для данных классов. Классы Book,  Film, Music имеют доступ к полям 
класса Product с помощью поля Product productInfo.

### Общая логика работы -

1. При авторизации пользователя на сайте Main получает данные для создания User.
   Main -> UserService -> UsersRepository -> users.txt
2. Добавление новой продукции:
   Main -> (ProductService, BookService, FilmService, MusicService) ->  
           (ProductsRepository, BookRepository, FilmsRepository, MusicsRepository) -> 
           (products txt, books.txt, film.txt, music.txt)
3. Покупка продукции:
   Main -> (OrderService) ->  (ProductsRepository, BookRepository, FilmsRepository, MusicsRepository,
                               CashWarrantRepository, DeliveryOffGoodsRepository, OrderRepository) ->
                          -> (products txt, books.txt, film.txt, music.txt) - удаление продукта.
                          -> (cash.txt, delivery.txt, order.txt) - добавление данных.
4. Добавление отзыва:
   Main -> (ReviewService) -> (ReviewRepository) -> reviews.txt
5. Отчёты:
   просмотры, сортировки, фильтры по параметрам для любого документа. 
   Так же получение данных о прибыли с помощью обращения к cash.txt


### Используемые технологии
Использование интерфейсов и наследования.
Работа с файлами в формате .txt.
Система контроля версий Git.

### Как запустить программу
Скачайте репозиторий с программой на свой компьютер.
Откройте проект в среде разработки, которая поддерживает Java.
Запустите программу через главный класс Main.


# Авторы
## Проект выполнен командой разработчиков:

Лидер команды:  @Leonid Usatii
@Natalia Bodnaryuk
@Oksana Patsii
@Julia Schönwald