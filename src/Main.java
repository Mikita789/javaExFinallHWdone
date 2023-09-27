import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws UserDataException, IOException {
        UserData userData = getUserData();

        FileWriterUsedData fileManager = new FileWriterUsedData(userData);
        try {
            fileManager.savePerson();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Не удалось записать в файл");
        }

    }

    private static UserData getUserData() throws UserDataException{
        UserData result = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("введите свои данные в формате /Фамилия Имя Отчество дата рождения(dd.mm.yyyy) номертелефона пол(f или m)/");
        System.out.println("Popov Nikita Olegovich 28.03.1993 375291111111 m");
        System.out.println();
        String userString = sc.nextLine();

        UserData ud = new UserData(userString);
        try{
            ud.checkUserData();
            return ud;
        }catch (UserDataException e){
            System.out.println(e.getMessage());
        }catch (DateTimeParseException d){
            System.out.println(d.getMessage());
        }

        if (result == null){
            throw new UserDataException("Не удалось создать объект.");
        }
        return result;
    }
}