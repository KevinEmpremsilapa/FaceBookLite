public abstract class Person
{
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private Password password;
    private String email;
    
    public Person()
    {
        firstName = "";
        lastName = "";
        age = 0;
    }
    
    public Person(String firstName , String lastName , int age)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(String firstName , String lastName , int age, String username, Password password, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
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

    public Password getPassword() {
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
    public void setPassword(Password password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}

