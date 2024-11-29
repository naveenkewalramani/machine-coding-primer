public class LeakyBucket extends RateLimiter{

    private int leakRate ;
    private int capacity;
    private int bucketSize;
    private int lastUpdatedAt;

    LeakyBucket(int leakRate, int capacity){
        this.leakRate = leakRate;
        this.capacity = capacity;
        this.bucketSize = 0;
        this.lastUpdatedAt = 0;
    }

    @Override
    public boolean checkIfAllowed(int timeStamp) {
        int leakCount = (timeStamp - this.lastUpdatedAt) * this.leakRate;
        this.bucketSize = Math.max(this.bucketSize - leakCount, 0);
        this.lastUpdatedAt = timeStamp;
        if (this.bucketSize > this.capacity){
            return false;
        }else{
            this.bucketSize++;
        }
        return true;
    }
}
