* API Rate Limiter
  * Leaky Bucket
  * Token Bucket Approach
    * User will define the number of token and refill rate
    * Each API request will try to get one token.
    * If token is achieved, the API request shall proceed.
    * If token is not achieved, the API request shall not proceed.
    * Bucket will be refill at constant refill rate with desired number of token
  * SlidingWindow
* Callback
  * OnAccept()
  * OnReject
