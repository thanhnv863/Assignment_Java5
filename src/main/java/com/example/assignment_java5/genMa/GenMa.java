package com.example.assignment_java5.genMa;

import java.util.Random;

public class GenMa {

    public String maGen(){
        String maGen = "SSNVNH";
        int randomNumber = new Random().nextInt(100000); // Số ngẫu nhiên từ 0 đến 9999
        String invoiceNumber = maGen + randomNumber;
        return invoiceNumber;
    }
}
