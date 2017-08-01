package visitor;

/**
 * Created by av7 on 7/29/17.
 */
public interface Visitor {
    public void visit(IMEIInfo imeiInfo);
    public void visit(ContactInfo contactInfo);
    public void visit(LocationInfo locationInfo);
}
