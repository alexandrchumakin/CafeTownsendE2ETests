package controls;

public class Message extends BaseControl{
    private static final String template = "//p[contains(@class, 'message') and text()='%1$s']";

    public Message(String controlLabel) {
        super(new String[]{template}, controlLabel);
    }

}
