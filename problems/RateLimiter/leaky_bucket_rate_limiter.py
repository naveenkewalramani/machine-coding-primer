class LeakyBucketRateLimiter():
    def __init__(self, capacity, leakRate):
        self.bucketSize = 0
        self.capacity = capacity
        self.leakRate = leakRate
        self.lastUpdateAt = 0
    
    def addRequest(self, noOfRequest, time):
        leakCount = (time - self.lastUpdateAt) * self.leakRate
        self.bucketSize = max(self.bucketSize - leakCount, 0)
        self.lastUpdateAt = time
        if self.bucketSize + noOfRequest > self.capacity:
            print("Cannot take",noOfRequest, "at",  time)
        else:
            self.bucketSize += noOfRequest
            print("Can take", noOfRequest, "at" , time , "current size" , self.bucketSize)
        
obj = LeakyBucketRateLimiter(5, 2)
obj.addRequest(2, 1)
obj.addRequest(2, 1)
obj.addRequest(2, 1)
obj.addRequest(2, 1)
obj.addRequest(2, 1)
obj.addRequest(1, 1)
obj.addRequest(1, 1)
obj.addRequest(2, 2)
obj.addRequest(2, 2)
obj.addRequest(2, 2)
obj.addRequest(2, 2)
obj.addRequest(2, 2)




