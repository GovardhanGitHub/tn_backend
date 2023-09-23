package com.example.tamilnadureservoir.controller;

import java.io.StringReader;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.example.tamilnadureservoir.dao.ConferenceDataRepository;
import com.example.tamilnadureservoir.model.ConferenceData;

@RestController
@RequestMapping("/soap")
public class SoapController {

    private final RestTemplate restTemplate;
    private final ConferenceDataRepository conferenceDataRepository;

    @Autowired
    public SoapController(RestTemplate restTemplate, ConferenceDataRepository conferenceDataRepository) {
        this.restTemplate = restTemplate;
        this.conferenceDataRepository = conferenceDataRepository;
    }

    @PostMapping("/insertConferenceData")
    public ResponseEntity<String> insertConferenceData(@RequestBody ConferenceData conferenceData) {
        try {
            // Save the conferenceData object to the database
            ConferenceData savedConferenceData = conferenceDataRepository.save(conferenceData);
            return new ResponseEntity<>("ConferenceData inserted with ID: " + savedConferenceData.getId(),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to insert ConferenceData: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllConferenceData")
    public ResponseEntity<List<ConferenceData>> getAllConferenceData() {
        List<ConferenceData> conferenceDataList = conferenceDataRepository.findAll();
        return new ResponseEntity<>(conferenceDataList, HttpStatus.OK);
    }

    @GetMapping("/getConferenceDataByPhone/{phone}")
    public ResponseEntity<Object> getConferenceDataByPhone(@PathVariable("phone") String phone) {
        Optional<ConferenceData> conferenceDataOptional = conferenceDataRepository.findByPhone(phone);

        if (conferenceDataOptional.isPresent()) {
            ConferenceData conferenceData = conferenceDataOptional.get();
            return new ResponseEntity<>(conferenceData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ConferenceData not found for phone: " + phone, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/callWebService", consumes = "application/soap+xml", produces = MediaType.APPLICATION_XML_VALUE)
    public String callWebService(
            @RequestBody String soapRequest,
            @RequestHeader("Content-Type") String contentType,
            @RequestHeader("SOAPAction") String soapAction) {

        // Log or use the 'Content-Type' and 'SOAPAction' headers as needed
        System.out.println("Content-Type: " + contentType);
        System.out.println("SOAPAction: " + soapAction);

        // Specify the SOAP action for your ASMX web service
        String soapActionValue = soapAction;
        String url = "https://clin.cmcvellore.ac.in/newconference/ConferencePay.asmx";

        try {
            // Create a DocumentBuilder to parse the SOAP request string
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document requestDoc = builder.parse(new InputSource(new StringReader(soapRequest)));

            // Create a DOMSource from the parsed SOAP request
            DOMSource requestSource = new DOMSource(requestDoc);

            // Create a DOMResult to capture the response
            DOMResult responseResult = new DOMResult();

            // Set the Content-Type header to specify the SOAP format
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_XML);

            // Set the SOAPAction header to specify the SOAP action
            headers.set("SOAPAction", soapActionValue);

            // Create a HttpEntity with the headers
            HttpEntity<DOMSource> httpEntity = new HttpEntity<>(requestSource, headers);

            // Send the SOAP request to the external ASMX web service
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class);

            // Extract the response XML from the ResponseEntity
            String responseXml = responseEntity.getBody();

            // Handle the SOAP response as needed
            return responseXml;
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace(); // You can log the exception details
            return null; // Return an appropriate response or handle differently
        }
    }

}
