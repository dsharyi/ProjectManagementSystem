package com.sharyi_dmytro.practice.module02.Console;

import com.sharyi_dmytro.practice.module02.APIs.API;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by nonal on 31.07.2017.
 */
public class ConsoleCustomer {

    private BufferedReader br;
    private API api;

    public ConsoleCustomer(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithCustomer() {
        while (true) {
            System.out.println("1. Создание заказчика.\n" +
                    "2. Просмотр данных заказчика.\n" +
                    "3. Редактирование данных заказчика.\n" +
                    "4. Удаление заказчика.\n" +
                    "0. Возвращение в главное меню.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        readCustomer();
                        break;
                    case 3:
                        updateCustomer();
                        break;
                    case 4:
                        deleteCustomer();
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

    private void createCustomer() {

        System.out.println("Создание заказчика");
        String name;

        try {
            System.out.println("\nВведите название заказчика: ");
            name = br.readLine();

            api.createCustomer(name);

            System.out.println("Заказчик " + name + " "  + " успешно добавлен!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readCustomer() {

        System.out.println("Просмотр заказчиков:");
        int id;

        while (true) {

            try {
                api.showAllCustomers();
                System.out.println("\nВведите ID заказчика: ");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readCustomer(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (WrongId wrongId){
                wrongId.getMessage();
            }
        }

    }

    private void updateCustomer() {

        System.out.println("Изменение данных заказчика");
        int id;
        String name;
        while (true) {
            try {
                api.showAllCustomers();
                System.out.println("\nВведите ID заказчика данные которого вы хотите изменить: ");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readCustomer(id));

                System.out.println("\nВведите новое название заказчика: ");
                name = br.readLine();

                api.updateCustomer(id, name);

                System.out.println("Изменения ушпешно сохранены");
                System.out.println(api.readCustomer(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            }

            catch (WrongId wrongId){
                wrongId.getMessage();
            }
        }
    }

    private void deleteCustomer() {

        System.out.println("Удаление заказчика по ID");
        int id;

        while (true) {

            try {
                api.showAllCustomers();
                System.out.println("\nВведите ID заказчика: ");
                id = Integer.parseInt(br.readLine());

                api.deleteCustomer(id);
                System.out.println("Заказчик с ID = " + id + " успешно удалён!");

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

