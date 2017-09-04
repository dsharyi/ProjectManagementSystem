package com.sharyi_dmytro.practice.module02.Console;

import com.sharyi_dmytro.practice.module02.APIs.API;
import com.sharyi_dmytro.practice.module02.Entities.Project;
import com.sharyi_dmytro.practice.module02.Entities.Skill;
import com.sharyi_dmytro.practice.module02.Exceptions.IncorrectName;
import com.sharyi_dmytro.practice.module02.Exceptions.SkillNull;
import com.sharyi_dmytro.practice.module02.Exceptions.WrongId;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by nonal on 31.07.2017.
 */
public class ConsoleDeveloper {

    private BufferedReader br;
    private API api;

    public ConsoleDeveloper(BufferedReader br, API api) {
        this.br = br;
        this.api = api;
    }

    public void chooseOperationWithDeveloper() {
        while (true) {
            System.out.println("1. Создание разработчика.\n" +
                    "2. Просмотр данных разработчика.\n" +
                    "3. Редактирование данных разработчика.\n" +
                    "4. Удаление разработчика.\n" +
                    "0. Возвращение в главное меню.");

            try {
                switch (Integer.parseInt(br.readLine())) {
                    case 1:
                        createDeveloper();
                        break;
                    case 2:
                        readDeveloper();
                        break;
                    case 3:
                        updateDeveloper();
                        break;
                    case 4:
                        deleteDeveloper();
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

    private void createDeveloper() {
        while (true) {
            System.out.println("Создать разработчика\n");

            String name;
            String secondName;
            int salary;
            String projectName;
            String skill;
            List<Skill> skills;

            try {
                System.out.println("Введите имя разработчика: ");
                name = br.readLine();

                System.out.println("Введите фамилию разработчика: ");
                secondName = br.readLine();

                System.out.println("Введите размер зарплаты разработчика: ");
                salary = Integer.parseInt(br.readLine());

                System.out.println("Введите название проекта над которым будет работать разработчик: ");
                System.out.println("Доступные проекты:");
                api.showAllProjects();
                projectName = br.readLine();
                Project proj = api.findProjectByName(projectName);

                System.out.println("Введите список навыков для разработчика! Для окончания ввода введите \"0\"!");
                System.out.println("Доступные навыки:");
                api.showAllSkills();
                skills = new ArrayList<>();

                while (!Objects.equals(skill = br.readLine(), "0")) {
                    Skill skillByName = api.findSkillByName(skill);

                    skills.add(skillByName);
                }

                api.createDeveloper(name, secondName, salary, proj, skills);
                System.out.println("Разработчик успешно добавлен в базу данных!");
                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (SkillNull skillNull) {
                System.out.println(skillNull.getMessage());
            }
            catch (IncorrectName i){
                System.out.println(i.getMessage());
            }
        }
    }

    private void readDeveloper() {

        System.out.println("Просмотр разработчика по ID");
        while (true) {
            int id;

            try {
                api.showAllDevelopers();
                System.out.println("\nВведите ID разработчика");
                id = Integer.parseInt(br.readLine());

                System.out.println(api.readDeveloper(id));

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }
    }


    private void updateDeveloper() {
        while (true) {

            System.out.println("Редактирование данных разработчика");

            int id;
            String newName;
            String newSecondName;
            int newSalary;
            String newskill;
            List<Skill> newSkills;
            Project project;

            try {
                api.showAllDevelopers();
                System.out.println("Введите ID разработчика данные которого вы хотите изменить:");
                id = Integer.parseInt(br.readLine());
                System.out.println(api.readDeveloper(id));

                System.out.println("Введите новое имя: ");
                newName = br.readLine();

                System.out.println("Введите новую фамилию: ");
                newSecondName = br.readLine();

                System.out.println("Введите новый размер зарплаты: ");
                newSalary = Integer.parseInt(br.readLine());

                api.showAllSkills();

                System.out.println("Введите новый список навыков для разработчика! Для окончания ввода введите \"0\"!");
                newSkills = new ArrayList<>();

                while (!Objects.equals(newskill = br.readLine(), "0")) {
                    Skill skillByName = api.findSkillByName(newskill);

                    newSkills.add(skillByName);
                }

                api.showAllProjects();
                System.out.println("\nВведите название проекта над которым будет работать разработчик: ");
                project = api.findProjectByName(br.readLine());

                api.updateDeveloper(id, newName, newSecondName, newSalary,project, newSkills);
                System.out.println("Изменения произведены успешно!");
                api.readDeveloper(id);
                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
            catch (IncorrectName incorrectName){
                System.out.println(incorrectName.getMessage());
            }

        }
    }

    private void deleteDeveloper() {


        System.out.println("Удаление разработчика по ID");
        while (true) {

            int id;

            try {
                api.showAllDevelopers();
                System.out.println("\nВведите ID разработчика");
                id = Integer.parseInt(br.readLine());

                api.deleteDeveloper(id);
                System.out.println("Разработчик с ID " + id + " успешно удалён!");

                return;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (WrongId wrongId) {
                System.out.println(wrongId.getMessage());
            }
        }

    }


}

