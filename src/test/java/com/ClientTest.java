package com;

//import com.client.FinancialManagementServicePort;
//import ukr.astelit.cim.model.customeracc.CustomerAccount;
//import ukr.astelit.cim.model.payment.CustomerPayment;
//import ukr.astelit.cim.model.payment.Money;
//import ukr.astelit.tm.fm.exchange.request.FinancialTransactionRequest;
//import ukr.astelit.tm.fm.ws.soap.FinancialManagementService;
//import ukr.astelit.tm.ws.commons.response.chain.ChainOperateResponse;
//
//import java.math.BigDecimal;
//import java.net.MalformedURLException;
//import java.util.Date;
//import java.util.List;
//
//public class ClientTest {
//
//    public static void main(String[] args) throws MalformedURLException {
///*
//        URL url = FinancialManagementService.class.getResource("/META-INF/FinancialManagement.wsdl");
//        QName qname = new QName("astelit.ukr:fm", "FinancialManagement");
//        Service service = Service.create(url, qname);
//
//        FinancialManagementService port = service.getPort(FinancialManagementService.class);
//*/
//
///*
//        FinancialManagement_Service management_service = new FinancialManagement_Service();
//
//        FinancialManagement managementService = management_service.getFinancialManagementPort();
//
//        BindingProvider bindingProvider = (BindingProvider) managementService;
//        Map<String, Object> requestContext = bindingProvider.getRequestContext();
//
//        String endpointAddress = "http://dev-test-tm-fm-1.dev.ict:8080/" + "astelit-fm-ws/fm-services/v2/fm";
//
//        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
//        requestContext.put(BindingProvider.USERNAME_PROPERTY, "iguana");
//        requestContext.put(BindingProvider.PASSWORD_PROPERTY, "iguana");
//        requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 10000);
//        requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 10000);
//
//        RegistryDetailsResponse registryDetails = managementService.getRegistryDetails(new RegistryDetailsRequest());
//        System.out.println(registryDetails);
//*/
//
///*
//        FinancialManagement management = new FinancialManagement();
//        FinancialManagementService financialManagementPort = management.getFinancialManagementPort();
//
//        BindingProvider bindingProvider = (BindingProvider) financialManagementPort;
//        Map<String, Object> requestContext = bindingProvider.getRequestContext();
//
//        String endpointAddress = "http://dev-test-tm-fm-1.dev.ict:8080/" + "astelit-fm-ws/fm-services/fm";
//
//        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
//        requestContext.put(BindingProvider.USERNAME_PROPERTY, "iguana");
//        requestContext.put(BindingProvider.PASSWORD_PROPERTY, "iguana");
//        requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 10000);
//        requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 10000);
//
//        FinancialTransactionRequest request = new FinancialTransactionRequest();
//        request.setChannel("A");
//        request.setProfileId("payment");
//
//        CustomerPayment payment = new CustomerPayment();
//        payment.setId("Z3xEP0");
//
//        Money money = new Money();
//        money.setAmount(new BigDecimal(1));
//        payment.setAmount(new Money());
//
//        payment.setPaymentDate(new XMLGregorianCalendarImpl());
//
//        List<CustomerAccount> paymentReceivedFor = payment.getPaymentReceivedFor();
//
//        CustomerAccount account = new CustomerAccount();
//
//        request.setCustomerPayment(payment);
//
//        GeneralOperateResult generalOperateResult = financialManagementPort.doPayment(request);
//
//        System.out.println(generalOperateResult.getTransactionId());
//*/
//
///*
//        FinancialManagement_Service management = new FinancialManagement_Service();
//        FinancialManagement financialManagementPort = management.getFinancialManagementPort();
//
//        BindingProvider bindingProvider = (BindingProvider) financialManagementPort;
//        Map<String, Object> requestContext = bindingProvider.getRequestContext();
//
//        String endpointAddress = "http://dev-test-tm-fm-1.dev.ict:8080/astelit-fm-ws/fm-services/v2/fm";
//
//        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointAddress);
//        requestContext.put(BindingProvider.USERNAME_PROPERTY, "iguana");
//        requestContext.put(BindingProvider.PASSWORD_PROPERTY, "iguana");
//        requestContext.put(BindingProviderProperties.REQUEST_TIMEOUT, 10000);
//        requestContext.put(BindingProviderProperties.CONNECT_TIMEOUT, 10000);
//
//        FinancialTransactionRequest request = new FinancialTransactionRequest();
//        request.setChannel("A");
//        request.setProfileId("payment");
//
//        CustomerPayment payment = new CustomerPayment();
//        payment.setId("Z3xEP0");
//
//        Money money = new Money();
//        money.setAmount(new BigDecimal(1));
//        payment.setAmount(new Money());
//
//        payment.setPaymentDate(new XMLGregorianCalendarImpl());
//
//        List<CustomerAccount> paymentReceivedFor = payment.getPaymentReceivedFor();
//
//        CustomerAccount account = new CustomerAccount();
//
//        request.setCustomerPayment(payment);
//
//        ChainOperateResponse chainOperateResponse = financialManagementPort.doPayment(request);
//
//        System.out.println(chainOperateResponse.getTransactionId());
//*/
//
//        FinancialManagementServicePort service = new FinancialManagementServicePort("http://dev-test-tm-fm-1.dev.ict:8080");
//
//        FinancialManagementService service1 = service.getService();
//
//        service.setUserNameProperty("iguana");
//        service.setPasswordProperty("iguana");
//
//        FinancialTransactionRequest request = new FinancialTransactionRequest();
//        request.setChannel("A");
//        request.setProfileId("payment");
//
//        CustomerPayment payment = new CustomerPayment();
//        payment.setId("Z3xEP0");
//
//        Money money = new Money();
//        money.setAmount(new BigDecimal(1));
//        payment.setAmount(new Money());
//
//        payment.setPaymentDate(new Date());
//
//        List<CustomerAccount> paymentReceivedFor = payment.getPaymentReceivedFor();
//
//        CustomerAccount account = new CustomerAccount();
//
//        request.setCustomerPayment(payment);
//
//        ChainOperateResponse chainOperateResponse = service1.doPayment(request);
//
//        System.out.println(chainOperateResponse);
//
//    }
//
//
//}
