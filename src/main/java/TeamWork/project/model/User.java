package TeamWork.project.model;

public class User {
    private String firstName;
    private String lastName;

    // Конструктор
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Геттеры
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Метод для получения полного имени
    public String getFullName() {
        return firstName + " " + lastName;
    }
}