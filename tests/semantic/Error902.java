// switch statement--duplicate case labels
class Main {
    public void main() {
        int x = 4;
        switch (x) {
        case 23:
            {}
            break;
        case 19+4:
            {}
            break;
        }
    }
}
