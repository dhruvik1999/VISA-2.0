import requests
import json
import datetime
from os.path import dirname, join


url='https://sandbox.api.visa.com/visadirect/fundstransfer/v1/pushfundstransactions'

key = join(dirname(__file__), "key_510b3ab1-03c1-4e90-81f3-6f10d876a58d.pem")
cert = join(dirname(__file__), "cert.pem")

headers = { "content-type": "application/json",
      'accept': 'application/json'
}

user_id='4YGY6OCK9LJMYB64O2X821d0_K6FiBVMMm7ERR4QrwxytU1PQ'
password='VBYB1VYpqJ3qZctXEw6'

body={}

timeout=10

def getPayload(amt,trId,cc,accbin,pacc,rnn,san):
  date = datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S")
  print(date)
  payload = json.loads('''
  {
  "acquirerCountryCode": "'''+cc+'''",
  "acquiringBin": "'''+accbin+'''",
  "amount": "'''+str(amt)+'''",
  "businessApplicationId": "AA",
  "cardAcceptor": {
  "address": {
  "country": "USA",
  "county": "San Mateo",
  "state": "CA",
  "zipCode": "94404"
  },
  "idCode": "CA-IDCode-77765",
  "name": "Visa Inc. USA-Foster City",
  "terminalId": "TID-9999"
  },
  "localTransactionDateTime": "'''+date+'''",
  "merchantCategoryCode": "6012",
  "pointOfServiceData": {
  "motoECIIndicator": "0",
  "panEntryMode": "90",
  "posConditionCode": "00"
  },
  "recipientName": "rohan",
  "recipientPrimaryAccountNumber": "'''+pacc+'''",
  "retrievalReferenceNumber": "'''+rnn+'''",
  "senderAccountNumber": "'''+san+'''",
  "senderAddress": "901 Metro Center Blvd",
  "senderCity": "Foster City",
  "senderCountryCode": "124",
  "senderName": "Mohammed Qasim",
  "senderReference": "",
  "senderStateCode": "CA",
  "sourceOfFundsCode": "05",
  "systemsTraceAuditNumber": "451018",
  "transactionCurrencyCode": "USD",
  "transactionIdentifier": "'''+trId+'''",
  "settlementServiceIndicator": "9",
  "colombiaNationalServiceData": {
  "countryCodeNationalService": "170",
  "nationalReimbursementFee": "20.00",
  "nationalNetMiscAmountType": "A",
  "nationalNetReimbursementFeeBaseAmount": "20.00",
  "nationalNetMiscAmount": "10.00",
  "addValueTaxReturn": "10.00",
  "taxAmountConsumption": "10.00",
  "addValueTaxAmount": "10.00",
  "costTransactionIndicator": "0",
  "emvTransactionIndicator": "1",
  "nationalChargebackReason": "11"
  }
  }
  ''')
  return payload

def pushMoney(amt=1000,trId="381228649430015",cc="840",accbin="408999",pacc="4957030420210496",rrn="412770451018",san="4653459515756154"):
  r = requests.post(url,
                  cert = (cert,key),
                  headers = headers,
                  auth = (user_id, password),
                  json=getPayload(amt,trId,cc,accbin,pacc,rrn,san),
                  timeout=timeout)

  print(r.text)
  return r.text

if __name__ == '__main__':
  pushMoney()