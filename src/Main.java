import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class Main {
//    1.Сохранить в файл строку и загрузить из файла строку с выводом в консоль используя классы
//    FileWriter и FileReader
//2.Загрузить из файла многострочный текст формата ФИО возраст и пол через пробелы.
// Разбить по строкам и вывести в консоль в формате "Иванов И.И. 32 М"
//            3.Загруженный и разбитый по строкам текст загрузить в подготовленные списки.
//            Фамилии, имена, отчества, возрас и пол в отдельных списках.
//            4.Отсортировать по возрасту используя дополнительный список индексов.
    public static void main(String[] args) throws IOException {
        System.out.println("генерация рандомной строки");
        String rand_str = "";


        rand_str += (UUID.randomUUID());

        System.out.println(rand_str);


        try (var fw = new FileWriter("testFile.txt")) {
            fw.write(rand_str);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        File file = new File("testFile.txt");
        FileReader fr = new FileReader(file);
        char[] a = new char[100];   // Количество символов, которое будем считывать
        fr.read(a);   // Чтение содержимого в массив
        System.out.println("FILE READ OK");
        for (char c : a)
            System.out.print(c);   // Вывод символов один за другими
        fr.close();
        System.out.println();


        String filePath = "users.txt";

        String content = null;
        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] row = new String[]{new String()};
        System.out.println(content);
        String[] words = (content.split("\n"));


        String[] Data = new String[5];
        for (int i = 0; i < Data.length; i++) {
            Data[i] = "";
        }


        for (int i = 0; i < words.length; i++) {
            row = words[i].split(" ");

            for (int j = 0; j < row.length; j++) {

                if (j == 0) {
                    Data[0] += row[j] + "\n";
                    try (var fw = new FileWriter("fam.txt")) {

                        fw.write(Data[0]);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
//                ЗАПИСЬ ПОЛА
                if (j == 4) {


                    Data[4] += row[j] + "\n";
                    try (var fw = new FileWriter("male.txt")) {
                        fw.write(Data[4]);


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
//                ЗАПИСЬ ОТЧЕСТВА
                if (j % 2 == 0 && j != 0 && j % 4 != 0) {


                    Data[2] += row[j] + "\n";
                    try (var fw = new FileWriter("th_names.txt")) {
                        fw.write(Data[2]);


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
//                ЗАПИСЬ ВОЗРАСТА
                if (j % 3 == 0 && j != 0) {


                    Data[3] += row[j] + "\n";
                    try (var fw = new FileWriter("ages.txt")) {
                        fw.write(Data[3]);


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
//                ЗАПИСЬ ИМЕНИ
                if (j == 1) {


                    Data[1] += row[j] + "\n";
                    try (var fw = new FileWriter("names.txt")) {
                        fw.write(Data[1]);


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }


        }
        System.out.println();
        String age_file = "ages.txt";

        String age_str = "";
        try {
            age_str = readFile(age_file, StandardCharsets.UTF_8);
            System.out.println(age_str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert age_str != null;
        String[] age_row=(age_str.split("\n"));
        Arrays.sort(age_row);
        try (var fw = new FileWriter("sort_ages.txt")) {
            for (int i=0;i<age_row.length;i++){
                fw.write(age_row[i]+"\n");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }





    }
    public static String readFile(String path, Charset encoding) throws IOException
    {
        List<String> lines = Files.readAllLines(Paths.get(path), encoding);
        return String.join(System.lineSeparator(), lines);
    }
}
