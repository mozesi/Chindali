---
title: "How to Integrate your e-commerce system with the National Bank of Malawi Online Payment Gateway."
seoTitle: "Integrate your e-commerce system with the National Bank of Malawi"
seoDescription: "How to Integrate your e-commerce system with the National Bank of Malawi Online Payment Gateway."
datePublished: Fri Dec 24 2021 15:14:38 GMT+0000 (Coordinated Universal Time)
cuid: ckxkjbnhr017usqs11yvh434b
slug: how-to-integrate-your-e-commerce-system-with-the-national-bank-of-malawi-online-payment-gateway
cover: https://cdn.hashnode.com/res/hashnode/image/unsplash/OqtafYT5kTw/upload/v1640361275093/eAtIliH9N.jpeg
tags: javascript, php, ecommerce, business-and-finance

---

Are you a Malawian business? Do you wish to run an international business and you are worried as to how you can receive payments? Do you experience payment issues when exporting goods and services? Do you want your customers to pay for goods and services from the comfort of their homes? Does your business require advance payment? All you need is to 
1. Register for the API with National Bank Of Malawi.
2. Integrate your e-commence site with the API.

In this article, I will demonstrate how you can integrate your e-commence site with the National Bank of Malawi (NBM) payment gateway using the hosted checkout model to respond to either of the aforementioned use cases. Primarily, the hosted checkout allows a merchant/business to collect payments from a payer (a customer) through an interaction hosted and displayed by the payment Gateway. 
 
Below is  an illustration of the information flow of the hosted checkout session.
![workFlowHostedCheckout.png](https://cdn.hashnode.com/res/hashnode/image/upload/v1638164345453/TQU38BL7b.png) 

1.The payer initiates the payment process for goods and services at your shop site.
 
2.The payer enters the required information, and clicks "Pay".

3.If the payment is successful, the payer can obtain the payment details from one of these sources: the Payment Gateway-hosted receipt or your shop site.

For simplicity, I will simulate a simple case where a user/payer wants to pay a merchant. We will need two pages for this purpose, an interface to allow a user to enter the amount to be paid and to select a currency for the payment. The second page will handle the actual request of the checkout session through the gateway and then the payer will be presented with the payment interface from  the gateway.

# **1. Create a folder and the files for the project.**#
In your web server, create a directory and name it **Ecommerce**. We will need two files for this use case. We will need a page **index.php** that will provide an interface to allow a user to enter then desired amount they want to pay a merchant.

In the second file **requestSession.php**, we will need to request the checkout session and make the payment to the merchant through the payment gateway. An also allow the user to confirm their intended donation or cancel the transaction. Open the Ecommerce directory and create the two files namely **index.php** and **requestSession.php**.

# **2. Create a form for making a payment. **#
Open Ecommerce/index.php and create a form with two input fields. An Input field of the type **number** and a select  field of the type **text**, these will be used for inputing the amount and selecting the desired currency type respectively. The action of the form should be **requestSession.php** and the method should be **POST**.
```
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>payment</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/navbar-fixed/">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<meta name="theme-color" content="#7952b3">
    <link href="navbar-top-fixed.css" rel="stylesheet">
  </head>
  <body>
<nav class="navbar navbar-expand-md fixed-top"  style="background-color:#1e3d11;">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.php" style="color:white">Donate</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.php" style="color:white"><b>Pay</b></a>
        </li>
      </ul>
    </div>
  </div>
</nav>
   <br>
   <main class="container">
  <div class="bg-light p-5 rounded">                      
        <form class="col-md-4" action="requestSession.php" method="POST">
            <div  class="row mb-3">
                <label for="currency" class="form-label">Select Currency</label><br>
                    <select type="text" id="curreny" class="form-select" name="currency"required>
                        <option value="USD">USD</option>
                        <option selected value="MWK">MWK</option>
                    </select>
            </div>
            <div  class="row mb-4">
	            <label for="amount" class="form-label">Enter Amount</label><br>
                    <input type="number" class="form-control" id="amount" name="amount"  min="5" required>
            </div>             
            <div class="col-12">
			    <button class="btn btn-lg btn-primary" type="submit" role="button">Donate Now &raquo;</button>
            </div>
        </form>
  </div>
</main>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</html>
``` 
We need to retrieve the result indicator and success indicators. The result indicator will be returned by the gateway through the return URL which we will specify as we will be requesting the checkout session. Upon requesting the checkout session, the  gateway will return the success indicator which we will store through a session variable. (Note that this can be stored in your e-commerce system database.)
```
<?php
session_start();
$resultIndicator  = $_GET["resultIndicator"];
$successIndicator = $_SESSION["successIndicator"];
?>
``` 
A match of the successIndicator and the resultIndicator indicates that the payment has been successful.
```
<?php
       if(isset($resultIndicator)& isset($successIndicator)){
                              if($resultIndicator == $successIndicator){
                                  echo "The payment was succesfull";           
          }    
?>
``` 

# **3. Request the hosted checkout interaction**
Ensure that your merchant profile is enabled for the Hosted Checkout service.We need to request a checkout session using the Create Checkout Session operation. The request should include the payment and interaction data, as well as completion instructions. Open Ecommerce/requestSession.php. We need to define and initiate the payment and interaction data.

```
<?php
session_start();
if( $_POST["currency"] || $_POST["amount"] ) {   
$amount = $_POST['amount'];
$currency = $_POST['currency'];
$reference = uniqid();
$orderId   = uniqid();
$apiUsername ="apiUsername";
$merchant ="MerchatId";
``` 
Define the interaction.returnUrl.

The gateway will need the returnUrl to redirect the payer to the shop site. Possibly, when redirected the payer can presented with the payment status or any desired message including a receipt. Hence we must provide the interaction.returnUrl in the Create Checkout Session operation.

```
$returnUrl = "https://returnurl.com";
``` 
Generate and define the API Password

You should generate the API password in the Merchant Administration Portal. As earlier indicated, the merchant profile must be enabled for API, Batch, and "Use Password Authentication" privileges.
To access Merchant Administration, we need to login to the merchant portal. Administrator login credentials will be provided to you by NBM when you are successfully boarded onto the gateway. As an administrator, you should create a new operator with permissions to generate the API password.

Below are the steps for generating the API password in the portal

1. Log in to the Merchant portal with your administrator login credentials.
2. Navigate to Admin > Operators
3. Create a new operator by entering all the mandatory fields. Assign "May Configure Integration Settings" privilege to enable the operator to generate the API password.
4. Log out of Merchant Administration, and log in back into Merchant Administration as the new operator.
5. Navigate to Admin > Web Services API Integration Settings > Edit.
6. Click Generate New, and select the Enable Integration Access Via Password box.
7. This is the API password you will use to authenticate API requests made from your web server to the gateway.
 
Then in the code define and assign the apiPassword as below.
```
$apiPassword ="yourMerchantPortalGeneratedApiPassword";
``` 

Define the operation type which is the create checkout session
```
$apiOperation = "CREATE_CHECKOUT_SESSION";
``` 
Define the base url for requesting the checkout session.

```
$baseUrl =  "https://nbm.gateway.mastercard.com/api/nvp/version/49";
``` 
Then we need to Initiate a curl with the Create Checkout Session operation. 
The request should include the payment and the interaction data, as well as the completion instructions. For this case, the needed data has been defined above.

```
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,$baseUrl);
curl_setopt($ch, CURLOPT_RETURNTRANSFER,1);
curl_setopt($ch, CURLOPT_POST,1);
curl_setopt($ch, CURLOPT_POSTFIELDS,
"apiOperation=".$apiOperation."&apiPassword=".$apiPassword."&transaction.reference=".$reference."&interaction.returnUrl=".$returnUrl."&apiUsername=".$apiUsername."&merchant=".$merchant."&order.id=".$orderId."&order.amount=".$amount."&order.currency=".$currency);
$headers = array();
$headers[] = 'Content-Type: application/x-www-form-urlencoded';
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
$result = curl_exec($ch);
if(curl_errno($ch)){
	echo 'ERROR:'.curl_error($ch);
}
curl_close($ch);
``` 
Extract the session.id and the success indicator from the curl result.

We need to extract the session id from the curl result. A successful response to this operation will contain the session.id and the  success Indicator parameters. 
```
$sessionid = explode("=",explode("&",$result)[2])[1];
$successIndicator = explode("=",explode("&",$result)[5])[1];
``` 
You may be wondering why the success Indicator.

The gateway sends the result of the payment in a resultIndicator parameter, which is appended to the return url (interaction.returnUrl) used to return the payer to your shop site.
We can determine the success of the payment by comparing the resultIndicator parameter to the successIndicator parameter returned in the Create Checkout Session response. 

A match of the successIndicator and the resultIndicator indicates that the payment has been successful. Depending on you system use case, you can save the value returned in the successIndicator parameter on your shop system to verify the success or failure of the payment. For this task I will use sessions just to demonstrate the point as explained.
```
$_SESSION["successIndicator"] = $successIndicator;
$_SESSION["amount"] = $amount ;
$_SESSION["currency"] = $currency;
}?>
```
Then include the below code  for the page structure and the payment request form.
``` 
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <title>your name</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/navbar-fixed/">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <link href="navbar-top-fixed.css" rel="stylesheet">
  </head>
  <body>
	<nav class="navbar navbar-expand-md fixed-top"  style="background-color:#1e3d11;">
	  <div class="container-fluid">
		<a class="navbar-brand" href="#" style="color:white">Your site</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
		  <ul class="navbar-nav me-auto mb-2 mb-md-0">
			<li class="nav-item">
			  <a class="nav-link active" aria-current="page" href="#" style="color:white"><b>Your site</b></a>
			</li>
		  </ul>
		</div>
	  </div>
	</nav>
   <br>
   <main class="container">
  <div class="bg-light p-5 rounded">
    <p class="lead">Make your payment.</p>
	<div class="form-group">
			<div class="col-xs-8">
			<label for="amount">Confirm a payment of <?php echo $currency.$amount;?>. </label>
			<br>
            <br>
<!-- 
A button for starting the payment process.	
-->
			<input type="button" maxlength="5" maxheight="2" class="btn btn-primary btn-lg" value="Pay" onclick="Checkout.showPaymentPage();" />
			</div>
			</div>
	</div>
</main>  
``` 
We need to reference the checkout.js file from the gateway servers. This will place the Checkout object into global scope. In this case we will use version 49 of the checkout.

```
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 
 <script src="https://nbm.gateway.mastercard.com/checkout/version/49/checkout.js" data-error="errorCallback" data-cancel="cancelCallback"></script>
``` 
Holla!!  We need some JavaScript to finalize the process. we need a call to the Checkout.configure() function and then pass in a JSON object that includes the returned session.id and other payment request parameters to pass in the necessary data for the payment. 
```
        <script type="text/javascript">
            function errorCallback(error) {
                  console.log(JSON.stringify(error));
            }
            function cancelCallback() {
                  console.log('Payment cancelled');
            }
            Checkout.configure({
                merchant: 'MECHARNTID',
                order: {
                    id: '<?php echo $orderId;  ?>' ,
                    amount: '<?php echo $amount;  ?>' ,
                    currency: '<?php echo $currency;  ?>',
                    description: 'description',
                },
				session:{
					id:'<?php echo $sessionid; ?>'
				},
                 interaction:{
                    merchant: {
                        name: 'NAME ',
                        address: {
                            line1: 'YOUR ADDRESS',
                            line2: 'YOUR POST BOX'            
                        }    
                    }
                  }
            });
        </script>   
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</html>
``` 
Congratulations! That is all to it. Happy business.