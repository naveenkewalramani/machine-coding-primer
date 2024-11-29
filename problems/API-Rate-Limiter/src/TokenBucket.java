public class TokenBucket extends RateLimiter {
    private int refillRateInSeconds = 0;
    private int refillTokenCount = 0;
    private int availableTokens = 0;
    private int lastRefillTime;

    TokenBucket(int refillRate, int refillTokens) {
        this.refillRateInSeconds = refillRate;
        this.refillTokenCount = refillTokens;
        this.lastRefillTime = 0;
        this.availableTokens = refillTokens;
    }

    private void occupyToken() {
        this.availableTokens--;
    }

    private boolean checkIfTokenAvailable() {
        return this.availableTokens > 0;
    }

    private void refillBucket(int timeStamp) {
        this.availableTokens =  Math.max(this.availableTokens, this.refillTokenCount);
        this.lastRefillTime = timeStamp;
    }

    private int getLastRefillTime(){
        return this.lastRefillTime;
    }

    @Override
    public boolean checkIfAllowed(int timeStamp) {
        int lastRefillTime = this.getLastRefillTime();
        if (timeStamp - lastRefillTime >= refillRateInSeconds){
            refillBucket(timeStamp);
        }
        if (checkIfTokenAvailable()){
            occupyToken();
            return true;
        }
        return false;
    }
}
