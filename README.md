**Payments and Pandemic - VISA Hackathon**

**Team ID (Team Name): Hex Clan**

**Project Title: Money Anywhere**

**Repo Link: https://github.com/dhruvik1999/VISA-2.0**

# **Solution Overview**

**Project Description**

Although all governments aim to purge hard cash and replace it with electronic money in the long run, there are several businesses that have not adopted the cashless modes of payments yet. Even in many developed countries, cash is still the king. Customers have to often pay for goods and services using the exact change in hard cash, which they generally obtain from ATMs.

However, physical contact between individuals in public places such as in ATM queues can act as a vector of transmission for the Coronavirus during the COVID-19 pandemic. ATMs also generally dispense cash in fairly large denominations.

Our objective is to allow users to request money from other cash providers (individuals or businesses) via a mobile application that combines geolocation and the secure protocols of VISA APIs to reduce the dependence on ATMs for paper currency. This will also consequently lead to smaller ATM queues and reduce the interaction with ATMs and physical contact with other individuals during the COVID-19 pandemic. 

We propose a solution in the form of a mobile app, which is integrated with the Google Maps API to help users locate the nearest cash provider and request a desired amount of money. The cash provider, which can either be a merchant or another individual can agree to provide the requested amount.

Both parties then meet in person to complete the transaction. The user can scan the QR code and pay the requested amount using the VISA Direct APIs and then the user can collect the hard cash. 

The cash provider is rewarded with a small transaction fee which is charged from the requesting individual for the transaction. Merchants can also spread awareness about their goods and services by acting as cash providers and attracting new customers to their stores.
The application aims not to promote the use of hard cash but rather simplify and smoothen the flow of paper currency already in circulation while incentivizing the users to switch to cashless modes of payment. This app aims to bring hard cash transactions under the visa payment ecosystem. 

Further, this idea can be expanded to create a co-operative system where governments can dispense pension to the recipients through local businesses. VISA can tie up with local banks and government authorities for easy disbursement of pensions and other payments to the elderly as part of the Corporate Social Responsibility (CSR). Greater incentives like rewarding every 7th cashless transaction, with a 10% cashback on the total transaction amount with a maximum cap can also be introduced.

With this idea, people would be more inclined to get “rid” of cash, as they are getting profit from giving cash, which would benefit the cashless economy in the long run.

Demo Video Link: https://drive.google.com/fileDemoVideo.mp4/d/1u2kWQu_QzY7n4ezEgCwusItUspwrQe-p/view?usp=sharing

**Implementation**

* The app is built using ‘Android Studio’ in JAVA 
* For the database, ‘Firebase realtime database’ has been implemented and connected with the app backend.
* Used the following ‘VISA Direct FundsTransfer API’ for the real-time transaction:
* PullFundsTransactionsPOST API
* PushFundsTransactionsPOST API
* ‘Google Maps’ has been integrated into the app using the Google Maps API.
* Python Environment has been integrated into the app using the Python SDK ‘Chaquopy’.
* QR scanning and generating are integrated into Android Studio using the Java library ‘ZXing’.

**Market Potential**

The major market base that the app aims to target consists of  :
* Residents of areas with the very low density of ATMs.
* Individuals residing in regions with a high number of Coronavirus cases where public places like ATMs must be avoided
* Cardholders who often face a shortage of exact change in the form of paper currency to do transactions
* Merchants who want to attract extra traffic to their stores

The application has a huge market potential as no solution similar to this exists currently and a huge population still resides in areas where ATMs or other means to withdraw cash is not available within reasonable vicinity. Especially during the time like this of COVID-19, the application will prove to be a boon for the users. This app makes the life of cardholders quite easier and will be widely adapted. Also, the extra transaction fee which has to be borne by the customer is quite reasonable and in some cases even less than what the person has to pay normally in a transit to ATM and back. Further this application can be expanded to other geographical regions as the problem which this application solves is quite common everywhere including developed countries.

# **Tech**

**APIs Used**

* Visa Direct
* PullFundsTransactionsPOST API
* PushFundsTransactionsPOST API
* Google Map API 16.0.0


**Technology Stack**

* Android Studio 4.0.0
* Firebase Realtime Database 16.0.4
* Chaquopy 8.0
* ZXing Library 3.2.1


**IP & Third-Party Software**

* Android Studio 4.0.0
* Chaquopy 8.0
* Firebase Realtime Database 16.0.4
* ZXing Library 3.2.1
* Google Map API


Frameworks and Tools

* Android-Studio
* firebase
* Git
* Github
* Zxing
* Google-maps
* JSON
* gson
