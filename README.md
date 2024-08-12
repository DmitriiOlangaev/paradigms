# Мои решения курса «Парадигмы программирования»

[Условия домашних заданий](https://www.kgeorgiy.info/courses/paradigms/homeworks.html)

## Домашнее задание 13. Простые числа на Prolog

Решение:
    [primes](prolog-solutions/primes.pl)

Модификации
* *Базовая*
    * Код должен находиться в файле `prolog-solutions/primes.pl`.
    * [Исходный код тестов](prolog/prtest/primes/PrimesTest.java)
        * Запускать c указанием сложности (`easy`, `hard` или `bonus`) и модификации.
* *Nth* (32, 33)
    * Добавьте правило `nth_prime(N, P)`, подсчитывающее `N`-ое простое число:
      `nth_prime(1, 2)`, `nth_prime(26, 101)`.
* *Index* (34, 34)
    * Добавьте правило `prime_index(P, N)`, подсчитывающее номер простого числа:
      `prime_index(2, 1)`, `prime_index(101, 26)`
* *Gcd* (36, 37)
    * Добавьте правило `gcd(A, B, GCD)`,
      подсчитывающее НОД(`A`, `B`) через разложение на простые множители:
      `gcd(4, 6, 2)`.
* *GcdLcm* (38, 39). К модификации *Gcd*
    * Добавьте правило `lcm(A, B, LCM)`,
      подсчитывающее НОК(`A`, `B`) через разложение на простые множители:
      `lcm(4, 6, 12)`.

Для запуска тестов можно использовать скрипты
[TestProlog.cmd](prolog/TestProlog.cmd) и [TestProlog.sh](prolog/TestProlog.sh)
* Репозиторий должен быть скачан целиком.
* Скрипты должны находиться в каталоге `prolog`
  (их нельзя перемещать, но можно вызывать из других каталогов).
* Полное имя класса теста указывается в качестве первого аргумента командной строки,
  например, `prtest.primes.PrimesTest`.
* Тестируемое решение должно находиться в текущем каталоге.


## Исходный код к лекциям по Prolog

Запуск Prolog
* [Windows](prolog/RunProlog.cmd)
* [*nix](prolog/RunProlog.sh)

Лекция 1. Факты, правила и вычисления
* [Учебный план](prolog/examples/1_1_plan.pl)
* [Вычисления](prolog/examples/1_2_calc.pl)
* [Списки](prolog/examples/1_3_lists.pl)
* [Правила высшего порядка](prolog/examples/1_4_high-order.pl)

Лекция 2. Задачи, унификация и объекты
* [Задача о расстановке ферзей](prolog/examples/2_1_queens.pl)
* [Задача Эйнштейна](prolog/examples/2_2_einstein.pl)
* [Арифметические выражения](prolog/examples/2_3_expressions.pl)

Лекция 3. Преобразование в строку и разбор
* [Преобразование через термы](prolog/examples/3_1_terms.pl)
* [Преобразование через списки](prolog/examples/3_2_chars.pl)
* [Грамматики](prolog/examples/3_3_grammar.pl)


## Домашнее задание 12. Комбинаторные парсеры

Решение:
    [expressions.clj](clojure-solutions/expression.clj)

Модификации
* *Base*
    * Код должен находиться в файле `clojure-solutions/expression.clj`.
    * [Исходный код тестов](clojure/cljtest/parsing/ParserTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
* *Variables* (32, 33). Дополнительно реализовать поддержку:
    * Переменных, состоящих из произвольного количества букв `XYZ` в любом регистре
        * Настоящее имя переменной определяется первой буквой ее имени
* *MinMax*. (34, 35). Сделать модификацию *Variables* и дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `Min` (`min`) – минимум, `(min 1 2 6)` равно 1;
        * `Max` (`max`) – максимум, `(max 1 2 6)` равно 6;
* *Complex* (36, 37). Сделать модификацию *Variables* и дополнительно реализовать поддержку:
    * Правоассоциативных бинарных операций над комплексными числами в форме `im op re`:
        * `AbsC` (`absc`) – модуль `3 absc 4` равно 5;
        * `PhiC` (`phic`) – аргумент `841 phic 540` примерно равно 1.
* *ComplexTrig* (38, 39). Сделать модификацию *Variables* и дополнительно реализовать поддержку:
    * Унарных операций в форме `op re`
        * `Sin` (`sin`) – синус, `sin 4846147` примерно равно 1;
        * `Cos` (`cos`) – косинус, `cos 5419351` примерно равно 1.
    * Правоассоциативных бинарных операций   над комплексными числами в форме `im op re`:
        * `SinC` (`sinc`) – действительная часть синуса `4 sinc 8` примерно равно 27;
        * `CosC` (`cosc`) – действительная часть косинуса `4 cosc 19` примерно равно 27.
    * Постфиксных унарных операций в форме `im op`
        * `SinP` (`sinp`) – действительная часть синуса `3 sinp` равно 0;
        * `CosP` (`cosp`) – действительная часть косинуса, `3 cosp` примерно равно 10;
        * Префиксные операции выполняются до постфиксных;
        * `toStringInfix` должно окружать вызовы постфиксных операций скобками, например, `(3 cosp)`.


## Домашнее задание 11. Объектные выражения на Clojure

Решение:
    [object](clojure-solutions/object.clj)

Модификации
* *Базовая*
    * Код должен находиться в файле `clojure-solutions/expression.clj`.
    * [Исходный код тестов](clojure/cljtest/object/ObjectTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
* *ExpLn* (32, 33). Дополнительно реализовать поддержку:
    * унарных операций:
        * `exp` – экспонента, `(exp 8)` примерно равно 2981;
        * `ln`  – натуральный логарифм абсолютной величины, `(ln -2981)` примерно равно 8.
* *PowLog* (34, 35). Дополнительно реализовать поддержку:
    * бинарных операций:
        * `pow` – возведение в степень, `(pow 2 3)` равно 8;
        * `log` – логарифм абсолютной величины по основанию абсолютной величины, `(log -2 -8)` равно 3.
* *MeanVarn* (36, 37). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `Mean` (`mean`) – математическое ожидание аргументов, `(mean 1 2 6)` равно 3;
        * `Varn` (`varn`) – дисперсия аргументов, `(varn 2 5 11)` равно 14;
* *Means* (38, 39). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `ArithMean` (`arithMean`) – арифметическое среднее `(arithMean 1 2 6)` равно 3;
        * `GeomMean` (`geomMean`) – геометрическое среднее `(geomMean 1 2 4)` равно 2;
        * `HarmMean` (`harmMean`) – гармоническое среднее, `(harmMean 2 3 6)` равно 3;


## Домашнее задание 10. Функциональные выражения на Clojure

Решение:
    [expression](clojure-solutions/expression.clj)

Модификации
* *Base*
    * Код должен находиться в файле `clojure-solutions/expression.clj`.
    * [Исходный код тестов](clojure/cljtest/functional/FunctionalTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
* *SinCos* (32, 33). Дополнительно реализовать поддержку:
    * унарных операций:
        * `sin` – синус, `(sin 4846147)` примерно равно 1;
        * `cos` – косинус, `(cos 5419351)` примерно равно 1.
* *SinhCosh* (34, 35). Дополнительно реализовать поддержку:
    * унарных операций:
        * `sinh` – гиперболический синус, `(sinh 3)` немного больше 10;
        * `cosh` – гиперболический косинус, `(cosh 3)` немного меньше 10.
* *MeanVarn* (36, 37). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `mean` – математическое ожидание аргументов, `(mean 1 2 6)` равно 3;
        * `varn` – дисперсия аргументов, `(varn 2 5 11)` равно 14.
* *Means* (38, 39). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `ArithMean` (`arithMean`) – арифметическое среднее `(arithMean 1 2 6)` равно 3;
        * `GeomMean` (`geomMean`) – геометрическое среднее `(geomMean 1 2 4)` равно 2;
        * `HarmMean` (`harmMean`) – гармоническое среднее, `(harmMean 2 3 6)` равно 3.


## Домашнее задание 9. Линейная алгебра на Clojure

Решение:
    [linear](clojure-solutions/linear.clj)

Модификации
* *Базовая*
    * Код должен находиться в файле `clojure-solutions/linear.clj`.
    * [Исходный код тестов](clojure/cljtest/linear/LinearTest.java)
        * Запускать c указанием сложности (`easy` или `hard`) и модификации.
* *Cuboid* (32, 33)
    * Назовем _кубоидом_ трёхмерную прямоугольную таблицу чисел.
    * Добавьте операции поэлементного
      сложения (`c+`), вычитания (`c-`), умножения (`c*`) и деления (`cd`)
      кубоидов.
      Например, `(c+ [[[1] [2]] [[3] [4]]] [[[5] [6]] [[7] [8]]])`
      должно быть равно `[[[6] [8]] [[10] [12]]]`.
* *Cuboid4* (34, 35)
    * Назовем _четырёхмерным кубоидом_ четырёхмерную прямоугольную таблицу чисел.
    * Добавьте операции поэлементного
      сложения (`c4+`), вычитания (`c4-`), умножения (`c4*`) и деления (`c4d`)
      четырёхмерных кубоидов.
      Например, `(c4+ [[[[1] [2]] [[3] [4]] [[5] [6]] [[7] [8]]]] [[[[9] [10]] [[11] [12]] [[13] [14]] [[15] [16]]]])`
      должно быть равно `[[[[10] [12]] [[14] [16]] [[18] [20]] [[22] [24]]]]`.
* *Simplex* (36, 37)
    * Назовем _симплексом_ многомерную таблицу чисел,
      такую что для некоторого `n` в ней существуют все значения
      с суммой индексов не превышающей `n` и только эти значения.
    * Добавьте операции поэлементного
      сложения (`x+`), вычитания (`x-`), умножения (`x*`) и деления (`xd`)
      симплексов.
      Например, `(x+ [[1 2] [3]] [[5 6] [7]])`
      должно быть равно `[[6 8] [10]]`.
    * [Исходный код тестов](clojure/cljtest/linear/SimplexTester.java)
* *Broadcast* (38, 39)
    * Назовем _тензором_ многомерную прямоугольную таблицу чисел.
    * _Форма_ тензора – последовательность чисел
      (_s_<sub>1..n</sub>)=(_s_<sub>1</sub>, _s_<sub>2</sub>, …, _s<sub>n</sub>_), где
      _n_ – размерность тензора, а _s<sub>i</sub>_ – число элементов
      по _i_-ой оси.
      Например, форма тензора `[[[2 3 4] [5 6 7]]]`  равна (1, 2, 3),
      а форма `1` равна ().
    * Тензор формы (_s_<sub>1.._n_</sub>) может быть _распространен_ (broadcast)
      до тензора формы (_u_<sub>1.._m_</sub>), если (_s_<sub>i.._n_</sub>) является
      суффиксом (_u<sub>1..m</sub>_).
      Для этого, исходный тензор копируется по недостающим осям.
      Например, распространив тензор `[ [2] [3] ]` формы (2, 1) до
      формы (3, 2, 1) получим `[ [ [2] [3] ] [ [2] [3] ] [ [2] [3] ] ]`,
      а распространив `1` до формы (2, 3) получим `[ [1 1 1] [1 1 1] ]`.
    * Тензоры называются совместимыми, если один из них может быть распространен
      до формы другого.
      Например, тензоры формы (3, 2, 1) и (2, 1) совместимы, а
      (3, 2, 1) и (1, 2) – нет. Числа совместимы с тензорами любой формы.
    * Добавьте операции поэлементного
      сложения (`h+`), вычитания (`h-`), умножения (`h*`) и деления (`hd`)
      совместимых тензоров.
      Если формы тензоров не совпадают, то тензоры меньшей размерности
      должны быть предварительно распространены до тензоров большей размерности.
      Например, `(h+ 1 [ [10 20 30] [40 50 60] ] [100 200 300] )`
      должно быть равно `[ [111 221 331] [141 251 361] ]`.
    * [Исходный код тестов](clojure/cljtest/linear/BroadcastTester.java)


## Исходный код к лекциям по Clojure

Документация
* [Clojure Reference](https://clojure.org/reference/documentation)
* [Clojure Cheat Sheet](https://clojure.org/api/cheatsheet)

Запуск Clojure
* Консоль: [Windows](clojure/RunClojure.cmd), [*nix](clojure/RunClojure.sh)
    * Интерактивный: `RunClojure`
    * С выражением: `RunClojure --eval "<выражение>"`
    * Скрипт: `RunClojure <файл скрипта>`
    * Справка: `RunClojure --help`
* IDE
    * IntelliJ Idea: [плагин Cursive](https://cursive-ide.com/userguide/)
    * Eclipse: [плагин Counterclockwise](https://marketplace.eclipse.org/content/counterclockwise)

[Скрипт со всеми примерами](clojure/examples.clj)

Лекция 1. Функции
* [Введение](clojure/examples/1_1_intro.clj)
* [Функции](clojure/examples/1_2_functions.clj)
* [Списки](clojure/examples/1_3_lists.clj)
* [Вектора](clojure/examples/1_4_vectors.clj)
* [Функции высшего порядка](clojure/examples/1_5_functions-2.clj)

Лекция 2. Внешний мир
* [Ввод-вывод](clojure/examples/2_1_io.clj)
* [Разбор и гомоиконность](clojure/examples/2_2_read.clj)
* [Порядки вычислений](clojure/examples/2_3_evaluation-orders.clj)
* [Потоки](clojure/examples/2_4_streams.clj)
* [Отображения и множества](clojure/examples/2_5_maps.clj)

Лекция 3. Объекты
* [Прототипное наследование](clojure/examples/3_1_js-objects.clj)
    * Библиотека для ДЗ [proto.clj](clojure/examples/proto.clj)
* [Java-классы](clojure/examples/3_2_java-objects.clj)
* [Изменяемое состояние](clojure/examples/3_3_mutable-state.clj)

Лекция 4. Комбинаторные парсеры
* [Базовые функции](clojure/examples/4_1_base.clj)
* [Комбинаторы](clojure/examples/4_2_combinators.clj)
    * Библиотека для ДЗ [parser.clj](clojure/examples/parser.clj)
* [JSON](clojure/examples/4_3_json.clj)

Лекция 5. Макросы и основания математики
* [Макросы](clojure/examples/5_1_macro.clj)
* [Парсеры](clojure/examples/5_2_parser.clj)
* [Числа Чёрча](clojure/examples/5_3_church.clj)


## Тестовое задание на Clojure

Это задание преднозначено для проверки правильности настройки Clojure.
Вам надо проверить, что оно успешно проверяется на вашем компьютере.

Для запуска тестов используются скрипты
[TestClojure.cmd](clojure/TestClojure.cmd) и [TestClojure.sh](clojure/TestClojure.sh)
* Репозиторий должен быть скачан целиком.
* Скрипты должны находиться в каталоге `clojure`
  (их нельзя перемещать, но можно вызывать из других каталогов).
* Тестируемое решение должно находиться в текущем каталоге.
* В качестве аргументов командной строки указывается
  полное имя класса теста, сложность и модификация,
  например, `cljtest.example.ExampleTest hard base`.

Модификации
* *base*
    * Код решения `clojure-solutions/example.clj`
    * [Исходный код тестов](clojure/cljtest/example/ExampleTest.java)
        * Запускать c аргументом `hard` или `easy`.


## Домашнее задание 8. Обработка ошибок на JavaScript

Решение:
    [objectExpression](javascript-solutions/objectExpression.js)

Модификации
* *Base*
    * Код должен находиться в файле `javascript-solutions/objectExpression.js`.
    * [Исходный код тестов](javascript/jstest/prefix/ParserTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
* *Prefix*: *MeanVar* (32, 33). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `Mean` (`mean`) – математическое ожидание аргументов, `(mean 1 2 6)` равно 3;
        * `Var` (`var`) – дисперсия аргументов, `(var 2 5 11)` равно 14.
    * [Исходный код тестов](javascript/jstest/prefix/ParserTest.java)
* *Prefix*: *ProdGeom* (34, 35). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `Product` (`product`) – произведение, `(product 1 2 4)` равно 8;
        * `Geom` (`geom`) – геометрическое среднее, `(geom 1 2 4)` равно 2.
    * [Исходный код тестов](javascript/jstest/prefix/ParserTest.java)
* *Postfix* (36-39). Дополнительно реализовать поддержку:
    * Выражений в постфиксной записи:
        * `(2 3 +)` равно 5
        * функция `parsePostfix`
        * метод `postfix`
    * [Исходный код тестов](javascript/jstest/prefix/PostfixTest.java)
        * Запускать c указанием модификации и сложности (`easy` или `hard`).
* *Postfix*: *MeanVar* (36, 37). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `Mean` (`mean`) – математическое ожидание аргументов, `(1 2 6 mean)` равно 3;
        * `Var` (`var`) – дисперсия аргументов, `(2 5 11 var)` равно 14.
* *Postfix*: *Means* (38, 39). Дополнительно реализовать поддержку:
    * операций произвольного числа аргументов:
        * `ArithMean` (`arithMean`) – арифметическое среднее `(1 2 6 arithMean)` равно 3;
        * `GeomMean` (`geomMean`) – геометрическое среднее `(1 2 4 geomMean)` равно 2;
        * `HarmMean` (`harmMean`) – гармоническое среднее, `(2 3 6 harmMean)` равно 3.
    * [Исходный код тестов](javascript/jstest/prefix/PostfixTest.java)


## Домашнее задание 7. Объектные выражения на JavaScript

Решение:
    [objectExpression](javascript-solutions/objectExpression.js)

Модификации
* *Base*
    * Код должен находиться в файле `javascript-solutions/objectExpression.js`.
    * [Исходный код тестов](javascript/jstest/object/ObjectTest.java)
        * Запускать c указанием модификации и сложности (`easy`, `hard` или `bonus`).
* *SinCos* (32, 33). Дополнительно реализовать поддержку:
    * унарных функций:
        * `Sin` (`sin`) – синус, `3.14159265 sin` примерно равно 0;
        * `Cos` (`cos`) – косинус, `3.14159265 cos` примерно равно -1.
* *SinhCosh* (34, 35). Дополнительно реализовать поддержку:
    * унарных функций:
        * `Sinh` (`sinh`) – гиперболический синус, `3 sinh` немного больше 10;
        * `Cosh` (`cosh`) – гиперболический косинус, `3 cosh` немного меньше 10.
* *ArcTan* (36, 37). Дополнительно реализовать поддержку:
    * функций:
        * `ArcTan` (`atan`) – арктангенс, `1256 atan` примерно равно 1.57;
        * `ArcTan2` (`atan2`) – арктангенс, `841 540 atan2` примерно равно 1.
* *Harmonic* (38, 39). Дополнительно реализовать поддержку:
    * функций от двух аргументов:
        * `Hypot` (`hypot`) – квадрат гипотенузы, `3 4 hypot` равно 25;
        * `HMean` (`hmean`) – гармоническое среднее, `5 20 hmean` равно 8.


## Домашнее задание 6. Функциональные выражения на JavaScript

Решение:
    [functionalExpression](javascript-solutions/functionalExpression.js)

Модификации
* *Базовая*
    * Код должен находиться в файле `javascript-solutions/functionalExpression.js`.
    * [Исходный код тестов](javascript/jstest/functional/FunctionalTest.java)
        * Запускать c аргументом `hard` или `easy`.
* *Pie* (32-39). Дополнительно реализовать поддержку:
    * констант:
        * `pi` – π;
        * `e` – основание натурального логарифма;
* *PieSquare* (32, 33). Дополнительно реализовать поддержку:
    * модификации *Pie*
    * унарных функций:
        * `Square` (`square`) – возведение в квадрат, `3 square` равно 9;
        * `Sqrt` (`sqrt`) – извлечение квадратного корня из модуля аргумента, `-9 sqrt` равно 3.
* *PieCube* (34, 35). Дополнительно реализовать поддержку:
    * модификации *Pie*
    * унарных функций:
        * `Cube` (`cube`) – возведение в куб, `-3 cube` равно −27;
        * `Cbrt` (`cbrt`) – извлечение кубического корня, `-27 cbrt` равно −3.
* *PieMinMax* (36, 37). Дополнительно реализовать поддержку:
    * модификации *Pie*
    * операций:
        * `min5` – минимальный из пяти аргументов, `3 1 4 1 5 min5` равно 1;
        * `max3` – максимальный из трёх аргументов, `3 1 4 max3` равно 4.
* *PieAvgMed* (38, 39). Дополнительно реализовать поддержку:
    * модификации *Pie*
    * операций:
        * `avg5` – арифметическое среднее пяти аргументов, `1 2 3 4 5 avg5` равно 3;
        * `med3` – медиана трёх аргументов, `1 2 -10 med3` равно 1.


## Исходный код к лекциям по JavaScript

[Скрипт с примерами](javascript/examples.js)

Запуск примеров
* [В браузере](javascript/RunJS.html)
* Из консоли
    * [на Java](javascript/RunJS.java): [RunJS.cmd](javascript/RunJS.cmd), [RunJS.sh](javascript/RunJS.sh)
    * [на node.js](javascript/RunJS.node.js): `node RunJS.node.js`

Лекция 1. Типы и функции
* [Типы](javascript/examples/1_1_types.js)
* [Функции](javascript/examples/1_2_functions.js)
* [Функции высшего порядка](javascript/examples/1_3_functions-hi.js).
  Обратите внимание на реализацию функции `mCurry`.
  Обратите внимание, что функции `array.map` и
  `array.reduce` (аналог `leftFold` входят в стандартную библиотеку).
* [Пример: вектора и матрицы](javascript/examples/1_4_vectors.js).

Лекция 2. Объекты и замыкания
* [Поля](javascript/examples/2_1_fields.js)
* [Методы](javascript/examples/2_2_methods.js)
* [Замыкания](javascript/examples/2_3_closures.js)
* [Модули](javascript/examples/2_4_modules.js)
* [Пример: стеки](javascript/examples/2_5_stacks.js)

Лекция 3. Другие возможности
* [Обработка ошибок](javascript/examples/3_1_errors.js)
* [Чего нет в JS](javascript/examples/3_2_no.js)
* [Стандартная библиотека](javascript/examples/3_3_builtins.js)
* [Работа со свойствами](javascript/examples/3_4_properties.js)
* [Методы и классы](javascript/examples/3_5_classes.js)
* [JS 6+](javascript/examples/3_6_js6.js)
* Модули:
  [объявление](javascript/examples/3_7_js6_module.mjs)
  [использование](javascript/examples/3_7_js6_module_usage.mjs)
* [Простейший ввод-вывод](javascript/examples/3_8_io.js)


## Тестовое задание на JavaScript

Это задание преднозначено для проверки правильности настройки
[JavaScript](https://ecma-international.org/publications-and-standards/standards/ecma-262/).
Вам надо проверить, что оно успешно проверяется на вашем компьютере.

Модификации
* *base*
    * Код решения `java-solutions/example.js` в
      [репозитории решений](https://geo@www.kgeorgiy.info/git/geo/paradigms-2024-solutions/).
      Если всё настроено верно, то вам достаточно сделать `git pull --rebase` в своём репозитории,
      чтобы получить решение.
    * [Исходный код тестов](javascript/jstest/example/ExampleTest.java)
        * Запускать c аргументом `hard` или `easy`.

Запуск тестов
* Для запуска тестов используется [GraalJS](https://github.com/graalvm/graaljs)
  (часть проекта [GraalVM](https://www.graalvm.org/)), но вам не требуется их скачивать.
* Для запуска тестов рекомендуется использовать скрипты
  [TestJS.cmd](javascript/TestJS.cmd) и [TestJS.sh](javascript/TestJS.sh)
    * Репозиторий должен быть скачан целиком.
    * Скрипты должны находиться в каталоге `javascript` (их нельзя перемещать, но можно вызывать из других каталогов).
    * В качестве аргументов командной строки указывается полное имя класса теста и модификация,
      например `jstest.example.ExampleTest hard base`.
* Для самостоятельно запуска из консоли необходимо использовать командную строку вида:
  `java -ea --module-path=<js>/graal --class-path <js> jstest.example.ExampleTest {hard|easy} <variant>`, где
    * `-ea` – включение проверок времени исполнения;
    * `--module-path=<js>/graal` путь к модулям Graal (здесь и далее `<js>` путь к каталогу `javascript` этого репозитория);
    * `--class-path <js>` путь к откомпилированным тестам;
    * {`hard`|`easy`} указание тестируемой сложности;
    * `<variant>`} указание тестируемой модификации.
* При запуске из IDE, обычно не требуется указывать `--class-path`, так как он формируется автоматически.
  Остальные опции все равно необходимо указать.
* Troubleshooting
    * `Error occurred during initialization of boot layer java.lang.module.FindException: Module org.graalvm.truffle not found, required by jdk.internal.vm.compiler`
      – неверно указан `--module-path`;
    * `Graal.js not found` – неверно указаны `--module-path`
    * `Error: Could not find or load main class jstest.example.ExampleTest`
      – неверно указан `--class-path`;
    * `Exception in thread "main" java.lang.AssertionError: You should enable assertions by running 'java -ea jstest.functional.FunctionalExpressionTest'`
      – не указана опция `-ea`;
    * `Exception in thread "main" jstest.EngineException: Script 'example.js' not found`
      – в текущем каталоге отсутствует решение (`example.js`)


## Домашнее задание 4. Очереди

Решение:
    []

Модификации
* *Базовая*
    * [Исходный код тестов](java/queue/QueueTest.java)
    * [Откомпилированные тесты](artifacts/queue/QueueTest.jar)
    * Для работы тестов необходимо добавить опцию JVM `--add-opens java.base/java.util=ALL-UNNAMED`
* *Dedup* (32, 33)
    * Реализовать метод `dedup`, удаляющией из очереди подряд идущие повторяющиеся элементы.
    * Порядок элементов должен сохраняться.
* *Distinct* (34, 35)
    * Реализовать метод `distinct`, удаляющией из очереди повторяющиеся элементы.
    * Порядок элементов должен сохраняться.
* *FlatMap* (36-39)
    * Добавить в интерфейс очереди и реализовать метод
      `flatMap(function)` – создать очередь, содержащую результаты применения
      [функции](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/Function.html)
      из элемента в список элементов
    * Исходная очередь должна остаться неизменной
    * Тип возвращаемой очереди должен соответствовать типу исходной очереди
    * Взаимный порядок элементов должен сохраняться
    * Дублирования кода быть не должно
* *Reduce* (38-39)
    * Добавить в интерфейс очереди и реализовать метод
      `reduce(init, op)` – вернуть значение, полученное последовательным применением
      [бинарного оператора](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/BinaryOperator.html)
      к `init` и элементам очереди в порядке от головы к хвосту
    * Исходная очередь должна остаться неизменной
    * Дублирования кода быть не должно


## Домашнее задание 3. Очередь на массиве

Модификации
* *Базовая*
    * Классы должны находиться в пакете `queue`
    * [Исходный код тестов](java/queue/ArrayQueueTest.java)
    * [Откомпилированные тесты](artifacts/queue/ArrayQueueTest.jar)
* *Deque*
    * Дополнительно реализовать методы
        * `push` – добавить элемент в начало очереди;
        * `peek` – вернуть последний элемент в очереди;
        * `remove` – вернуть и удалить последний элемент из очереди.
* *CountIf* (32, 33)
    * Реализовать метод `countIf`, возвращающий число элементов очереди, удовлетворяющих
      [предикату](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/Predicate.html).
* *IndexIf* (34, 35)
    * Реализовать метод
        * `indexIf`, возвращающий индекс первого элемента, удовлетворяющего
          [предикату](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/Predicate.html);
        * `lastIndexIf`, возвращающий индекс последнего элемента, удовлетворяющего
          [предикату](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/Predicate.html).
    * Индексы отсчитываются с головы очереди.
    * Если искомого элемента нет, методы должны возвращать `-1`.
* *DequeCountIf* (36, 37)
    * Реализовать модификацию *Deque*;
    * Реализовать метод `countIf`, возвращающий число элементов очереди, удовлетворяющих
      [предикату](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/Predicate.html).
* *DequeIndexIf* (38, 39)
    * Реализовать модификацию *Deque*;
    * Реализовать метод
        * `indexIf`, возвращающий индекс первого элемента, удовлетворяющего
          [предикату](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/Predicate.html);
        * `lastIndexIf`, возвращающий индекс последнего элемента, удовлетворяющего
          [предикату](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/function/Predicate.html).
    * Индексы отсчитываются с головы очереди.
    * Если искомого элемента нет, методы должны возвращать `-1`.

Если при тестировании вы получаете ошибку
`... module java.base does not "opens java.util" to unnamed module ...`
(характерно для Java 17+), то при запуске тестов добавьте опции
`--add-opens` и `java.base/java.util=ALL-UNNAMED`.


## Домашнее задание 2. Бинарный поиск

Решение:
    [binarySearch](java-solutions/search/BinarySearch.java)
    [binarySearchMin](java-solutions/search/BinarySearchMin.java)

Модификации
* *Базовая*
    * Класс `BinarySearch` должен находиться в пакете `search`
    * [Исходный код тестов](java/search/BinarySearchTest.java)
    * [Откомпилированные тесты](artifacts/search/BinarySearchTest.jar)
* *Choice* (32 - 35)
    * Если сумма всех чисел во входе чётная, то должна быть использована
      рекурсивная версия, иначе — итеративная.
* *ClosestD* (32, 33)
    * На вход подаётся число `x` и непустой массив, отсортированный по невозрастанию.
    * Требуется вывести значение элемента массива наименее отличающегося от `x`.
    * Класс должен иметь имя `BinarySearchClosestD`
* *ClosestA* (34, 35)
    * На вход подаётся число `x` и непустой массив, отсортированный по неубыванию.
    * Требуется вывести значение элемента массива наименее отличающегося от `x`.
    * Класс должен иметь имя `BinarySearchClosestA`
* *ClosestI* (36, 37)
    * На вход подаётся число `x` и непустой массив, отсортированный по неубыванию.
    * Требуется вывести минимальный индекс элемента массива наименее отличающегося от `x`.
    * Требуется вывести минимальный индекс элемента массива наименее отличающегося от `x`.
    * Класс должен иметь имя `BinarySearchClosestI`
* *Shift* (38, 39)
    * На вход подается число `x` и массив `a`,
      полученный циклическим сдвигом отсортированного (строго) по возрастанию массива.
    * Требуется вывести индекс первого вхождения `x` в `a` или `-1`, если `x` не входит в `a`.
    * Класс должен иметь имя `BinarySearchShift`


Для того, чтобы протестировать базовую модификацию домашнего задания:

1. Скачайте тесты ([BinarySearchTest.jar](artifacts/search/BinarySearchTest.jar))
1. Откомпилируйте `BinarySearch.java`
1. Проверьте, что создался `BinarySearch.class`
1. В каталоге, в котором находится `search/BinarySearch.class` выполните команду

   ```
      java -jar <путь к BinarySearchTest.jar> Base
   ```

   Например, если `BinarySearchTest.jar` находится в текущем каталоге,
   а `BinarySearch.class` в каталоге `search`, выполните команду

   ```
       java -jar BinarySearchTest.jar Base
   ```

