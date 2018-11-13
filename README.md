# ATM
Work in progress. ATM management service. There are only four withdrawable banknote denominations: 20 PLN, 50 PLN, 100 PLN, 200 PLN. ATM refill functionality implemented as well.

There are several principles:

- You cannot pay out more than the account balance
- You cannot pay out in unavailable banknotes
- The payout banknote selection logic is to pay out as few banknotes as possible