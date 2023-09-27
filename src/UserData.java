import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserData {
    private String userString;

    public String getFullName() {
        return fullName;
    }

    private String fullName;
    private String dateOfBirth;
    private long phoneNumber;
    private String gender;

    public UserData(String userString) {
        this.userString = userString;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void checkUserData() throws UserDataException, DateTimeParseException, NumberFormatException{
        String[] userData = userString.split(" ");
        if (userData.length < 6){
            throw new UserDataException("Введена не вся информация. Мне жаль ;(");
        }else if(userData.length > 6){
            throw new UserDataException("Введена избыточная информация. Мне жаль ;(");
        }

        fullName =  userData[0] + " " + userData[1] + " " + userData[2];
        dateOfBirth = userData[3];
        try{
            phoneNumber = Long.parseLong(userData[4]);
        }catch (NumberFormatException e){
            throw new NumberFormatException("Не удалось преобразовать строку телефона в LONG. Мне жаль ;(");
        }

        gender = userData[5];


        if (fullName.isEmpty() || fullName.matches(".*\\d.*")){
            throw new UserDataException("ФИО пустое, либо есть цифры. Мне жаль ;(");
        }
        //проверяем дату рождения
        String resultCheckDateOfBirth = checkDateOfBirth();
        if (resultCheckDateOfBirth != null){
            throw new DateTimeParseException(resultCheckDateOfBirth, dateOfBirth, 0);
        }
        //проверяем телефон
        String resultChekPhone = chekPhone();
        if (resultChekPhone != null){
            throw new UserDataException(resultChekPhone);
        }
        if (!gender.equals("f") && !gender.equals("m")){
            throw new UserDataException("Пол не соответствует формату. Мне жаль ;(");
        }
        System.out.println("Все данные корректны. Поздравляю!");

    }
    //делал для номеров РБ ( 375 хх ххх хх хх )
    private String chekPhone(){
        String message = null;
        if (String.valueOf(phoneNumber).length() < 11){
            message = "Слишком короткий номер";
            return message;
        }else if(phoneNumber < 375000000000L){
            message = "Неверный формат кода 375 в номере телефона";
        }else if(phoneNumber < 375130000000L){
            message = "Неверный формат кода оператора в номере телефона";
            return message;
        }else if (phoneNumber < 375131000000L){
            message = "Персональный номер телефона не может начинаться с 0";
            return message;
        }else{
            return message;
        }
        return message;
    }

    private String checkDateOfBirth() throws DateTimeParseException {
        String message = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try{
            LocalDate userDateOfBirth = LocalDate.parse(dateOfBirth, formatter);
            LocalDate currentDate = LocalDate.now();
            if (userDateOfBirth.compareTo(currentDate) > 0){
                message = "Введена дата рождения больше текущей.";
                return message;
            }else if (userDateOfBirth.getYear() < currentDate.getYear() - 110){
                message = "Вам не может быть больше 110 лет.";
                return message;
            }else{
                return message;
            }

        }catch (DateTimeParseException e){
            message = "Дата не соответствует формату. Мне жаль ;(";
        }
        return message;
    }
}
