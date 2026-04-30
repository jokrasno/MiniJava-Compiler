// Does this work in different classes
class A
{
    String x;

    public void setX()
    {
        x = "abc";
    }
}
class B
{
    String x;

    public void setX()
    {
        x = "abc";
    }
}
class Main extends Lib
{
    public void main()
    {
        A a = new A();
        a.setX();
        B b = new B();
        b.setX();
        printBool(a.x == b.x);
    }
}

