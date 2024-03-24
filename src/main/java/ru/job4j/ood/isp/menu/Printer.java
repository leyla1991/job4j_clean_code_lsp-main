package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo m : menu) {
            System.out.println("-".repeat(m.getNumber().length())
                    + m.getNumber()
                    + m.getName());
        }
    }
}
