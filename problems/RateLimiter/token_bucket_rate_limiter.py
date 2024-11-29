class TokenBucketRateLimiter():
    def __init__(self, tokenCount, updateInterval):
        self.Bucket = tokenCount
        self.LastRefilled = 0
        self.UpdateInterval = updateInterval
        self.TokenToBeRefill = tokenCount
    
    def refillBucket(self, updatedAt):
        self.Bucket += self.TokenToBeRefill
        self.LastRefilled = updatedAt
    
    def utilizeToken(self):
        if self.Bucket<=0:
            return False
        self.Bucket -=1
        return True

    def apiCall(self, time):
        # first update the bucket
        if time - self.LastRefilled >= self.UpdateInterval:
            self.refillBucket(time)
        isUtilized = self.utilizeToken()
        if isUtilized == False:
            print("Cannot go forward at ", time, self.Bucket)
        else:
            print("Can go forward at ", time, self.Bucket)

obj = TokenBucketRateLimiter(3, 5)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(2)
obj.apiCall(2)
obj.apiCall(2)
obj.apiCall(3)
obj.apiCall(3)
obj.apiCall(5)
obj.apiCall(5)
obj.apiCall(11)
obj.apiCall(12)
obj.apiCall(16)
obj.apiCall(16)

        