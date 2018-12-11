
public interface DAO {

    public <T> void update(T t, String[] params);

    public <T> void delete(T t);

}
