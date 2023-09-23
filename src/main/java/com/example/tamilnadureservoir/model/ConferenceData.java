package com.example.tamilnadureservoir.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ConferenceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Request fields
    private String conferencecode;
    private String conferenceyear;
    private String bankname;
    private String remoteip;
    private String regno;
    private String candidatename;
    private String nameinreceipt;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String phone;
    private String mobile;
    private String email;
    private String foodtype;
    private String participanttype;
    private String practicetype;
    private String accompanymembers;
    private String paymentamount;
    private String ToWards;
    private String Allow80G;
    private String PanCardNo;
    private String hasgst;
    private String GSTReg;
    private String gstnumber;
    private String gstmobileno;
    private String gstemailid;
    private String inputcaption1;
    private String inputvalue1;
    private String inputcaption2;
    private String inputvalue2;
    private String inputcaption3;
    private String inputvalue3;
    private String inputcaption4;
    private String inputvalue4;
    private String inputcaption5;
    private String inputvalue5;

    // Response fields
    private String responseTransid;
    private String responseResultCode;
    private String responseResult;
    private String responseURL;

    // Constructors, getters, and setters

    // You can generate getters and setters for each field using your IDE or
    // manually.
}
