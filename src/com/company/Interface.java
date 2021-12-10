package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Interface {
    private boolean checkButton;
    JFrame jFrame = getFrame("Student Manager");
    Brain brain = new Brain();

    public void mainMenu(){
        jFrame.add(menu());
        jFrame.revalidate();
    }

    public JPanel menu(){
        JPanel jPanel1 = new JPanel();
        JLabel label = new JLabel("ГЛАВНОЕ МЕНЮ");
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        label.setFont(font);
        label.setBounds(30, 40, 40, 10);
        jPanel1.add(label);
        MyLayout layout1 = new MyLayout();
        jPanel1.setLayout(layout1);
        JButton[] myButtons = mainButtons();
        for(int i = 0; i < 9; i++)
            jPanel1.add(myButtons[i]);
        for(int i = 0; i < 9; i++) {
            int finalI = i;
            myButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(finalI != 3 && finalI != 8)
                        jFrame.remove(jPanel1);
                    switch (finalI){
                        case 0:
                            int position = brain.listOfStudent.size();
                            System.out.println(position);
                            brain.showAllInform(brain.listOfStudent);
                            jFrame.add(onlyEnter(position));
                            break;
                        case 1:
                            if(brain.checkFile("file.txt"))
                                jFrame.add(dataOnList(brain.createCorrectFile("file.txt"), "Список из файла"));
                            else {
                                JOptionPane.showMessageDialog(menu(), "Не найден файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                                jFrame.remove(menu());
                                jFrame.add(menu());
                            }
                            break;
                        case 2:
                            jFrame.add(dataOnList(brain.listOfStudent, "Список студентов"));
                            break;
                        case 3:
                            UIManager.put("OptionPane.yesButtonText", "Дa, продолжить");
                            UIManager.put("OptionPane.noButtonText", "Нет, не добавлять");
                            int result = JOptionPane.showConfirmDialog(jPanel1, "Напоминаем, что при обнаружении" +
                                    "\nсовпадения номеров студенческих билетов\nбудет оставлена ТОЛЬКО копия,\nкоторая записана в исходном списке.\n" +
                                            "Вы хотите продолжить?",
                                    "Информация", JOptionPane.YES_NO_OPTION);
                            if(result == JOptionPane.OK_OPTION) {
                                checkButton = true;
                                if (brain.checkFile("file.txt")) {
                                    JOptionPane.showMessageDialog(jPanel1, "Данные добавлены в список!", "Добавление", JOptionPane.INFORMATION_MESSAGE);
                                    brain.addFile("file.txt");
                                } else {
                                    JOptionPane.showMessageDialog(menu(), "Не найден файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            break;
                        case 4:
                            jFrame.add(searchMenu());
                            break;
                        case 5:
                            UIManager.put("OptionPane.yesButtonText", "Дa, продолжить");
                            UIManager.put("OptionPane.noButtonText", "Нет, не добавлять");
                            int result1 = JOptionPane.showConfirmDialog(jPanel1, "Напоминаем, что при обнаружении" +
                                            "\nсовпадения номеров студенческих билетов\nв файл будет записана ТОЛЬКО копия," +
                                            "\nкоторая находиться в исходном списке.\nИли списке поиска\n" +
                                            "Вы хотите продолжить?",
                                    "Информация", JOptionPane.YES_NO_OPTION);
                            if(result1 == JOptionPane.OK_OPTION)
                                jFrame.add(createFileMenu());
                            else {
                                jFrame.remove(jPanel1);
                                jFrame.add(jPanel1);
                                jFrame.revalidate();
                            }
                            break;
                        case 6:
                            jFrame.add(panelForEdit());
                            break;
                        case 7:
                            jFrame.add(panelForRemove());
                            break;
                        case 8:
                            UIManager.put("OptionPane.yesButtonText", "Выйти");
                            UIManager.put("OptionPane.noButtonText", "Отмена");
                            int result2 = JOptionPane.showConfirmDialog(jPanel1,
                                    "Вы хотите выйти?",
                                    "Выход",
                                    JOptionPane.YES_NO_OPTION);
                            if(result2 == JOptionPane.OK_OPTION)
                                System.exit(0);
                            else {
                                jFrame.remove(jPanel1);
                                jFrame.add(jPanel1);
                                jFrame.revalidate();
                            }
                            break;
                    }
                    if(finalI != 3 && finalI != 8)
                        jFrame.revalidate();
                }
            });
        }
        return jPanel1;
    }

    public JButton[] mainButtons(){
        JButton[] buttons = new JButton[9];
        buttons[0] = new JButton("Ввод новых данных");
        buttons[1] = new JButton("Просмотр файла");
        buttons[2] = new JButton("Просмотр списка");
        buttons[3] = new JButton("Добавление из файла");
        buttons[4] = new JButton("Поиск");
        buttons[5] = new JButton("Создание файлов");
        buttons[6] = new JButton("Редактирование");
        buttons[7] = new JButton("Удаление");
        buttons[8] = new JButton("Выход");
        return buttons;
    }

    public JPanel enterData(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel label = new JLabel(title);
        JLabel name = new JLabel("Имя:");
        JLabel surname = new JLabel("Фамилия:");
        JLabel ticket = new JLabel("Номер студенческого билета:");
        JLabel group = new JLabel("Номер группы:");
        JLabel label1 = new JLabel("Оценки:");
        JButton exit = new JButton("Назад");

        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        label.setFont(font);
        label.setSize(400, 20);
        label.setLocation(10, 0);
        surname.setBounds(10, 30, 100, 20);
        name.setBounds(10, 60, 50, 20);
        ticket.setBounds(10, 90, 200, 20);
        group.setBounds(10, 120, 200, 20);
        label1.setBounds(10, 150, 200, 20);
        exit.setBounds(460, 300, 100, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.remove(panel);
                jFrame.add(menu());
                jFrame.revalidate();
            }
        });
        panel.add(exit);
        panel.add(label);
        panel.add(name);
        panel.add(surname);
        panel.add(ticket);
        panel.add(group);
        panel.add(label1);
        panel.revalidate();
        return panel;
    }

    public JPanel onlyEnter(int position){
        UIManager.put("OptionPane.yesButtonText", "Дa");
        UIManager.put("OptionPane.noButtonText", "Нет");
        JPanel enterPanel = enterData("Ввод данных");
        JButton add = new JButton("Добавить");
        add.setBounds(350, 300, 100, 30);
        JTextField[] area = new JTextField[4];
        JComboBox<Integer>[] marks = new JComboBox[5];

        for(int i = 0; i < 4; i++){
            area[i] = new JTextField(null,100);
            area[i].setBounds(200, (i+1) * 30, 150, 20);
            JScrollPane jScrollPane = new JScrollPane(area[i]);
            jScrollPane.setSize(10, 10);
            enterPanel.add(area[i]);
        }
        for(int i = 0; i < 5; i++){
            marks[i] = new JComboBox<>();
            marks[i].addItem(2);
            marks[i].addItem(3);
            marks[i].addItem(4);
            marks[i].addItem(5);
            marks[i].setBounds(i * 60 + 10, 180, 40, 20);
            marks[i].setSelectedItem(2);
            enterPanel.add(marks[i]);
        }
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkSurname = brain.firstUpperCase(area[0].getText().trim());
                String checkName = brain.firstUpperCase(area[1].getText().trim());
                String checkTicket = area[2].getText();
                String checkGroup = area[3].getText();
                if(checkSurname.equals("")){
                    JOptionPane.showMessageDialog(enterPanel,
                            "Некорректная фамилия",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                } else if(checkName.equals("")){
                    JOptionPane.showMessageDialog(enterPanel,
                            "Некорректное имя",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                } else if(!brain.checkInt(checkTicket.trim())){
                    JOptionPane.showMessageDialog(enterPanel,
                            "Некоректный номер студенческого",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(!brain.checkInt(checkGroup.trim())){
                    JOptionPane.showMessageDialog(enterPanel,
                            "Некоректный номер группы",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(!brain.checkRepeatEnter(Integer.parseInt(checkTicket.trim()))){
                    JOptionPane.showMessageDialog(enterPanel,
                            "Студент с таким номером билета уже добавлен!\nПовторите ввод!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int result = JOptionPane.showConfirmDialog(enterPanel, "Добавить студента " + brain.firstUpperCase(area[0].getText().trim()) + " " +
                            brain.firstUpperCase(area[1].getText().trim()) + "?", "Добавление", JOptionPane.YES_NO_OPTION);
                    brain.listOfStudent.add(position, brain.setInform(area, marks));
                    if(result == JOptionPane.OK_OPTION) {
                        JOptionPane.showMessageDialog(enterPanel, "Данные сохранены в список!", "Сохранение", JOptionPane.INFORMATION_MESSAGE);
                        jFrame.remove(enterPanel);
                        jFrame.add(menu());
                        jFrame.revalidate();
                        return;
                    }
                    if(result == JOptionPane.NO_OPTION){
                        brain.listOfStudent.remove(position);
                        JOptionPane.showMessageDialog(enterPanel, "Данные не сохранены!", "Сохранение", JOptionPane.INFORMATION_MESSAGE);
                        jFrame.remove(enterPanel);
                        jFrame.add(menu());
                        jFrame.revalidate();
                    }
                }
            }
        });

        enterPanel.add(add);
        return enterPanel;
    }

    public JPanel edit(List<Student> list, String[] array, int[] tempMarks, int position){
        UIManager.put("OptionPane.yesButtonText", "Дa");
        UIManager.put("OptionPane.noButtonText", "Нет");
        JPanel editPanel = enterData("Редактирование данных");
        JTextField[] area = new JTextField[4];
        JComboBox<Integer>[] marks = new JComboBox[5];
        JButton edit = new JButton("Редактировать");

        edit.setBounds(250, 300, 200, 30);

        for(int i = 0; i < 4; i++){
            area[i] = new JTextField(array[i],100);
            area[i].setBounds(200, (i+1) * 30, 150, 20);
            JScrollPane jScrollPane = new JScrollPane(area[i]);
            jScrollPane.setSize(10, 10);
            editPanel.add(area[i]);
        }
        for(int i = 0; i < 5; i++){
            marks[i] = new JComboBox<>();
            marks[i].addItem(2);
            marks[i].addItem(3);
            marks[i].addItem(4);
            marks[i].addItem(5);
            marks[i].setBounds(i * 60 + 11, 180, 40, 20);
            marks[i].setSelectedItem(tempMarks[i]);
            editPanel.add(marks[i]);
        }
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkSurname = area[0].getText();
                String checkName = area[1].getText();
                String checkTicket = area[2].getText();
                String checkGroup = area[3].getText();
                if(checkSurname.equals("")){
                    JOptionPane.showMessageDialog(editPanel, "Некорректная фамилия", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else if(checkName.equals("")){
                    JOptionPane.showMessageDialog(editPanel,
                            "Некорректное имя",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                } else if(!brain.checkInt(checkTicket.trim())){
                    JOptionPane.showMessageDialog(editPanel,
                            "Некоректный номер студенческого",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(!brain.checkInt(checkGroup.trim())){
                    JOptionPane.showMessageDialog(editPanel,
                            "Некоректный номер группы",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(!brain.checkRepeatEdit(Integer.parseInt(checkTicket.trim()), position)){
                    JOptionPane.showMessageDialog(editPanel,
                            "Студент с таким номером билета уже добавлен!\nПовторите ввод!",
                            "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    Student tempStudent = list.get(position);
                    list.remove(position);
                    brain.showCurrentInform(tempStudent);
                    System.out.println();
                    brain.showAllInform(list);
                    int result = JOptionPane.showConfirmDialog(editPanel, "Добавить студента " + area[0].getText().trim() + " " +
                            area[1].getText().trim() + "?", "Добавление", JOptionPane.YES_NO_OPTION);
                    list.add(position, brain.setInform(area, marks));
                    brain.showAllInform(list);
                    if(result == JOptionPane.OK_OPTION) {
                        JOptionPane.showMessageDialog(editPanel, "Изменения сохранены!", "Сохранение", JOptionPane.INFORMATION_MESSAGE);
                        jFrame.remove(editPanel);
                        jFrame.add(menu());
                        jFrame.revalidate();
                        return;
                    }
                    if(result == JOptionPane.NO_OPTION){
                        list.add(position, tempStudent);
                        list.remove(position + 1);
                        brain.showAllInform(list);
                        JOptionPane.showMessageDialog(editPanel, "Изменения не сохранены!", "Сохранение", JOptionPane.INFORMATION_MESSAGE);
                        jFrame.remove(editPanel);
                        jFrame.add(menu());
                        jFrame.revalidate();
                    }
                }
            }
        });
        editPanel.add(edit);
        return editPanel;
    }


    public JPanel searchMenu(){
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(null);
        JLabel title = new JLabel("Меню поиска");
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        title.setFont(font);
        title.setBounds(10, 0, 200, 20);
        JButton[] searchButton = new JButton[11];
        for(int i = 0; i < 11; i++){
            switch (i) {
                case 0 -> {
                    searchButton[i] = new JButton("По номеру группы");
                    searchButton[i].setBounds(75, 25, 230, 30);
                }
                case 1 -> {
                    searchButton[i] = new JButton("По номеру института");
                    searchButton[i].setBounds(300, 25, 230, 30);
                }
                case 2 -> {
                    searchButton[i] = new JButton("По году поступления");
                    searchButton[i].setBounds(75, 25 + i * 30, 230, 30);
                }
                case 3 -> {
                    searchButton[i] = new JButton("Поиск по студенческому билету");
                    searchButton[i].setBounds(300, 25 + (i - 1) * 30, 230, 30);
                }
                case 4 -> {
                    searchButton[i] = new JButton("По фамилии");
                    searchButton[i].setBounds(75, 25 + i * 30, 230, 30);
                }
                case 5 -> {
                    searchButton[i] = new JButton("По имени");
                    searchButton[i].setBounds(300, 25 + (i - 1) * 30, 230, 30);
                }
                case 6 -> {
                    searchButton[i] = new JButton("Поиск отличников");
                    searchButton[i].setBounds(75, 25 + i * 30, 230, 30);
                }
                case 7 -> {
                    searchButton[i] = new JButton("Поиск хорошистов");
                    searchButton[i].setBounds(300, 25 + (i - 1) * 30, 230, 30);
                }
                case 8 -> {
                    searchButton[i] = new JButton("Поиск списка без стипендии");
                    searchButton[i].setBounds(75, 25 + i * 30, 230, 30);
                }
                case 9 -> {
                    searchButton[i] = new JButton("Поиск списка на отчисление");
                    searchButton[i].setBounds(300, 25 + (i - 1) * 30, 230, 30);
                }
                case 10 -> {
                    searchButton[i] = new JButton("Закончить поиск");
                    searchButton[i].setBounds(300, 320, 230, 30);
                }
            }
            searchPanel.add(searchButton[i]);
        }
        for(int i = 0; i < 11; i++) {
            int finalI = i;
            searchButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.remove(searchPanel);
                    switch (finalI) {
                        case 0 -> jFrame.add(panelForSearchInt("Поиск по группе"));
                        case 1 -> jFrame.add(panelForSearchInt("Поиск по институту"));
                        case 2 -> jFrame.add(panelForSearchInt("Поиск по году"));
                        case 3 -> jFrame.add(panelForSearchInt("Поиск по студенческому билету"));
                        case 4 -> jFrame.add(panelForSearchInt("Поиск по фамилии"));
                        case 5 -> jFrame.add(panelForSearchInt("Поиск по имени"));
                        case 6 -> jFrame.add(panelForSearch("Поиск отличников"));
                        case 7 -> jFrame.add(panelForSearch("Поиск хорошистов"));
                        case 8 -> jFrame.add(panelForSearch("Поиск студентов с тройками"));
                        case 9 -> jFrame.add(panelForSearch("Поиск студентов на отчисление"));
                        case 10 -> {
                            jFrame.remove(searchPanel);
                            jFrame.add(menu());
                            jFrame.revalidate();
                        }
                    }
                    jFrame.revalidate();
                }
            });
        }
        searchPanel.add(title);
        return searchPanel;
    }

    public JPanel createFileMenu(){
        JPanel createPanel = new JPanel();
        createPanel.setLayout(null);
        JLabel title = new JLabel("Создание новых файлов");
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        title.setFont(font);
        title.setBounds(10, 0, 300, 30);
        JButton[] createButtons = new JButton[8];
        for(int i = 0; i < 8; i++){
            switch (i) {
                case 0 -> createButtons[i] = new JButton("Добавить в исходный файл");
                case 1 -> createButtons[i] = new JButton("Создать файл с одногруппниками");
                case 2 -> createButtons[i] = new JButton("Создать файл с поступившими в одном году");
                case 3 -> createButtons[i] = new JButton("Создать файл с отличниками");
                case 4 -> createButtons[i] = new JButton("Создать файл с хорошистами");
                case 5 -> createButtons[i] = new JButton("Создать файл с троешниками");
                case 6 -> createButtons[i] = new JButton("Создать файл со списком на отчисление");
                case 7 -> createButtons[i] = new JButton("Назад");
            }
            createButtons[i].setBounds(140, 35 + i * 35, 300, 30);
            createPanel.add(createButtons[i]);
        }
        for(int i = 0; i < 8; i++){
            int temp = i;
            createButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<Student> tempList = new List<>();
                    switch (temp) {
                        case 0:
                            if(!brain.checkFile("file.txt"))
                                JOptionPane.showMessageDialog(menu(), "Не найден файл!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            else {
                                if(checkButton){
                                    brain.writeStartFile(brain.listOfStudent, "file.txt", false);
                                }
                                else {
                                    tempList.addAll(brain.createCorrectFile("file.txt"));
                                    tempList.addAll(brain.listOfStudent);
                                    brain.writeStartFile(tempList, "file.txt", false);
                                    for (int i = 0; i < tempList.size(); i++)
                                        tempList.remove(i);
                                }
                                JOptionPane.showMessageDialog(menu(), "Данные записаны в файл!", "Запись", JOptionPane.INFORMATION_MESSAGE);
                                checkButton = false;
                            }
                            createPanel.revalidate();
                            break;
                        case 1:
                            jFrame.remove(createPanel);
                            jFrame.add(panelForCreate("Создание файла с одногруппниками"));
                            jFrame.revalidate();
                            break;
                        case 2:
                            jFrame.remove(createPanel);
                            jFrame.add(panelForCreate("Создание файла с поступившими в одном году"));
                            jFrame.revalidate();
                            break;
                        case 3:
                            if(brain.searchExcellent().size() == 0)
                                JOptionPane.showMessageDialog(createPanel, "Файл не создан!\n(Исходный список пуст или пуст список поиска)",
                                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                            else {
                                if(brain.checkFile("Отличники.txt") && brain.createCorrectFile("Отличники.txt").size() != 0) {
                                    JOptionPane.showMessageDialog(createPanel, "Данные записаны!", "Запись", JOptionPane.INFORMATION_MESSAGE);
                                    tempList.addAll(brain.createCorrectFile("Отличники.txt"));
                                }
                                else
                                    JOptionPane.showMessageDialog(createPanel, "Файл создан!", "Создание", JOptionPane.INFORMATION_MESSAGE);
                                tempList.addAll(brain.searchExcellent());
                                brain.writeStartFile(tempList, "Отличники.txt", false);
                                for(int i = 0; i < tempList.size(); i++)
                                    tempList.remove(i);
                            }
                            break;
                        case 4:
                            if(brain.searchWithFour().size() == 0)
                                JOptionPane.showMessageDialog(createPanel, "Файл не создан!\n(Исходный список пуст или пуст список поиска)",
                                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                            else {
                                if(brain.checkFile("Хорошисты.txt") && brain.createCorrectFile("Хорошисты.txt").size() != 0) {
                                    JOptionPane.showMessageDialog(createPanel, "Данные записаны!", "Запись", JOptionPane.INFORMATION_MESSAGE);
                                    tempList.addAll(brain.createCorrectFile("Хорошисты.txt"));
                                }
                                else
                                    JOptionPane.showMessageDialog(createPanel, "Файл создан!", "Создание", JOptionPane.INFORMATION_MESSAGE);
                                tempList.addAll(brain.searchWithFour());
                                brain.writeStartFile(tempList, "Хорошисты.txt", false);
                                for(int i = 0; i < tempList.size(); i++)
                                    tempList.remove(i);
                            }
                            break;
                        case 5:
                            if(brain.searchWithThree().size() == 0)
                                JOptionPane.showMessageDialog(createPanel, "Файл не создан!\n(Исходный список пуст или пуст список поиска)",
                                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                            else {
                                if(brain.checkFile("Без_Стипендии.txt") && brain.createCorrectFile("Без_Стипендии.txt").size() != 0) {
                                    JOptionPane.showMessageDialog(createPanel, "Данные записаны!", "Запись", JOptionPane.INFORMATION_MESSAGE);
                                    tempList.addAll(brain.createCorrectFile("Без_Стипендии.txt"));
                                }
                                else
                                    JOptionPane.showMessageDialog(createPanel, "Файл создан!", "Создание", JOptionPane.INFORMATION_MESSAGE);
                                tempList.addAll(brain.searchWithThree());
                                brain.writeStartFile(tempList, "Без_Стипендии.txt", false);
                                for(int i = 0; i < tempList.size(); i++)
                                    tempList.remove(i);
                            }
                            break;
                        case 6:
                            if(brain.searchForExpulsion().size() == 0)
                                JOptionPane.showMessageDialog(createPanel, "Файл не создан!\n(Исходный список пуст или пуст список поиска)",
                                        "Ошибка", JOptionPane.ERROR_MESSAGE);
                            else {
                                if(brain.checkFile("На_отчисление.txt")  && brain.createCorrectFile("На_отчисление.txt").size() != 0) {
                                    JOptionPane.showMessageDialog(createPanel, "Данные записаны!", "Запись", JOptionPane.INFORMATION_MESSAGE);
                                    tempList.addAll(brain.createCorrectFile("На_отчисление.txt"));
                                }
                                else
                                    JOptionPane.showMessageDialog(createPanel, "Файл создан!", "Создание", JOptionPane.INFORMATION_MESSAGE);
                                tempList.addAll(brain.searchForExpulsion());
                                brain.writeStartFile(tempList, "На_отчисление.txt", false);
                                for(int i = 0; i < tempList.size(); i++)
                                    tempList.remove(i);
                            }
                            break;
                        case 7:
                            jFrame.remove(createPanel);
                            jFrame.add(menu());
                            jFrame.revalidate();
                            break;
                    }
                    createPanel.revalidate();
                }
            });
        }
        createPanel.add(title);
        return createPanel;
    }

    public JPanel dataOnList(List<Student> list, String name){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JTextArea showList = new JTextArea(15, 300);
        showList.setEditable(false);
        JButton exit = new JButton("Назад");
        JLabel title = new JLabel(name);
        int result;
        int j = 1;
        for(int i = 0; i < list.size(); i++){
            StringBuilder currentInform = new StringBuilder(j + ") " + list.get(i).surname + " " +
                    list.get(i).name + " " + list.get(i).numberOfTicket + " " + list.get(i).numberOfGroup + " ");
            for(int k = 0; k < 5; k++) {
                if (k != 4)
                    currentInform.append(list.get(i).marks[k]).append(" ");
                else
                    currentInform.append(list.get(i).marks[k]);
            }
            j++;
            showList.append(currentInform + "\n");
        }
        JScrollPane scrollPane = new JScrollPane(showList);
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        title.setFont(font);
        title.setBounds(10, 0, 400, 30);
        scrollPane.setBounds(10, 40, 550, 250);
        exit.setBounds(460, 330, 100, 30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.add(menu());
                jFrame.remove(jPanel);
                jFrame.revalidate();
            }
        });
        jPanel.add(title);
        jPanel.add(exit);
        jPanel.add(scrollPane);
        return jPanel;
    }

    public JPanel panelForEdit(){
        JPanel jPanel = dataOnList(brain.listOfStudent, "Редактирование данных");
        JLabel field = new JLabel("Выберите номер студента в списке:");
        JTextField text = new JTextField("Введите номер");
        JButton edit = new JButton("Редактировать");
        text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        field.setBounds(10,300, 300, 20);
        text.setBounds(240, 300, 100, 20);
        edit.setBounds(300, 330, 150, 30);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(text.getText()) - 1;
                if (!brain.checkInt(text.getText())) {
                    JOptionPane.showMessageDialog(jPanel, "Введите номер", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else if (brain.listOfStudent.size() == 0)
                    JOptionPane.showMessageDialog(jPanel, "Список пуст!",
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                else if (Integer.parseInt(text.getText()) > brain.listOfStudent.size())
                    JOptionPane.showMessageDialog(jPanel, "Введите номер, который меньше"
                            + (brain.listOfStudent.size() + 1), "Ошибка", JOptionPane.ERROR_MESSAGE);
                else {
                    String[] tempArray = new String[4];
                    int[] temp = new int[5];
                    tempArray[0] = brain.listOfStudent.get(number).surname;
                    tempArray[1] = brain.listOfStudent.get(number).name;
                    tempArray[2] = String.valueOf(brain.listOfStudent.get(number).numberOfTicket);
                    tempArray[3] = String.valueOf(brain.listOfStudent.get(number).numberOfGroup);
                    for (int i = 0; i < 5; i++) {
                        temp[i] = brain.listOfStudent.get(number).marks[i];
                    }
                    jFrame.add(edit(brain.listOfStudent, tempArray, temp, number));
                    jFrame.remove(jPanel);
                    jFrame.revalidate();
                }
            }
        });
        jPanel.add(edit);
        jPanel.add(text);
        jPanel.add(field);
        return jPanel;
    }

    public JPanel panelForRemove(){
        JPanel jPanel = dataOnList(brain.listOfStudent, "Удаление данных");
        JLabel field = new JLabel("Выберите номер студента в списке:");
        JTextField text = new JTextField("Введите номер");
        JButton remove = new JButton("Удалить");
        text.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText(null);
                source.removeFocusListener(this);
            }
        });
        field.setBounds(10,300, 300, 20);
        text.setBounds(240, 300, 100, 20);
        remove.setBounds(300, 330, 150, 30);
        jPanel.add(field);
        jPanel.add(text);
        jPanel.add(remove);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(brain.listOfStudent.size() == 0) {
                    jFrame.remove(jPanel);
                    jFrame.add(menu());
                    jFrame.revalidate();
                    JOptionPane.showMessageDialog(panelForRemove(), "Список пуст!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } else if(!brain.checkInt(text.getText())) {
                    JOptionPane.showMessageDialog(panelForRemove(), "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    jFrame.revalidate();
                } else if(!brain.checkDeleteInform(text.getText()))
                    JOptionPane.showMessageDialog(panelForRemove(), "Введите номер, меньший " + (brain.listOfStudent.size() + 1),
                            "Ошибка", JOptionPane.ERROR_MESSAGE);
                else if(brain.checkDeleteInform(text.getText())) {
                    jFrame.remove(jPanel);
                    jFrame.add(menu());
                    jFrame.revalidate();
                    JOptionPane.showMessageDialog(jPanel, "Студент " + brain.listOfStudent.get((Integer.parseInt(text.getText()) - 1)).surname
                            + " " + brain.listOfStudent.get(Integer.parseInt(text.getText()) - 1).name + " удален!", "Удаление", JOptionPane.INFORMATION_MESSAGE);
                    brain.deleteInform(text.getText());
                }
            }
        });
        return jPanel;
    }

    public JScrollPane paneSearch(List<Student> tempList){
        JTextArea area = new JTextArea(15, 200);
        area.setEditable(false);
        int k = 1;
        for (int i = 0; i < tempList.size(); i++) {
            String tempSurname = tempList.get(i).surname;
            String tempName = tempList.get(i).name;
            int tempTicket = tempList.get(i).numberOfTicket;
            int tempGroup = tempList.get(i).numberOfGroup;
            area.append(k + ")" + tempSurname + " " + tempName + " " + tempTicket + " " + tempGroup + " ");
            for (int j = 0; j < 5; j++) {
                if (j != 4)
                    area.append(tempList.get(i).marks[j] + " ");
                else
                    area.append(tempList.get(i).marks[j] + "\n");
            }
            k++;
        }
        JScrollPane pane = new JScrollPane(area);
        pane.setBounds(10, 80, 400, 200);
        return pane;
    }

    public JPanel panelForSearch(String name) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JLabel title = new JLabel(name);
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        title.setFont(font);
        title.setBounds(10, 0, 400, 30);
        JButton exit = new JButton("Назад");
        exit.setBounds(450, 320, 100, 30);
        switch (name) {
            case "Поиск отличников" -> jPanel.add(paneSearch(brain.searchExcellent()));
            case "Поиск хорошистов" -> jPanel.add(paneSearch(brain.searchWithFour()));
            case "Поиск студентов с тройками" -> jPanel.add(paneSearch(brain.searchWithThree()));
            case "Поиск студентов на отчисление" -> jPanel.add(paneSearch(brain.searchForExpulsion()));
        }
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.remove(jPanel);
                jFrame.add(searchMenu());
                jFrame.revalidate();
            }
        });
        jPanel.revalidate();
        jPanel.add(title);
        jPanel.add(exit);
        return jPanel;
    }

    public JPanel panelForSearchInt(String name) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JLabel title = new JLabel(name);
        JLabel titleSearch = new JLabel("Введите: ");
        JTextField textField = new JTextField();
        JButton search = new JButton("Поиск");
        JButton exit = new JButton("Назад");
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        title.setFont(font);
        title.setBounds(10, 0, 400, 30);
        titleSearch.setBounds(10, 40, 200, 20);
        textField.setBounds(210, 40, 200, 20);
        search.setBounds(340, 320, 100, 30);
        exit.setBounds(450, 320, 100, 30);
        jPanel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (name) {
                    case "Поиск по группе":
                        if(!brain.checkInt(textField.getText())) {
                            JOptionPane.showMessageDialog(jPanel, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            jFrame.revalidate();
                        }
                        else
                            jPanel.add(paneSearch(brain.searchByGroup(Integer.parseInt(textField.getText()))));
                        break;
                    case "Поиск по году":
                        if(!brain.checkInt(textField.getText())) {
                            JOptionPane.showMessageDialog(jPanel, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            jFrame.revalidate();
                        }
                        else
                            jPanel.add(paneSearch(brain.searchByYear(Integer.parseInt(textField.getText()))));
                        break;
                    case "Поиск по институту":
                        if(!brain.checkInt(textField.getText())) {
                            JOptionPane.showMessageDialog(jPanel, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            jFrame.revalidate();
                        }
                        else
                            jPanel.add(paneSearch(brain.searchByInstitute(Integer.parseInt(textField.getText()))));
                        break;
                    case "Поиск по студенческому билету":
                        if(!brain.checkInt(textField.getText())) {
                            JOptionPane.showMessageDialog(jPanel, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            jFrame.revalidate();
                        }
                        else
                            jPanel.add(paneSearch(brain.searchByTicket(Integer.parseInt(textField.getText()))));
                        break;
                    case "Поиск по фамилии":
                        jPanel.add(paneSearch(brain.searchSurname(brain.firstUpperCase(textField.getText()))));
                        break;
                    case "Поиск по имени":
                        jPanel.add(paneSearch(brain.searchName(brain.firstUpperCase(textField.getText()))));
                        break;
                }
                jPanel.revalidate();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.remove(jPanel);
                jFrame.add(searchMenu());
                jFrame.revalidate();
            }
        });
        jPanel.add(exit);
        jPanel.add(title);
        jPanel.add(textField);
        jPanel.add(titleSearch);
        return jPanel;
    }

    public JPanel panelForCreate (String name){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel title = new JLabel(name);
        Font font = new Font("Bitstream Charter", Font.BOLD, 20);
        title.setFont(font);
        JButton create = new JButton("Создать");
        JButton exit = new JButton("Назад");
        JLabel titleSearch = new JLabel("Введите: ");
        JTextField textField = new JTextField();

        title.setBounds(10, 0, 500, 30);
        titleSearch.setBounds(10, 40, 200, 20);
        textField.setBounds(210, 40, 200, 20);
        create.setBounds(340, 320, 100, 30);
        exit.setBounds(450, 320, 100, 30);
        panel.add(create);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> tempList = new List<>();
                switch (name){
                    case "Создание файла с одногруппниками":
                        if(!brain.checkInt(textField.getText()))
                            JOptionPane.showMessageDialog(panel, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        else if(Integer.parseInt(textField.getText()) <= 0 || Integer.parseInt(textField.getText()) >= 10000)
                            JOptionPane.showMessageDialog(panel, "Введите новое число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        else if(brain.searchByGroup(Integer.parseInt(textField.getText())).size() == 0)
                            JOptionPane.showMessageDialog(panel, "Файл не создан!\n(Исходный список пуст или пуст список поиска)",
                                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                        else {
                            if(brain.checkFile((textField.getText() + ".txt")) && brain.createCorrectFile((textField.getText() + ".txt")).size() != 0){
                                JOptionPane.showMessageDialog(panel, "Данные записаны!", "Запись", JOptionPane.INFORMATION_MESSAGE);
                                tempList.addAll(brain.createCorrectFile((textField.getText() + ".txt")));
                            }
                            else
                                JOptionPane.showMessageDialog(panel, "Файл создан!", "Создание", JOptionPane.INFORMATION_MESSAGE);
                            tempList.addAll(brain.searchByGroup(Integer.parseInt(textField.getText())));
                            brain.writeStartFile(tempList, (textField.getText() + ".txt"), false);
                            for(int i = 0; i < tempList.size(); i++)
                                tempList.remove(i);
                        }
                            jFrame.remove(panel);
                            jFrame.add(createFileMenu());
                        break;
                    case "Создание файла с поступившими в одном году":
                        if(!brain.checkInt(textField.getText()))
                            JOptionPane.showMessageDialog(panel, "Введите число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        else if(Integer.parseInt(textField.getText()) <= 2016 || Integer.parseInt(textField.getText()) > 2021)
                            JOptionPane.showMessageDialog(panel, "Введите новое число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        else if(brain.searchByYear(Integer.parseInt(textField.getText())).size() == 0)
                            JOptionPane.showMessageDialog(panel, "Файл не создан!\n(Исходный список пуст или пуст список поиска)",
                                    "Ошибка", JOptionPane.ERROR_MESSAGE);
                        else {
                            String name = textField.getText() + ".txt";
                            if(brain.checkFile((textField.getText() + ".txt")) && brain.createCorrectFile(name).size() != 0) {
                                JOptionPane.showMessageDialog(panel, "Данные записаны!", "Запись", JOptionPane.INFORMATION_MESSAGE);
                                tempList.addAll(brain.createCorrectFile((textField.getText() + ".txt")));
                            }
                            else
                                JOptionPane.showMessageDialog(panel, "Файл создан!", "Создание", JOptionPane.INFORMATION_MESSAGE);
                            tempList.addAll(brain.searchByYear(Integer.parseInt(textField.getText())));
                            brain.writeStartFile(tempList, (textField.getText() + ".txt"), false);
                            for(int i = 0; i < tempList.size(); i++)
                                tempList.remove(i);
                        }
                            jFrame.remove(panel);
                            jFrame.add(createFileMenu());
                        break;
                }
                jFrame.revalidate();
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.remove(panel);
                jFrame.add(createFileMenu());
                jFrame.revalidate();
            }
        });
        panel.add(title);
        panel.add(titleSearch);
        panel.add(textField);
        panel.add(create);
        panel.add(exit);
        return panel;
    }

    public static JFrame getFrame(String title){
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - 300, dimension.height/2 - 200, 600, 400);
        jFrame.setResizable(false);
        jFrame.setTitle(title);
        return jFrame;
    }
}
