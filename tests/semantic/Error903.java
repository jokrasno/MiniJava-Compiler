// switch statement--duplicate default labels
class Main {
    public void main() {
        int x = 4;
        switch (x) {
        default:
            {}
            break;
        case 19+4:
            {}
            break;
        default:
            {}
            break;
        }
    }
}
