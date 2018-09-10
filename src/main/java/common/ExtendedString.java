package common;

import org.apache.commons.codec.binary.Base64;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ExtendedString {
    private String user;
    private String password;

    public String getPassword() {
        return this.password;
    }

    public String getUser() {
        return this.user;
    }

    public ExtendedString() {
        this.user = Configurations.getValueByKey("loginUser");
        this.password = Configurations.getValueByKey("loginPW");
    }

    public String baseEncode() {
        return String.format("Basic %1$s", new String(Base64.encodeBase64(
                String.format("%1$s:%2$s", this.user, this.password).getBytes()
        )));
    }

    public String generateString() {
        return UUID.randomUUID().toString();
    }

    public String generateDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    public static String formatMessage(Exception ex){
        int index = ex.getMessage().indexOf("\n");
        index = index != -1 ? index : ex.getMessage().length();
        return ex.getClass().toString() + ": " + ex.getMessage().substring(0, index);
    }
}
