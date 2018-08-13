package com.company;

public class Smartphone implements CallFunction,BrowserFunction {
    @Override
    public void browsing(String[] sites) {
        for (String site:sites ) {
            boolean isValidSite=true;
            for (int i = 0; i < site.length(); i++) {
                if (Character.isDigit(site.charAt(i))){
                    isValidSite=false;
                    break;
                }
            }
            if (isValidSite){
                System.out.printf("Browsing: %s!"+System.lineSeparator(),site);
            } else{
                System.out.println("Invalid URL!");
            }
        }
    }

    @Override
    public void calling(String[] numbers) {
        for (String number:numbers ) {
            boolean isValidNumber=true;
            for (int i = 0; i <number.length(); i++) {
                if (!Character.isDigit(number.charAt(i))){
                    isValidNumber=false;
                    break;
                }
            }
            if (isValidNumber){
                System.out.printf("Calling... %s"+System.lineSeparator(),number);
            } else{
                System.out.println("Invalid number!");
            }
        }
    }
}
