import javax.crypto.SecretKey;

public class Password
{
    private String encrypted;
    private String key;

    public Password(String data, String key)
    {
        encrypted = data;
        this.key = key;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
