package src.main.java;

public class Menu {
	
    public static void printMenu() {
        System.out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃            Меню программы             ┃
                ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
                ┃ 1. Добавление нового двигателя        ┃
                ┃ 2. Удаление двигателя                 ┃
                ┃ 3. Вывод всех двигателей              ┃
                ┃ 4. Сравнение двигателей (по индексу)  ┃
                ┃ 5. Завершение работы программы        ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }

    public static void printMenuChoiceType() {
        System.out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃        Добавление двигателя         ┃
                ┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫
                ┃ 1. Добавление двигателя             ┃
                ┃ 2. Добавление ДВС                   ┃
                ┃ 3. Добавление дизельного двигателя  ┃
                ┃ 4. Добавление реактивного двигателя ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }


    public static void printMenuChoiceConstructor() {
        System.out.println("""
                ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                ┃ 1. Добавление без параметров ┃
                ┃ 2. Добавление с параметрами  ┃
                ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
    }

    public static void printMessage(int choice) {
        if (choice == 1) {
            System.out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃      Двигатели ещё не добавлены      ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

        if (choice == 2) {
            System.out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃  Двигатель добавлен  ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━┛""");
        }

        if (choice == 3) {
            System.out.println("""
                    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
                    ┃  Такого пункта нет в меню.  ┃
                    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛""");
        }
    }


}
