package com.testing.university.util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {

    public static int getInt()   {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        try{
            number = sc.nextInt();
            if (number < 1 || number > Integer.MAX_VALUE){
                throw new Exception();
            }
        } catch (InputMismatchException e) {
            System.out.println(SchemaStrings.WRONG_NUMBER);
            return 0;
        } catch (Exception e){
            System.out.println(SchemaStrings.WRONG_NUMBER_OR_NOT_NUMBER);
            return 0;
        }
        return number;
    }
    
    public static String getString(){
        String result;
        Scanner sc = new Scanner(System.in);
        try{
            result = sc.next();
            
            if (result.length() < 1 || result == null || result.equals("")){
                throw new Exception();
            }
            
        } catch (Exception e) {
            System.out.println(SchemaStrings.WRONG_CHARACTER_OR_NOT_CHARACTER);
            return null;
        }
        return result;
    }
    
}
