import requests
import json
import datetime
from os.path import dirname, join

# print(datetime.datetime.now())


url='https://sandbox.api.visa.com/visadirect/fundstransfer/v1/pullfundstransactions'

key = join(dirname(__file__), "key_510b3ab1-03c1-4e90-81f3-6f10d876a58d.pem")
cert = join(dirname(__file__), "cert.pem")

headers = { "content-type": "application/json",
			'accept': 'application/json'
}

user_id='4YGY6OCK9LJMYB64O2X821d0_K6FiBVMMm7ERR4QrwxytU1PQ'
password='VBYB1VYpqJ3qZctXEw6'

body={}

timeout=10


def getPayload(amount):
	date = datetime.datetime.now().strftime("%Y-%m-%dT%H:%M:%S")
	print(date)
	payload = json.loads(''' 
	{
	"acquirerCountryCode": "457",
	"acquiringBin": "408999",
	"amount": "'''+str(amount)+'''",
	"businessApplicationId": "AA",
	"cardAcceptor": {
	"address": {
	"country": "USA",
	"county": "081",
	"state": "CA",
	"zipCode": "94404"
	},
	"idCode": "ABCD1234ABCD123",
	"name": "Visa Inc. USA-Foster City",
	"terminalId": "ABCD1234"
	},
	"cavv": "0700100038238906000013405823891061668252",
	"foreignExchangeFeeTransaction": "11.99",
	"localTransactionDateTime": "'''+date+'''",
	"retrievalReferenceNumber": "330000550000",
	"senderCardExpiryDate": "2015-10",
	"senderCurrencyCode": "USD",
	"senderPrimaryAccountNumber": "4895142232120006",
	"surcharge": "11.99",
	"systemsTraceAuditNumber": "451001",
	"nationalReimbursementFee": "11.22",
	"cpsAuthorizationCharacteristicsIndicator": "Y",
	"addressVerificationData": {
	"street": "XYZ St",
	"postalCode": "12345"
	},
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
	},
	"riskAssessmentData": {
	"delegatedAuthenticationIndicator": true,
	"lowValueExemptionIndicator": true,
	"traExemptionIndicator": true,
	"trustedMerchantExemptionIndicator": true,
	"scpExemptionIndicator": true
	},
	"visaMerchantIdentifier": "73625198"
	}
	''')
	return payload

def pullMoney(amt):
	try:
		r = requests.post(url,
						cert = (cert,key),
						headers = headers,
						auth = (user_id, password),
						json=getPayload(amt),
						timeout=timeout)
		return r.text
	except Exception as e:
		print (e)

if __name__ == '__main__':
	r=pullMoney(1000)
	print(r)

