public class Main {

    public static void main(String[] args) {
        Server srv01 = new Server();
        ITStaff krb = new ITStaff("Kasper");
        ITStaff tog = new ITStaff("Tobias");
        srv01.addObserver(krb);
        srv01.addObserver(tog);

        srv01.changeIsDown(true);
    }
}
