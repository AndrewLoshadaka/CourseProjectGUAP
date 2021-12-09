package com.company;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Brain {
    List<Student> listOfStudent = new List<>();

    public Student setInform(JTextField[] textFields, JComboBox<Integer>[] marks){
        int[] tempMarks = new int[5];
        for(int i = 0; i < 5; i++){
            tempMarks[i] = (int)marks[i].getSelectedItem();
        }
        return new Student(firstUpperCase(textFields[0].getText().trim()), firstUpperCase(textFields[1].getText().trim()),
                Integer.parseInt(textFields[2].getText().trim()),
                Integer.parseInt(textFields[3].getText().trim()), tempMarks);
    }

    public boolean checkInt(String data){
        try {
            Integer.parseInt(data);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public boolean checkFile(String name){
        File file = new File("C://Users//Андрей//Desktop//myProject", name);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public String firstUpperCase(String word){
        String[] checkArray = {"!", "#", "%", "'", ",", "-", "*", "+", ".", "/", "0", "1", "2", "3", "4", "5", "6", "7",
                "8", "9", ":", ";", "(", ")", "{", "}", "[","]", "?", ";", "<", "=", ">", "@", "]", "^", "_", "|", "}", "~", "№", "$", "&"};
        String[] tempStr = word.split("");
        int size = tempStr.length;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < checkArray.length; j++) {
                if(tempStr[i].equals(checkArray[j])) {
                    tempStr[i] = "";
                }
            }
        }
        StringBuilder retStr = new StringBuilder();
        for(String x : tempStr){
            retStr.append(x);
        }
        word = retStr.toString().toLowerCase();
        if(word.equals(""))
            return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public List<Student> checkRepeat(List<Student> tempList) {
        for (int i = 0; i < tempList.size(); i++) { //корректировка списка
            for (int j = i + 1; j < tempList.size(); j++) {
                if (tempList.get(i).numberOfTicket == tempList.get(j).numberOfTicket) {
                    tempList.remove(j);
                    j--;
                }
            }
        }
        return tempList;
    }

    public boolean checkRepeatEnter(int tempTicket){ //повтор на вводе
        boolean flag = true;
        for(int i = 0; i < listOfStudent.size(); i++){
            if(listOfStudent.get(i).numberOfTicket == tempTicket) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public boolean checkRepeatEdit(int tempTicket, int position){ //просмотр повтора при редактировании
        boolean flag = true;
        for(int i = 0; i < listOfStudent.size(); i++){
            if(i != position) {
                if (listOfStudent.get(i).numberOfTicket == tempTicket) {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }


    public void addFile(String name){ //добавление в список неповторяющихся
        if(listOfStudent.size() == 0)
            listOfStudent.addAll(createCorrectFile(name));
        else {
            listOfStudent.addAll(createCorrectFile(name));
            checkRepeat(listOfStudent);
        }
    }

    public boolean checkNullOnString(String line){
        return !line.trim().equals("");
    }

    public boolean checkInt(String[] data){
        try {
            for (int i = 0; i < 5; i++) {
                if (!checkInt(data[i]) || (Integer.parseInt(data[i]) < 2 || Integer.parseInt(data[i]) > 5))
                    return false;
            }
        }catch (NumberFormatException e) {
        }
        return true;
    }

    public String createCorrectStr(String str){
        String newString;
        String name = null, surname = null;
        String ticket = null, group = null;
        String[] array = new String[5];
        int countElement= 0;
        for(int i = 0; i < str.length(); i++){
            char temp = str.charAt( i );
            if(temp == '#')
                countElement++;
        }
        if(countElement != 9)
            newString = null;
        else if(str.indexOf('#') < str.length() + 1) {
            str.substring(0, str.indexOf('#'));
            String[] value = str.split("#");
            for (int i = 0; i < value.length; i++) {
                surname = value[0].trim();
                name = value[1].trim();
                ticket = value[2].trim();
                group = value[3].trim();
                for (int j = 0; j < 5; j++)
                    array[j] = value[j + 4].trim();
            }
        }
            newString = surname + "# " + name + "# " + ticket + "# " + group + "# ";
            for (int k = 0; k < 5; k++) {
                newString = newString + array[k] + "# ";
            }
        return newString;
    }

    public List<Student> createCorrectFile(String nameOfFile){
            List<Student> tempList = new List<>();
            BufferedReader reader;
            Student student;
            String line;
            String name = null, surname = null;
            String ticket = null, group = null;
            String[] array = new String[5];
            try {
                reader = new BufferedReader(new FileReader((new File("C://Users//Андрей//Desktop//myProject",
                        nameOfFile)), StandardCharsets.UTF_8));
                line = reader.readLine();
                while (line != null) {
                    line = createCorrectStr(line);
                    String[] value = line.split("# ");
                    for (int i = 0; i < value.length; i++) {
                        surname = firstUpperCase(value[0].trim());
                        name = firstUpperCase(value[1].trim());
                        ticket = value[2].trim();
                        group = value[3].trim();
                        for (int j = 0; j < 5; j++)
                            array[j] = value[j + 4];
                    }
                    if (checkNullOnString(surname) && checkNullOnString(name) &&
                            checkInt(ticket) && checkInt(group) && checkInt(array)) {
                        int[] temp = new int[5];
                        for (int i = 0; i < 5; i++)
                            temp[i] = Integer.parseInt(array[i]);
                        assert ticket != null; //посмотреть assert
                        student = new Student(surname, name, Integer.parseInt(ticket), Integer.parseInt(group), temp);
                        tempList.add(student);
                    }
                    line = reader.readLine();
                }
            } catch (IOException e) {
                System.err.println("File not found");
            }
            checkRepeat(tempList);
            writeStartFile(tempList, nameOfFile, false);
        return tempList;
    }

    public void showCurrentInform(Student student){
        System.out.print(student.surname + " ");
        System.out.print(student.name + " ");
        System.out.print(student.numberOfTicket + " ");
        System.out.print(student.numberOfGroup + " ");
        for (int i = 0; i < 5; i++) {
            System.out.print(student.marks[i] + " ");
        }
        System.out.println();
    }

    public void showAllInform(List<Student> list){
        for(int i = 0; i < list.size(); i++){
            showCurrentInform(list.get(i));
        }
    }

    public List<Student> searchByGroup(int number) { //поиск по группе
        List<Student> temp = new List<>();
        showAllInform(temp);
        for (int i = 0; i < listOfStudent.size(); i++) {
            if (number == listOfStudent.get(i).numberOfGroup)
                temp.add(listOfStudent.get(i));
        }
        return temp;
    }

    public List<Student> searchByYear(int year){ //поиск по году
        List<Student> temp = new List<>();
        for(int i = 0; i < listOfStudent.size(); i++){
            if(listOfStudent.get(i).numberOfGroup / 100 % 10 == year % 10)
                temp.add(listOfStudent.get(i));
        }
        return temp;
    }

    public List<Student> searchByInstitute(int institute){ //поиск по иституту
        List<Student> temp = new List<>();
        for (int i = 0; i < listOfStudent.size(); i++){
            if(listOfStudent.get(i).numberOfGroup / 1000 == institute)
                temp.add(listOfStudent.get(i));
        }
        return temp;
    }

    public List<Student> searchName(String name) {//поиск по имени
        List<Student> temp = new List<>();
        for (int i = 0; i < listOfStudent.size(); i++)
            if (name.equalsIgnoreCase(listOfStudent.get(i).name))
                temp.add(listOfStudent.get(i));
        return temp;
    }

    public List<Student> searchSurname(String surname) {//поиск по фамилии
        List<Student> temp = new List<>();
        for (int i = 0; i < listOfStudent.size(); i++)
            if (surname.equalsIgnoreCase(listOfStudent.get(i).surname))
                temp.add(listOfStudent.get(i));
        return temp;
    }

    public List<Student> searchByTicket(int ticket){ //поиск по билету
        List<Student> temp = new List<>();
        for (int i = 0; i < listOfStudent.size(); i++){
            if(listOfStudent.get(i).numberOfTicket == ticket)
                temp.add(listOfStudent.get(i));
        }
        return temp;
    }

    public List<Student> searchExcellent(){ //поиск отличников
        List<Student> temp = new List<>();
        for(int i = 0; i < listOfStudent.size(); i++){
            int sumOfMarks = 0;
            for(int j = 0; j < 5; j++)
                sumOfMarks += listOfStudent.get(i).marks[j];
            if(sumOfMarks == 25)
                temp.add(listOfStudent.get(i));
        }
        return temp;
    }

    public List<Student> searchWithFour(){
        List<Student> temp = new List<>();
        for(int i = 0; i < listOfStudent.size(); i++){
            int composition = 1;
            for(int j = 0; j < 5; j++){
                composition = composition * listOfStudent.get(i).marks[j];
            }
            if(composition > 1023 && composition < 2501 && composition != 1250 && composition % 3 != 0)
                temp.add(listOfStudent.get(i));
        }
        return temp;
    }

    public boolean catchTwo(int[] array){
        boolean flag = false;
        for(int i = 0; i < 5; i++){
            if(array[i] == 2){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public List<Student> searchWithThree(){ //без стипендии (3, но без 2)
        List<Student> temp = new List<>();
        for (int i = 0; i < listOfStudent.size(); i++) {
            for(int j = 0; j < 5; j++)
                if(!catchTwo(listOfStudent.get(i).marks) && listOfStudent.get(i).marks[j] == 3) {
                    temp.add(listOfStudent.get(i));
                    break;
                }
        }
        return temp;
    }

    public List<Student> searchForExpulsion(){
        List<Student> temp = new List<>();
        for(int i = 0; i < listOfStudent.size(); i++) {
            if (catchTwo(listOfStudent.get(i).marks)) {
                temp.add(listOfStudent.get(i));
            }
        }
        return temp;
    }

    public boolean checkDeleteInform(String number) {
        if (Integer.parseInt(number) > listOfStudent.size())
            return false;
        else
            return true;
    }

    public void deleteInform(String number){
        listOfStudent.remove(Integer.parseInt(number) - 1);
    }

    public void writeStartFile(List<Student> list, String name, boolean checkFlag) { //запись в исходник, checkFlag - на запись в конец(1) или с начала (0)
        checkRepeat(list);
        FileWriter writer = null;
        try {
            File file = new File("C://Users//Андрей//Desktop//myProject", name);
            if (!file.exists())
                file.createNewFile();
            writer = new FileWriter(file, checkFlag);
            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).surname + "# ");
                writer.write(list.get(i).name + "# ");
                writer.write(list.get(i).numberOfTicket + "# ");
                writer.write(list.get(i).numberOfGroup + "# ");
                for (int j = 0; j < 5; j++) {
                    writer.write(list.get(i).marks[j] + "# ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("File not found");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("File not found");
            }
        }
    }
}
