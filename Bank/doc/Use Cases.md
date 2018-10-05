# Use Cases

## A customer wants to open an account
1. The customer selects "New Account" from the menu
2. The bank prompts the user for their name and password
3. The bank prompts the user for the account type (checking or savings)
4. The bank creates the desired account and gives the customer a unique account number
5. The customer is returned to the main menu

## A customer wants to deposit money in an account
1. The customer selects "Deposit" from the menu
2. The bank prompts the user for the account number
3. The bank prompts the user for the amount
4. The amount is added to the given account
5. The bank displays a transaction receipt
6. The customer is returned to the main menu

### Variation: Customer enters an invalid account number in step 2
1. The bank displays an error message and continues with step 6

### Variation: Customer enters an amount less than or equal to zero in step 3
1. The bank displays a cancellation message and continues with step 6

## A customer wants to withdraw money from an account
1. The customer selects "Withdraw" from the menu
2. The bank prompts the user for the account number and password
3. The bank prompts the user for the amount
4. The amount is subtracted from the given account
5. The bank displays a transaction receipt
6. The customer is returned to the main menu

### Variation: Customer enters an invalid account number or password in step 2
1. The bank displays an error message and continues with step 6

### Variation: Customer requests more than the current balance in step 3
1. The bank displays an error message including the current balance, and returns to step 3

### Variation: Customer requests an amount less than or equal to zero in step 3
1. The bank displays a cancellation message and continues with step 6

## A customer wants to check their account balance
1. The customer selects "Balance" from the menu
2. The bank prompts the user for the account number and password
3. The bank displays the account balance
4. The customer is returned to the main menu

### Variation: Customer enters an invalid account number or password in step 2
1. The bank displays an error message and continues with step 4

## A customer wants to write a check on their checking account
1. The customer selects "Write Check" from the menu
2. The bank prompts the user for the account number and password
3. The bank prompts the user for the amount of the check, and a unique password to accompany the check
4. The bank creates the desired check and gives the customer a unique check number
5. The customer can give the check number and password to another customer as a payment
6. The customer is returned to the main menu

### Variation: Customer enters an invalid account number or password in step 2
1. The bank displays an error message and continues with step 6

## A customer wants to deposit a check to an account
1. The customer selects "Deposit Check" from the menu
2. The bank prompts the user for the account number
3. The bank prompts the user for the check number and password
4. The amount of the check is withdrawn from the check writer's account
5. The check number is recorded as no longer usable
6. The amount of the check is added to the customer's account
7. The bank displays a transaction receipt
8. The customer is returned to the main menu

### Variation: Customer enters an invalid account number in step 2
1. The bank displays an error message and continues with step 8

### Variation: Customer enters an invalid (or unusable) check number or password in step 3
1. The bank displays an error message and continues with step 8

### Variation: Check writer's account balance is insufficient to withdraw the check in step 4
1. The bank displays an error message
2. The bank subtracts an "Insufficient Funds" fee from the check writer's account
3. The check number is recorded as no longer usable
4. Continue with step 8

## TODO: Produce monthly statements, add interest to savings

