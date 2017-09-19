package visitor;

/**
 * Created by av7 on 7/29/17.
 */
public interface Visitor {
    public void visit(IMEIObserver imeiObserver);
    public void visit(ContactObserver contactObserver);
    public void visit(LocationObserver locationObserver);
}
