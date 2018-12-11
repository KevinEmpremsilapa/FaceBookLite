public class PersonDAO extends Person implements DAO
{
    private String firstName;
    private String lastName;
    private int age;
    private String username;
    private Password password;
    private String email;
    private boolean hideFriends;
    private boolean hidePosts;
    private boolean hideAge;
    private boolean hideStatus;

    public PersonDAO()
    {
        super();
    }

    public PersonDAO(String firstName , String lastName , int age)
    {
        super(firstName, lastName, age);
    }

    public PersonDAO(String firstName , String lastName , int age, String username, Password password, String email)
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

    public Password getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    public String getFullName(){return firstName + " " + lastName;}

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
    public void setFullName(String name){
        int i = name.lastIndexOf(' ');

        firstName = name.substring(0, i);
        lastName = name.substring(i + 1);
    }
    public boolean isHideFriends() {
        return hideFriends;
    }

    public void setHideFriends(boolean hideFriends) {
        this.hideFriends = hideFriends;
    }

    public boolean isHidePosts() {
        return hidePosts;
    }

    public void setHidePosts(boolean hidePosts) {
        this.hidePosts = hidePosts;
    }

    public boolean isHideAge() {
        return hideAge;
    }

    public void setHideAge(boolean hideAge) {
        this.hideAge = hideAge;
    }

    public boolean isHideStatus() {
        return hideStatus;
    }

    public void setHideStatus(boolean hideStatus) {
        this.hideStatus = hideStatus;
    }

    //===DAO======

    public <T> void update(T t, String[] params) {};
    public <T> void delete(T t) {};
    public void registerPerson(Person person)
    {
        DBUtil.registerUser(person);
    }
    public Person login(String username, String password)
    {
        return DBUtil.loginUser(username,password);
    }
    public void addFriend(Person user, Person friend)
    {
        DBUtil.addFriend(user, friend);
    }
    public void updatePassword(String email, String newPassword)
    {
        DBUtil.updatePassword(email, newPassword);
    }
    public void addPost(Person user, Post post)
    {
        DBUtil.addPost(user,post);
    }
    public void deletePost(Person user, Post post)
    {
        DBUtil.deletePost(user,post);
    }
    public void updateField(Person user, String field, String updateInfo)
    {
        DBUtil.updateField(user,field,updateInfo);
    }
    public void updateField(Person user, String field, String innerField, String updateInfo)
    {
        DBUtil.updateField(user,field,innerField, updateInfo);
    }
    public void searchField(String coll, String field, String find)
    {
        DBUtil.searchField(coll,field,find);
    }


    public void deleteFriend(Person user, Person friend)
    {
        DBUtil.deleteFriend(user,friend);
    }


}
