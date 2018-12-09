import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import org.bson.types.ObjectId;

public class DBUtil
{
    private static final String connectionString = "mongodb://User:password123qaz@comp585-shard-00-00-cpnmd.gcp.mongodb.net:27017/test?ssl=true&replicaSet=COMP585-shard-0&authSource=admin&retryWrites=true";
    private static MongoClientURI uri;
    private static MongoClient mongoClient;
    private static DB database;
    private static DBCollection collection;

    public static void connectDatabase()
    {
        uri = new MongoClientURI(connectionString);
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDB("Main");
    }

    /*public static void main (String[] args) {

        //startDatabase();
        //Person person = new PersonDAO("Abby", "James",13, "abc", "xyz", "h@g.com");
        //newRegister(person);
        //System.out.println(loginUser("abc", "xyz"));
        //updatePassword("xyz", "abc");

        //Change "testColle" to anything if you want
        DBCollection coll = db2.getCollection("testColle");
        BasicDBObject doc = new BasicDBObject("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new BasicDBObject("x", 203).append("y", 102));
        coll.insert(doc);

        //This will show all the names of the databases, you should see the database you added from the TODO line
        mongoClient.getDatabaseNames().forEach(System.out::println);
        //DBCollection db = db2.getCollection("Users");

        //FINDER
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("firstName", "Garcia");
        DBCursor cursor = db.find(searchQuery);
        while(cursor.hasNext())
        {
            System.out.println("----" + cursor.next());
        }
    }*/

    public static String registerUser(Person person)
    {
        collection = database.getCollection("Users");

        BasicDBObject document = new BasicDBObject();
        document.put("firstName", person.getFirstName());
        document.put("lastName", person.getLastName());
        document.put("age", person.getAge());
        document.put("username", person.getUsername());
        document.put("password", new BasicDBObject("enc", person.getPassword().getEncrypted()).append("key", person.getPassword().getKey()));
        document.put("email", person.getEmail());
        collection.insert(document);

        return document.get("_id").toString();
    }

    public static Person loginUser(String username, String password)
    {
        collection = database.getCollection("Users");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", username);

        DBCursor cursor = collection.find(searchQuery);
        if(cursor.hasNext()) {
            DBObject db = cursor.next();
            DBObject db2 = (DBObject) db.get("password");
            System.out.println("password " + db.get("password"));
            System.out.println("input: " + db2.get("enc") + " hidden: " +  db2.get("key"));

            if(AES.decrypt(db2.get("enc").toString(), db2.get("key").toString()).equals(password))
            {
                ObjectId id = (ObjectId) db.get("_id");
                System.out.println("Login Success");
                return profileInfo(id);
            }
            else
                return null;
        }

        else
            return null;
    }

    private static Person profileInfo(ObjectId uid)
    {
        Person person = new PersonDAO();
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", uid);

        DBCursor cursor = collection.find(searchQuery);
        if(cursor.hasNext()) {
            DBObject db = cursor.next();
            ObjectId id = (ObjectId) db.get("_id");
            person.setFirstName(db.get("firstName").toString());
            person.setLastName(db.get("lastName").toString());
            person.setAge(Integer.parseInt(db.get("age").toString()));
            //String firstName = db.get("firstName").toString();
            //String firstName = db.get("firstName").toString();

            System.out.println("Login   " + id);
        }
        return person;
    }

    public static void updatePassword(String email, String newPassword)
    {
        collection = database.getCollection("Users");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("password", new BasicDBObject()
                .append("enc", newPassword).append("key", email)));

        BasicDBObject searchQuery = new BasicDBObject().append("email", email);

        collection.update(searchQuery, newDocument);
    }

    public static boolean searchField(String field, String find)
    {
        collection = database.getCollection("Users");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(field, find);

        DBCursor cursor = collection.find(searchQuery);
        if(cursor.hasNext()) {
            return true;
        }
        else
            return false;
    }

    public static void printAllFriends(String uid)
    {
        collection = database.getCollection("Friendship");

    }

    public static void setDOB()
    {
        collection = database.getCollection("Friendship");
    }

    public static void getPosts()
    {
        collection = database.getCollection("Posts");
    }
}
