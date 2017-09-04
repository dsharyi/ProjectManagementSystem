package com.sharyi_dmytro.practice.module02.Console;

import com.sharyi_dmytro.practice.module02.APIs.API;
import com.sharyi_dmytro.practice.module02.Entities.Company;
import com.sharyi_dmytro.practice.module02.Entities.Customer;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by nonal on 31.07.2017.
 */
public class ConsoleProject {

        private BufferedReader br;
        private API api;

        public ConsoleProject(BufferedReader br, API api) {
            this.br = br;
            this.api = api;
        }

        public void chooseOperationWithProject() {
            while (true) {
                System.out.println("1. Создание проекта.\n" +
                        "2. Просмотр данных проекта.\n" +
                        "3. Редактирование данных проекта.\n" +
                        "4. Удаление проекта.\n" +
                        "0. Возвращение в главное меню.");

                try {
                    switch (Integer.parseInt(br.readLine())) {
                        case 1:
                            createProject();
                            break;
                        case 2:
                            readproject();
                            break;
                        case 3:
                            updateProject();
                            break;
                        case 4:
                            deleteProject();
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

        private void createProject() {

            System.out.println("Создание проекта");
            String name;
            int cost;
            int idCompany;
            int idCustomer;
            while (true) {
                try {
                    System.out.println("\nВведите название проекта: ");
                    name = br.readLine();

                    System.out.println("Введите стоимость проекта: ");
                    cost = Integer.parseInt(br.readLine());

                    System.out.println("Список доступных компаний");
                    api.showAllCompanies();
                    System.out.println("Введите ID компании:");
                    idCompany = Integer.parseInt(br.readLine());

                    System.out.println("Список заказчиков");
                    api.showAllCustomers();
                    System.out.println("Введите ID заказчика: ");
                    idCustomer = Integer.parseInt(br.readLine());

                    Company company = api.readCompany(idCompany);
                    Customer customer = api.readCustomer(idCustomer);

                    api.createProject(name, cost, company, customer);

                    System.out.println("Проект успешно добавлен в базу даных!");

                    return;

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WrongId wrongId) {
                    System.out.println(wrongId.getMessage());
                }
            }

        }

        private void readproject() {

            System.out.println("Просмотр проекта по ID");
            int id;

            while (true) {
                try {
                    api.showAllProjects();
                    System.out.println("\nВведите ID проекта: ");

                    id = Integer.parseInt(br.readLine());

                    System.out.println(api.readProject(id));
                    return;
                } catch (WrongId wrongId) {
                    System.out.println(wrongId.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        private void updateProject() {

            System.out.println("Изменение данных проекта");
            String name;
            int id;
            int cost;
            while (true) {
                try {
                    api.showAllProjects();
                    System.out.println("\nВведите ID проекта данные которого вы хотите изменить: ");
                    id = Integer.parseInt(br.readLine());

                    System.out.println("Введите новое название проекта: ");
                    name = br.readLine();

                    System.out.println("Введите новую стоимость проекта: ");
                    cost = Integer.parseInt(br.readLine());

                    api.updateProject(id, name, cost);
                    System.out.println("Изменения успешно сохранены!");

                    return;

                } catch (WrongId wrongId) {
                    System.out.println(wrongId.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void deleteProject() {

            System.out.println("Удаление проекта по ID");
            int id;

            while (true) {
                try {
                    api.showAllProjects();
                    System.out.println("\nВведите ID проекта: ");
                    id = Integer.parseInt(br.readLine());

                    api.deleteProject(id);

                    System.out.println("Проект с ID успешно удалён! ");
                    return;
                } catch (WrongId wrongId) {
                    System.out.println(wrongId.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

