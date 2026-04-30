// Test basic operation "abc" == "abc"
// This should not work if its the result of substring
class Main extends Lib
{
    public void main()
    {
        printBool("abcdef".substring(0,3) == "abcdef".substring(0,3));
    }
}

