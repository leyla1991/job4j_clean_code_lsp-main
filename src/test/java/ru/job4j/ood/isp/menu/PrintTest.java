package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PrintTest {
    @Test
    void whenPrint() {
        ActionDelegate delegate = System.out::println;
        PrintStream pr = System.out;
        ByteArrayOutputStream by = new ByteArrayOutputStream();
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Первые блюда", delegate);
        menu.add(Menu.ROOT, "Вторые блюда", delegate);
        menu.add(Menu.ROOT, "Десерт", delegate);
        menu.add("Первые блюда", "Крем-суп", delegate);
        menu.add("Вторые блюда", "Картошка фри", delegate);
        menu.add("Десерт", "Чизкейк", delegate);
        Printer printer = new Printer();
        System.setOut(new PrintStream(by));
        printer.print(menu);
        String ls = System.lineSeparator();
        String ex = "--1.Первые блюда" + ls
                + "----1.1.Крем-суп" + ls
                + "--2.Вторые блюда" + ls
                + "----2.1.Картошка фри" + ls
                + "--3.Десерт" + ls
                + "----3.1.Чизкейк";
        System.setOut(pr);
        assertThat(by.toString().trim()).isEqualTo(ex);
    }
}