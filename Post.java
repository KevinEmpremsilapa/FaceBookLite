import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Post
{
    private String text;
    private String date;
    private final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd");
    public Post()
    {

    }

    public Post(String text)
    {
        this.text = text;
        Date now = new Date();

        date = formatter.format(now);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return date + "\n" + text;
    }
}
