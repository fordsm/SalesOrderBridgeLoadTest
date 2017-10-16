
def kafkaPath = "/usr/local/Cellar/kafka/0.11.0.1/bin"

"$kafkaPath/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic CommerceShared".execute()

def topics = "$kafkaPath/kafka-topics --list --zookeeper localhost:2181".execute()
println(topics)

"$kafkaPath/kafka-console-producer --broker-list localhost:9092 --topic CommerceShared ${getOrderJson()}".execute()

def getOrderJson(){
    return  "{\n" +
            "  \"id\": \"${UUID.randomUUID()}\",\n" +
            "  \"eventType\": \"OrderSubmitted\",\n" +
            "  \"version\": 1,\n" +
            "  \"payload\": {\n" +
            "    \"id\": \"${UUID.randomUUID()}\",\n" +
            "    \"orderNumber\": \"5f0a69e7-7d3f-451a-b857-5d666bd4f187\",\n" +
            "    \"details\": {\n" +
            "      \"orderNumber\": \"5f0a69e7-7d3f-451a-b857-5d666bd4f187\",\n" +
            "      \"displayOrderId\": \"5F0A-69E7-7D3F\",\n" +
            "      \"header\": {\n" +
            "        \"orderStatus\": \"Submitted\",\n" +
            "        \"storeCode\": \"smallgroup\",\n" +
            "        \"createdDate\": \"2017-10-02T14:59:46Z\",\n" +
            "        \"locale\": \"en\",\n" +
            "        \"currency\": \"USD\",\n" +
            "        \"emailFrom\": \"admin@smallgroup.com\",\n" +
            "        \"totalItemCostBeforeTax\": 19.99,\n" +
            "        \"totalShippingCostBeforeTax\": 0,\n" +
            "        \"totalItemTaxes\": 1.85,\n" +
            "        \"totalShippingTaxes\": 0,\n" +
            "        \"totalTaxes\": 1.85,\n" +
            "        \"totalItemCostIncludingTax\": 21.84,\n" +
            "        \"totalShippingCostIncludingTax\": 0,\n" +
            "        \"grandTotal\": 21.84\n" +
            "      },\n" +
            "      \"appliedPromotions\": [],\n" +
            "      \"customerReference\": \"subscription-f84cb764-4df3-4a14-a155-9cb32d07f203-10\",\n" +
            "      \"customer\": {\n" +
            "        \"customerId\": \"05d40764-513d-491f-be3a-cfb276d87993\",\n" +
            "        \"customerUserName\": \"05d40764-513d-491f-be3a-cfb276d87993\",\n" +
            "        \"firstName\": \"Andrew\",\n" +
            "        \"lastName\": \"Grossholz\",\n" +
            "        \"email\": \"andrew.grossholz@broadmanholman.com\"\n" +
            "      },\n" +
            "      \"shipments\": [\n" +
            "        {\n" +
            "          \"shipmentId\": \"24860-1\",\n" +
            "          \"shipmentStatus\": \"SHIPPED\",\n" +
            "          \"discountAmount\": 0,\n" +
            "          \"shipmentType\": \"DIGITAL\",\n" +
            "          \"lineItems\": [\n" +
            "            {\n" +
            "              \"lineItemId\": \"7a113e1b-45e0-4bf4-a74b-dacb7a13fba7\",\n" +
            "              \"appliedPromotions\": [],\n" +
            "              \"productCode\": \"SG_Subs000002\",\n" +
            "              \"itemNumber\": \"005791337\",\n" +
            "              \"quantity\": 1,\n" +
            "              \"listUnitPrice\": 19.99,\n" +
            "              \"unitPrice\": 19.99,\n" +
            "              \"itemSubtotalPrice\": 19.99,\n" +
            "              \"amountBeforeTax\": 19.99,\n" +
            "              \"itemTaxes\": 1.85,\n" +
            "              \"amountIncludingTax\": 21.84,\n" +
            "              \"displayName\": \"SmallGroup Subscription\",\n" +
            "              \"options\": [\n" +
            "                {\n" +
            "                  \"key\": \"Subscription\",\n" +
            "                  \"value\": \"True\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"key\": \"BillingFrequency\",\n" +
            "                  \"value\": \"Monthly\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"key\": \"GroupSize\",\n" +
            "                  \"value\": \"SG_Single_Group\"\n" +
            "                }\n" +
            "              ],\n" +
            "              \"taxLines\": [\n" +
            "                {\n" +
            "                  \"jurisdictionId\": \"STATE\",\n" +
            "                  \"taxRegionId\": \"TENNESSEE\",\n" +
            "                  \"taxIsInclusive\": false,\n" +
            "                  \"taxName\": \"Sales and Use Tax\",\n" +
            "                  \"taxCode\": \"70100\",\n" +
            "                  \"taxAmount\": 1.4,\n" +
            "                  \"taxRate\": 0.07,\n" +
            "                  \"taxCalculationDate\": \"2017-10-02T14:59:46Z\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"jurisdictionId\": \"STATE\",\n" +
            "                  \"taxRegionId\": \"TENNESSEE\",\n" +
            "                  \"taxIsInclusive\": false,\n" +
            "                  \"taxName\": \"State Single Article Tax\",\n" +
            "                  \"taxCode\": \"70100\",\n" +
            "                  \"taxAmount\": 0,\n" +
            "                  \"taxRate\": 0,\n" +
            "                  \"taxCalculationDate\": \"2017-10-02T14:59:46Z\"\n" +
            "                },\n" +
            "                {\n" +
            "                  \"jurisdictionId\": \"COUNTY\",\n" +
            "                  \"taxRegionId\": \"DAVIDSON\",\n" +
            "                  \"taxIsInclusive\": false,\n" +
            "                  \"taxName\": \"Local Sales and Use Tax\",\n" +
            "                  \"taxCode\": \"70100\",\n" +
            "                  \"taxAmount\": 0.45,\n" +
            "                  \"taxRate\": 0.0225,\n" +
            "                  \"taxCalculationDate\": \"2017-10-02T14:59:46Z\"\n" +
            "                }\n" +
            "              ]\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ],\n" +
            "      \"payments\": [\n" +
            "        {\n" +
            "          \"paymentStatus\": \"SUCCESS\",\n" +
            "          \"paymentMethod\": {\n" +
            "            \"token\": \"8440000650380026\",\n" +
            "            \"cardholderName\": \"Josh Hartz\",\n" +
            "            \"expMonth\": 5,\n" +
            "            \"expYear\": 2021,\n" +
            "            \"authCode\": \"085942\",\n" +
            "            \"amount\": 19.99,\n" +
            "            \"address\": {\n" +
            "              \"id\": \"\",\n" +
            "              \"firstName\": \"Andrew\",\n" +
            "              \"lastName\": \"Grossholz\",\n" +
            "              \"line1\": \"1 LifeWay Plz\",\n" +
            "              \"line2\": \"\",\n" +
            "              \"city\": \"Nashvlle\",\n" +
            "              \"state\": \"TN\",\n" +
            "              \"zip\": \"37234\",\n" +
            "              \"country\": \"US\",\n" +
            "              \"phone\": \"\",\n" +
            "              \"addressType\": \"UNKNOWN\"\n" +
            "            },\n" +
            "            \"id\": \"f342c26f-f0e0-4f93-8c78-d30a28619e73\",\n" +
            "            \"cardType\": \"VISA\"\n" +
            "          }\n" +
            "        }\n" +
            "      ],\n" +
            "      \"giftCardPayments\": []\n" +
            "    }\n" +
            "  },\n" +
            "  \"timestamp\": \"2017-10-02T14:59:46.873Z\"\n" +
            "}"
}