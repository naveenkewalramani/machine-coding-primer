import collections

class SilidingWindow():
    def __init__(self, windowSize, maxRquests):
        self.queue = collections.deque()
        self.windowSize = windowSize
        self.maxRequests = maxRquests
    
    def apiCall(self, time):
        while(len(self.queue) !=0 and self.queue[0] <= time - self.windowSize):
            self.queue.popleft()
        if len(self.queue) < self.maxRequests:
            self.queue.append(time)
            print("Allowed", time)
        else:
            print("Forbidden", time)

obj = SilidingWindow(3, 5)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(1)
obj.apiCall(2)
obj.apiCall(2)
obj.apiCall(2)
obj.apiCall(4)
obj.apiCall(4)
obj.apiCall(4)
obj.apiCall(4)
obj.apiCall(4)

