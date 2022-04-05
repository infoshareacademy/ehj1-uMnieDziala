package com.isa.unasdziala.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    boolean exit;

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }

    public void runMenu() {
        printHeader();
        while (!exit) {
            printMenu(List < String > list);                                // ToDo
            int choice = getInput();
            performAction(choice);
        }
    }

    private void printHeader() {
        System.out.println("+---------------------------------------+");
        System.out.println("|              Welcome to               |");
        System.out.println("|     Employee Leave Management App     |");
        System.out.println("+---------------------------------------+");
    }

    private void printMenu(List<String> list) {
        List<String> menuList = new ArrayList<>();
        menuList.add("Please make a selection:");
        menuList.add("(1) Search for employee by id.");
        menuList.add("(2) Edit employee data.");
        menuList.add("(0) Exit.");
        menuList.stream()
                .toList()
                .forEach(System.out::println);
    }

    private int getInput() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > 3) {                                      //ToDo list
            try {
                System.out.println("Enter your choice:");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Please try again.");
            }
        }
        return choice;
    }

    private void performAction(int choice) {
        switch (choice) {
            case 0:
                exit = true;
                System.out.println("Thank you for using our application");
                break;
            case 1:
        }
    }
}
