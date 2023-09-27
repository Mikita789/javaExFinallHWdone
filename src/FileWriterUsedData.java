import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUsedData {
    UserData person;
    public FileWriterUsedData(UserData person){
        this.person = person;
    }

    public void savePerson() throws  IOException{
        if (person != null){
            String namePerson = person.getFullName().split(" ")[0];
            String fileName = namePerson + ".txt";
            FileWriter fw = new FileWriter(fileName , true);
            String information = String.format("<%s><%s><%d><%s>\n",
                    person.getFullName(), person.getDateOfBirth(), person.getPhoneNumber(), person.getGender());
            fw.write(information);
            fw.close();
            System.out.println("Данные успешно записаны");
        }

    }
}
