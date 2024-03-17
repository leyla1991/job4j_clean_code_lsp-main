package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {

    private static final String MENU = """
             Выберите действие:
            1. Добавить корень меню
            2. Добавить подпункт
            3. Действие
            4. Вывести меню
            Для завершения введите любое другое число""";
    private static final ActionDelegate DEFAULT_DELEGATE = System.out :: println;
    private Menu menu;
    private Scanner scanner;

    public TodoApp(Menu menu, Scanner scanner) {
        this.menu = menu;
        this.scanner = scanner;
    }

    public static void main(String[] args) {
        TodoApp todoApp = new TodoApp(new SimpleMenu(), new Scanner(System.in));
        Printer printer = new Printer();
        boolean rsl = true;
        while (rsl) {
            System.out.println(MENU);
            int number = Integer.parseInt(todoApp.scanner.nextLine());
            if (number == 1) {
                System.out.println("Придумайте название:");
                String name = todoApp.scanner.nextLine();
                todoApp.menu.add(Menu.ROOT, name, todoApp.DEFAULT_DELEGATE);
            } else if (number == 2) {
                System.out.println("Выберите пункт к которому хотите добавить подпункт:");
                String nameParent = todoApp.scanner.nextLine();
                System.out.println("Назовите подпункт:");
                String nameChild = todoApp.scanner.nextLine();
                todoApp.menu.add(nameParent, nameChild, todoApp.DEFAULT_DELEGATE);
            } else if (number == 3) {
                System.out.println("Выберите название пункта, который хотите выполнить");
                String name = todoApp.scanner.nextLine();
                Optional<Menu.MenuItemInfo> optional = todoApp.menu.select(name);
                optional.get().getActionDelegate().delegate();
            } else if (number == 4) {
                System.out.println("Меню:");
                printer.print(todoApp.menu);
            } else {
                rsl = false;
            }
        }
    }
}
