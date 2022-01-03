package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {

    private Scanner scanner = new Scanner(System.in);


    public String askStr(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }


    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }
}
