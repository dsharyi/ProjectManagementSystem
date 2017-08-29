package com.sharyi_dmytro.practice.module02.Console;

import com.sharyi_dmytro.practice.module02.APIs.API;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by nonal on 31.07.2017.
 */
public class ConsoleCompany {


    private BufferedReader br;
    private API api;

    public ConsoleCompany(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithCompany() {
        while (true) {
            System.out.println("1. Создание компании.\n" +
                    "2. Просмотр данных компании.\n" +
                    "3. Редактирование данных компании.\n" +
                    "4. Удаление компании.\n" +
                    "0. Возвращение в главное меню.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createCompany();
                        break;
                    case 2:
                        readCompany();
                        break;
                    case 3:
                        updateCompany();
                        break;
                    case 4:
                        deleteCompany();
                        break;
                    case 0:
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

    private void createCompany() {
        System.out.println("Создание компании");
        String name;
        try {
            System.out.println("\nВведите название компании:");
            name = br.readLine();
            api.createCompany(name);
            System.out.println("Компания была успешно добавлена!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readCompany() {
        while (true) {
            System.out.println("Просмотр компании по ID");
            int id;

            try {
                api.showAllCompanies();
                System.out.println("\nВведите ID омпании: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readCompany(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (WrongId wrongId){
                wrongId.getMessage();
            }
        }


    }

    private void updateCompany() {

        System.out.println("Изменение данных компании");
        int id;
        String name;
        while (true) {
            try {
                api.showAllCompanies();
                System.out.println("\nВведите ID компании данные которой вы хотите изменить: ");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readCompany(id));
                System.out.println("Введите новое название компании:");
                name = br.readLine();

                api.updateCompany(id, name);
                System.out.println("Изменения ушпешно сохранены");
                System.out.println(api.readCompany(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (WrongId wrongId){
                wrongId.getMessage();
            }
        }


    }

    private void deleteCompany() {
        while (true) {
            System.out.println("Удаление компании по ID");
            int id;

            try {
                api.showAllCompanies();
                System.out.println("\nВведите ID компании: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.deleteCompany(id));
                System.out.println("Компания с ID = " + id + " успешно удалена!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (WrongId wrongId){
                wrongId.getMessage();
            }
        }

    }


}

