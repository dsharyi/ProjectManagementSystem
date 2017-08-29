package com.sharyi_dmytro.practice.module02.Console;


import java.io.BufferedReader;
import java.io.IOException;


public class ConsoleHelper {

    private BufferedReader br;
    private ConsoleDeveloper consoleDeveloper;
    private ConsoleSkill consoleSkill;
    private ConsoleCompany consoleCompany;
    private ConsoleCustomer consoleCustomer;
    private ConsoleProject consoleProject;

    public ConsoleHelper(BufferedReader br, ConsoleDeveloper consoleDeveloper, ConsoleSkill consoleSkill, ConsoleCompany consoleCompany, ConsoleCustomer consoleCustomer, ConsoleProject consoleProject) {
        this.br = br;
        this.consoleDeveloper = consoleDeveloper;
        this.consoleSkill = consoleSkill;
        this.consoleCompany = consoleCompany;
        this.consoleCustomer = consoleCustomer;
        this.consoleProject = consoleProject;
    }

    public void chooseOperation() {
        while (true) {

            System.out.println("\t\tГлавное меню.\n\n" +
                    "1. Навыки.\n" +
                    "2. Разработчики.\n" +
                    "3. Заказчики.\n" +
                    "4. Компании.\n" +
                    "5. Проекты.\n" +
                    "0. Выход.\n\n" +

                    "Выберите сущность, над которой хотите произвести операции!");
            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        System.out.println("Навыки\n");
                        consoleSkill.chooseOperationWithSkill();
                        break;
                    case 2:
                        System.out.println("Разработчики\n");
                        consoleDeveloper.chooseOperationWithDeveloper();
                        break;
                    case 3:
                        System.out.println("Заказчики\n");
                        consoleCustomer.chooseOperationWithCustomer();
                        break;
                    case 4:
                        System.out.println("Компании\n");
                        consoleCompany.chooseOperationWithCompany();
                        break;
                    case 5:
                        System.out.println("Проекты\n");
                        consoleProject.chooseOperationWithProject();
                        break;
                    case 0:
                        System.out.println("Выход");
                        return;

                    default:
                        System.out.println("Неверный номер операции, повторите ввод!");
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException n) {
                System.out.println("Неверный номер операции, повторите ввод!");
            }
        }


    }
}




