http GET http://localhost:8083/vouchers

http POST http://localhost:8083/vouchers userId=1 bikeId=1

http POST http://localhost:8083/vouchers userId=2 bikeId=2

http POST http://localhost:8083/vouchers userId=2 bikeId=2 voucherCnt=2

http PATCH http://localhost:8083/vouchers userId=2 bikeId=2 voucherCnt=1

http GET http://localhost:8081/rentals

http POST http://localhost:8081/rentals id=1 voucherId=1 bikeId=1

http POST http://localhost:8081/rentals id=2 userId=1 voucherId=1 bikeId=1

http PATCH http://localhost:8081/rentals id=1 userId=1 voucherId=1 bikeId=1



pause