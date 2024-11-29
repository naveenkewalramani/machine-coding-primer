package callback;


public class DisplayMessageCallback implements CallbackInterface {


    @Override
    public String onAccept() {
        System.out.println("Request can be processed");
        return "";
    }

    @Override
    public String onReject() {
        System.out.println("Request cannot be processed");
        return "";
    }
}
