package interfaces;

public interface IGrid extends IControl{
    void selectItem(String employee);
    boolean itemExists(String employee);
}
