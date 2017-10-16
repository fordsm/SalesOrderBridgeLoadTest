import kafka.producer.KeyedMessage
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord

def loop = 1000
def kafkaProducer = getProducer()

for(int i = 0; i < loop; i++){
def order = getOrderJson()
ProducerRecord<Integer, String> data = new ProducerRecord<>("CommerceShared", order)
    kafkaProducer.send(data)
}
System.out.println("$loop messages sent successfully");
    producer.close();



def getProducer(){
    // create instance for properties to access producer configs
    Properties props = new Properties();

//Assign localhost id
    props.put("bootstrap.servers", "localhost:9092")
//Set acknowledgements for producer requests.
    props.put("acks", "all");
//Specify buffer size in config
    props.put("batch.size", 16384);
//Reduce the no of requests less than 0
    props.put("linger.ms", 1);
//The buffer.memory controls the total amount of memory available to the producer for buffering.
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = new KafkaProducer<String, String>(props);

    return producer
}

def getOrderJson(){
    return  "{" +
            "  \"id\": \"${UUID.randomUUID()}\"," +
            "  \"eventType\": \"OrderSubmitted\"," +
            "  \"version\": 1," +
            "  \"payload\": {" +
            "    \"id\": \"${UUID.randomUUID()}\"," +
            "    \"orderNumber\": \"5f0a69e7-7d3f-451a-b857-5d666bd4f187\"," +
            "    \"details\": {" +
            "      \"orderNumber\": \"5f0a69e7-7d3f-451a-b857-5d666bd4f187\"," +
            "      \"displayOrderId\": \"5F0A-69E7-7D3F\"," +
            "      \"header\": {" +
            "        \"orderStatus\": \"Submitted\"," +
            "        \"storeCode\": \"smallgroup\"," +
            "        \"createdDate\": \"2017-10-02T14:59:46Z\"," +
            "        \"locale\": \"en\"," +
            "        \"currency\": \"USD\"," +
            "        \"emailFrom\": \"admin@smallgroup.com\"," +
            "        \"totalItemCostBeforeTax\": 19.99," +
            "        \"totalShippingCostBeforeTax\": 0," +
            "        \"totalItemTaxes\": 1.85," +
            "        \"totalShippingTaxes\": 0," +
            "        \"totalTaxes\": 1.85," +
            "        \"totalItemCostIncludingTax\": 21.84," +
            "        \"totalShippingCostIncludingTax\": 0," +
            "        \"grandTotal\": 21.84" +
            "      }," +
            "      \"appliedPromotions\": []," +
            "      \"customerReference\": \"subscription-f84cb764-4df3-4a14-a155-9cb32d07f203-10\"," +
            "      \"customer\": {" +
            "        \"customerId\": \"05d40764-513d-491f-be3a-cfb276d87993\"," +
            "        \"customerUserName\": \"05d40764-513d-491f-be3a-cfb276d87993\"," +
            "        \"firstName\": \"Andrew\"," +
            "        \"lastName\": \"Grossholz\"," +
            "        \"email\": \"andrew.grossholz@broadmanholman.com\"" +
            "      }," +
            "      \"shipments\": [" +
            "        {" +
            "          \"shipmentId\": \"24860-1\"," +
            "          \"shipmentStatus\": \"SHIPPED\"," +
            "          \"discountAmount\": 0," +
            "          \"shipmentType\": \"DIGITAL\"," +
            "          \"lineItems\": [" +
            "            {" +
            "              \"lineItemId\": \"7a113e1b-45e0-4bf4-a74b-dacb7a13fba7\"," +
            "              \"appliedPromotions\": []," +
            "              \"productCode\": \"SG_Subs000002\"," +
            "              \"itemNumber\": \"005791337\"," +
            "              \"quantity\": 1," +
            "              \"listUnitPrice\": 19.99," +
            "              \"unitPrice\": 19.99," +
            "              \"itemSubtotalPrice\": 19.99," +
            "              \"amountBeforeTax\": 19.99," +
            "              \"itemTaxes\": 1.85," +
            "              \"amountIncludingTax\": 21.84," +
            "              \"displayName\": \"SmallGroup Subscription\"," +
            "              \"options\": [" +
            "                {" +
            "                  \"key\": \"Subscription\"," +
            "                  \"value\": \"True\"" +
            "                }," +
            "                {" +
            "                  \"key\": \"BillingFrequency\"," +
            "                  \"value\": \"Monthly\"" +
            "                }," +
            "                {" +
            "                  \"key\": \"GroupSize\"," +
            "                  \"value\": \"SG_Single_Group\"" +
            "                }" +
            "              ]," +
            "              \"taxLines\": [" +
            "                {" +
            "                  \"jurisdictionId\": \"STATE\"," +
            "                  \"taxRegionId\": \"TENNESSEE\"," +
            "                  \"taxIsInclusive\": false," +
            "                  \"taxName\": \"Sales and Use Tax\"," +
            "                  \"taxCode\": \"70100\"," +
            "                  \"taxAmount\": 1.4," +
            "                  \"taxRate\": 0.07," +
            "                  \"taxCalculationDate\": \"2017-10-02T14:59:46Z\"" +
            "                }," +
            "                {" +
            "                  \"jurisdictionId\": \"STATE\"," +
            "                  \"taxRegionId\": \"TENNESSEE\"," +
            "                  \"taxIsInclusive\": false," +
            "                  \"taxName\": \"State Single Article Tax\"," +
            "                  \"taxCode\": \"70100\"," +
            "                  \"taxAmount\": 0," +
            "                  \"taxRate\": 0," +
            "                  \"taxCalculationDate\": \"2017-10-02T14:59:46Z\"" +
            "                }," +
            "                {" +
            "                  \"jurisdictionId\": \"COUNTY\"," +
            "                  \"taxRegionId\": \"DAVIDSON\"," +
            "                  \"taxIsInclusive\": false," +
            "                  \"taxName\": \"Local Sales and Use Tax\"," +
            "                  \"taxCode\": \"70100\"," +
            "                  \"taxAmount\": 0.45," +
            "                  \"taxRate\": 0.0225," +
            "                  \"taxCalculationDate\": \"2017-10-02T14:59:46Z\"" +
            "                }" +
            "              ]" +
            "            }" +
            "          ]" +
            "        }" +
            "      ]," +
            "      \"payments\": [" +
            "        {" +
            "          \"paymentStatus\": \"SUCCESS\"," +
            "          \"paymentMethod\": {" +
            "            \"token\": \"8440000650380026\"," +
            "            \"cardholderName\": \"Josh Hartz\"," +
            "            \"expMonth\": 5," +
            "            \"expYear\": 2021," +
            "            \"authCode\": \"085942\"," +
            "            \"amount\": 19.99," +
            "            \"address\": {" +
            "              \"id\": \"\"," +
            "              \"firstName\": \"Andrew\"," +
            "              \"lastName\": \"Grossholz\"," +
            "              \"line1\": \"1 LifeWay Plz\"," +
            "              \"line2\": \"\"," +
            "              \"city\": \"Nashvlle\"," +
            "              \"state\": \"TN\"," +
            "              \"zip\": \"37234\"," +
            "              \"country\": \"US\"," +
            "              \"phone\": \"\"," +
            "              \"addressType\": \"UNKNOWN\"" +
            "            }," +
            "            \"id\": \"f342c26f-f0e0-4f93-8c78-d30a28619e73\"," +
            "            \"cardType\": \"VISA\"" +
            "          }" +
            "        }" +
            "      ]," +
            "      \"giftCardPayments\": []" +
            "    }" +
            "  }," +
            "  \"timestamp\": \"2017-10-02T14:59:46.873Z\"" +
            "}"
}