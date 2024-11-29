import callback.CallbackInterface;

abstract public class RateLimiter {
    CallbackInterface callback;

    public boolean checkIfAllowed(int timeStamp){
        return true;
    }
}
