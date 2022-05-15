# purchase-inquiry
http://localhost:8081/purchase-inquiry/api/swagger-ui.html#/purchase-inquiry-controller

sample req :
{
"amount": 10000,
"biller": "GOPAY",
"destNo": "0845678941",
"srcAccountNo": "123456789"
}

sample res :
{
"refNo": "14503897-ad89-42e2-bec0-e04f096e7554",
"destNo": "0845678941",
"destName": "Mira",
"amount": 10000,
"fee": 1000
}

sample error req :
{
"amount": 10000,
"biller": "OVO",
"destNo": "0845678941",
"srcAccountNo": "123456789"
}

sample error res :
{
"errCode": "3RD-01",
"errDesc": "Invalid Biller",
"refNo": "162f9ee0-7da0-4816-b8e4-359137bd3cd2"
}
