public class PersonDAO extends Person
{
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private String password;
    private String email;

    public PersonDAO()
    {
        super();
    }

    public PersonDAO(String firstName , String lastName , int age)
    {
        super(firstName, lastName, age);
    }

    public PersonDAO(String firstName , String lastName , int age, String username, String password, String email)
    {
        super(firstName, lastName, age, username, password, email);
    }

    // getters
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getAge()
    {
        return age;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    // setters
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
