import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String registerUser(Person person)
    {
        collection = database.getCollection("Users");

        BasicDBObject document = new BasicDBObject();
        document.put("firstName", person.getFirstName());
        document.put("lastName", person.getLastName());
        document.put("age", person.getAge());
        document.put("username", person.getUsername());
        document.put("password", new BasicDBObject("enc", person.getPassword().getEncrypted())
                .append("key", person.getPassword().getKey()));
        document.put("email", person.getEmail());
        document.put("status", "online");
        document.put("hidden", new BasicDBObject("hideFriends", false)
                .append("hidePosts", false)
                .append("hideAge", false)
                .append("hideStatus", false));
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

            if(AES.decrypt(db2.get("enc").toString(), db2.get("key").toString()).equals(password))
            {
                ObjectId id = (ObjectId) db.get("_id");
                return profileInfo(id);
            }
            else
                return null;
        }

        else
            return null;
    }

    public static boolean searchField(String coll, String field, String find)
    {
        collection = database.getCollection(coll);
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put(field, find);

        DBCursor cursor = collection.find(searchQuery);
        if(cursor.hasNext()) {
            return true;
        }
        else
            return false;
    }

    private static Person profileInfo(ObjectId uid)
    {
        Person person = new PersonDAO();
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", uid);

        DBCursor cursor = collection.find(searchQuery);
        if(cursor.hasNext()) {
            DBObject db = cursor.next();

            person.setFirstName(db.get("firstName").toString());
            person.setLastName(db.get("lastName").toString());
            person.setAge(Integer.parseInt(db.get("age").toString()));
            person.setUsername(db.get("username").toString());
            person.setStatus(db.get("status").toString());
            DBObject db2 = (DBObject) db.get("hidden");
            person.setHideAge(Boolean.valueOf(db2.get("hideAge").toString()));
            person.setHidePosts(Boolean.valueOf(db2.get("hidePosts").toString()));
            person.setHideFriends(Boolean.valueOf(db2.get("hideFriends").toString()));
            person.setHideStatus(Boolean.valueOf(db2.get("hideStatus").toString()));
            //String firstName = db.get("firstName").toString();
            //String firstName = db.get("firstName").toString();
        }
        return person;
    }

    private static Person printFriendInfo(String username)
    {
        collection = database.getCollection("Users");

        Person person = new PersonDAO();
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", username);

        DBCursor cursor = collection.find(searchQuery);
        if(cursor.hasNext()) {
            DBObject db = cursor.next();
            ObjectId id = (ObjectId) db.get("_id");
            person.setFirstName(db.get("firstName").toString());
            person.setLastName(db.get("lastName").toString());
            person.setAge(Integer.parseInt(db.get("age").toString()));
            person.setUsername(db.get("username").toString());
            person.setStatus(db.get("status").toString());
            person.setPicture(new ImageView("Images/defaultUserIcon.png"));

            DBObject db2 = (DBObject) db.get("hidden");
            person.setHideAge(Boolean.valueOf(db2.get("hideAge").toString()));
            person.setHidePosts(Boolean.valueOf(db2.get("hidePosts").toString()));
            person.setHideFriends(Boolean.valueOf(db2.get("hideFriends").toString()));
            person.setHideStatus(Boolean.valueOf(db2.get("hideStatus").toString()));
        }
        return person;
    }

    public static void updatePassword(String user, String newPassword)
    {
        collection = database.getCollection("Users");
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("password", new BasicDBObject()
                .append("enc", newPassword).append("key", user)));

        BasicDBObject searchQuery = new BasicDBObject().append("username", user);

        collection.update(searchQuery, newDocument);
    }

    public static ObservableList<Person> printAllFriends(Person person)
    {
        ObservableList<Person> friends = FXCollections.observableArrayList();

        collection = database.getCollection("Friendship");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("user", person.getUsername());

        DBCursor cursor = collection.find(searchQuery);
        while(cursor.hasNext()) {
            DBObject db = cursor.next();

            Person friend = new PersonDAO();
            friend = printFriendInfo(db.get("friend").toString());
            friends.add(friend);
        }

        collection = database.getCollection("Friendship");
        BasicDBObject searchFriends = new BasicDBObject();
        searchFriends.put("friend", person.getUsername());

        DBCursor cursor2 = collection.find(searchFriends);
        while(cursor2.hasNext()) {
            DBObject db = cursor2.next();
            Person friend = new PersonDAO();
            friend = printFriendInfo(db.get("user").toString());
            friends.add(friend);

        }
        return friends;
    }
    public static ObservableList<Person> printAllUsers(Person person)
    {
        ObservableList<Person> usersList = FXCollections.observableArrayList();

        collection = database.getCollection("Users");

        DBCursor cursor;
        cursor = collection.find();
        while(cursor.hasNext()) {
            DBObject db = cursor.next();
            Person user = new PersonDAO();
            user.setFirstName(db.get("firstName").toString());
            user.setLastName(db.get("lastName").toString());
            user.setUsername(db.get("username").toString());
            if(!person.getUsername().equals(user.getUsername()))
                if(!areTheyFriends(person, user))
                    usersList.add(user);
        }

        return usersList;
    }
    private static boolean areTheyFriends(Person user, Person friend)
    {
        collection = database.getCollection("Friendship");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("user", user.getUsername());
        searchQuery.put("friend", friend.getUsername());

        DBCursor cursor = collection.find(searchQuery);
        if(cursor.hasNext()) {
            return true;
        }

        BasicDBObject searchFriends = new BasicDBObject();
        searchFriends.put("user", friend.getUsername());
        searchFriends.put("friend", user.getUsername());

        cursor = collection.find(searchFriends);
        if(cursor.hasNext()) {
            return true;
        }

        return false;
    }

    public static void updateField(Person user, String field, String updateInfo)
    {
        collection = database.getCollection("Users");

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append(field, (updateInfo)));

        BasicDBObject searchQuery = new BasicDBObject().append("username", user.getUsername());

        collection.update(searchQuery, newDocument);
    }

    public static void updateField(Person user, String field, String innerField, String updateInfo)
    {
        collection = database.getCollection("Users");


        BasicDBObject newDocument = new BasicDBObject();
        //TODO: Overwrites other flags
        /*newDocument.put("$set", new BasicDBObject().append(field, new BasicDBObject()
                .append(innerField, updateInfo)));*/
        newDocument.append("$set", new BasicDBObject()
                .append(field, new BasicDBObject().append("hideAge", user.isHideAge())
                .append("hidePosts", user.isHidePosts())
                .append("hideFriends", user.isHideFriends())
                .append("hideStatus",user.isHideStatus())));

        BasicDBObject searchQuery = new BasicDBObject().append("username", user.getUsername());

        collection.update(searchQuery, newDocument);
    }

    public static void addFriend(Person user, Person friend)
    {
        collection = database.getCollection("Friendship");
        BasicDBObject document = new BasicDBObject();
        document.put("user", user.getUsername());
        document.put("friend", friend.getUsername());
        collection.insert(document);
    }

    public static void addPost(Person user, Post post)
    {
        collection = database.getCollection("Posts");
        BasicDBObject document = new BasicDBObject();
        document.put("date", post.getDate());
        document.put("user", user.getUsername());
        document.put("post", post.getText());
        collection.insert(document);
    }

    public static ObservableList<Post> printAllPosts(Person user)
    {
        ObservableList<Post> postsList = FXCollections.observableArrayList();

        collection = database.getCollection("Posts");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("user", user.getUsername());

        DBCursor cursor;
        cursor = collection.find(searchQuery);
        while(cursor.hasNext()) {
            DBObject db = cursor.next();
            Post post = new Post();
            post.setDate(db.get("date").toString());
            post.setText(db.get("post").toString());
            postsList.add(post);
        }
        return postsList;
    }

    public static void deletePost(Person user, Post post)
    {
        collection = database.getCollection("Posts");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("user", user.getUsername());
        searchQuery.put("post", post.getText());
        collection.remove(searchQuery);
    }

    public static void deleteFriend(Person user, Person friend)
    {
        collection = database.getCollection("Friendship");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("user", user.getUsername());
        searchQuery.put("friend", friend.getUsername());
        BasicDBObject searchQuery2 = new BasicDBObject();
        searchQuery2.put("user", friend.getUsername());
        searchQuery2.put("friend", user.getUsername());
        collection.remove(searchQuery);
        collection.remove(searchQuery2);
    }
}
