# Realtime-Transactions-Statistics
 Realtime Transactions Statistics - A SpringBoot Application for storing and fetching realtime transactions. The main use case for the API is to calculate realtime statistics for the last 60 seconds of transactions. 
The API has the following endpoints:

  - POST /transactions – called every time a transaction is made. It is also the
sole input of this rest API.
  - GET /statistics – returns the statistic based of the transactions of the last 60
seconds.
  - DELETE /transactions – deletes all transactions.
