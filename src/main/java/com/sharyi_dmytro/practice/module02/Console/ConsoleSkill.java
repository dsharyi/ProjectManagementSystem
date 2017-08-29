package com.sharyi_dmytro.practice.module02.Console;

import com.sharyi_dmytro.practice.module02.APIs.API;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by nonal on 31.07.2017.
 */
public class ConsoleSkill {
    private BufferedReader br;
    private API api;

    public ConsoleSkill(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithSkill() {
        while (true) {
            System.out.println("1. Создание навыка.\n" +
                    "2. Просмотр данных навыка.\n" +
                    "3. Редактирование данных навыка.\n" +
                    "4. Удаление навыка.\n" +
                    "0. Возвращение в главное меню.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createSkill();
                        break;
                    case 2:
                        readSkill();
                        break;
                    case 3:
                        updateSkill();
                        break;
                    case 4:
                        deleteSkill();
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


    private void createSkill() {
        while (true) {
            System.out.println("Добавление навыка");
            try {
                String name;

                System.out.println("\nВведите название нового навыка: ");
                name = br.readLine();

                api.createSkill(name);
                System.out.println("\nНавык " + name + " успешно добавлен в БД!");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void readSkill()  {

        System.out.println("Просмотр навыков:");
        System.out.println("-----------------------------");
        api.showAllSkills();
        System.out.println("-----------------------------");
        }


    private void updateSkill() {

        System.out.println("Редактирование данных навыков");
        int id;
        String newName;
        api.showAllSkills();


        while (true) {
            try {
                System.out.println("\nВведите ID навыка: ");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readSkill(id));
                System.out.println("\nВведите новое название навыка: ");
                newName = br.readLine();

                api.updateSkill(id, newName);
                System.out.println("Изменения сохранены успешно!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }

    private void deleteSkill() {

        System.out.println("Удаление навыка по ID");
        int id;
        api.showAllSkills();

        while (true) {
            try {
                System.out.println("\nВведите ID навыка: ");
                id = Integer.parseInt(br.readLine());

                api.deleteSkill(id);
                System.out.println("Навык с ID " + id + " успешно удалён");
                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }
}

