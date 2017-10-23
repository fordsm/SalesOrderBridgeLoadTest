//@Grab(group='org.apache.activemq',module = 'activemq-all', version='5.8.0')

import org.apache.activemq.ActiveMQConnectionFactory
import javax.jms.*

def loops = 5000
def brokerUrl = 'tcp://172.16.44.208:61616'
def queue = 'Q.KAFKA.T.AcknowledgeSalesOrder'

def messageTxt = "<?xml version=\"1.0\" encoding=\"US-ASCII\" standalone='no'?>\n" +
        "<!-- Oracle eXtensible Markup Language Gateway Server  -->\n" +
        "<AcknowledgeSalesOrder xmlns=\"http://www.openapplications.org/oagis/9\" releaseID=\"9_4\" versionID=\"9_4\">\n" +
        "  <ApplicationArea>\n" +
        "    <Sender>\n" +
        "      <LogicalID>www.lifeway.com</LogicalID>\n" +
        "      <ComponentID>SALESORDER</ComponentID>\n" +
        "      <TaskID>ACKNOWLEDGE</TaskID>\n" +
        "      <ReferenceID>PROD::</ReferenceID>\n" +
        "      <ConfirmationCode>0</ConfirmationCode>\n" +
        "      <AuthorizationID>APPS</AuthorizationID>\n" +
        "    </Sender>\n" +
        "  </ApplicationArea>\n" +
        "  <DataArea>\n" +
        "    <Acknowledge>\n" +
        "      <OriginalApplicationArea>\n" +
        "        <Sender>\n" +
        "          <LogicalID>lifeway</LogicalID>\n" +
        "          <ComponentID>Sales</ComponentID>\n" +
        "          <TaskID/>\n" +
        "          <ReferenceID/>\n" +
        "          <ConfirmationCode>0</ConfirmationCode>\n" +
        "          <AuthorizationID/>\n" +
        "        </Sender>\n" +
        "      </OriginalApplicationArea>\n" +
        "      <ResponseCriteria>\n" +
        "        <ResponseExpression actionCode=\"ACK\"/>\n" +
        "      </ResponseCriteria>\n" +
        "    </Acknowledge>\n" +
        "    <SalesOrder>\n" +
        "      <SalesOrderHeader>\n" +
        "        <DocumentID>\n" +
        "          <ID schemeName=\"OracleOrderId\"/>\n" +
        "        </DocumentID>\n" +
        "        <AlternateDocumentID>\n" +
        "          <ID schemeName=\"WSCOrderId\">10253060</ID>\n" +
        "        </AlternateDocumentID>\n" +
        "        <Status>\n" +
        "          <Description>NEW</Description>\n" +
        "        </Status>\n" +
        "        <SalesOrganizationIDs>\n" +
        "          <ID schemeName=\"Source\">LC</ID>\n" +
        "        </SalesOrganizationIDs>\n" +
        "      </SalesOrderHeader>\n" +
        "    </SalesOrder>\n" +
        "  </DataArea>\n" +
        "</AcknowledgeSalesOrder>"


new ActiveMQConnectionFactory(brokerURL: brokerUrl).createConnection().with {
    start()
    createSession(false, Session.AUTO_ACKNOWLEDGE).with {
//        def message = createTextMessage(reader.text)
        def message = createTextMessage(messageTxt)
        message.with {
            jMSDeliveryMode = DeliveryMode.NON_PERSISTENT
            jMSReplyTo = createTemporaryQueue()
            jMSCorrelationID = UUID.randomUUID().toString()
        }

        (1..loops).each {
//            new URL("http://localhost:8080/prometheus").getContent()
            createProducer().send(createQueue(queue), message)
        }
    }
    close()
}

//run with
//groovy jms.groovy < ~/Downloads/request.xml