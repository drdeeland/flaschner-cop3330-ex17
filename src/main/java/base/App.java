/*
 *  UCF COP3330 Summer 2021 Assignment 1 Solution
 *  Copyright 2021 Dillon Flaschner
 */

package base;

import java.util.Scanner;

public class App {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        App myApp = new App();

        int gender = myApp.genderInput();
        int alcoholIntake = myApp.alcoholInput();
        int weight = myApp.weightInput();
        int hours = myApp.hoursInput();

        float distributionRatio = myApp.getDistributionRatio(gender);

        double BAC = myApp.BACCalc(alcoholIntake, weight, hours, distributionRatio);

        String output = myApp.buildOutput(BAC);
        myApp.printOutput(output);
    }

    public int alcoholInput() {
        System.out.print("How many ounces of alcohol did you have? ");
        return checkIfInteger();
    }

    public int weightInput() {
        System.out.print("What is your weight in pounds? ");
        return checkIfInteger();
    }

    public int genderInput() {
        System.out.print("Enter a 1 if you are male or a 2 if you are female: ");
        int gender = checkIfInteger();
        if (checkValidGender(gender)) {
            return gender;
        } else {
            System.out.println("Please enter only a 1 or 2.");
            return genderInput();
        }
    }

    public boolean checkValidGender(int gender) {
        switch (gender) {
            case 1:

            case 2:
                return true;
        }
        return false;
    }

    public int hoursInput() {
        System.out.print("How many hours has it been since your last drink? ");
        return checkIfInteger();
    }

    public float getDistributionRatio(int gender) {
        switch (gender) {
            case 1:
                return 0.73f;

            case 2:
                return 0.66f;
        }
        return 0f;
    }

    public int checkIfInteger() {
        boolean check = false;
        while (!check) {
            check = input.hasNextInt();

            if (!check) {
                System.out.println("Please enter a numerical value.");
                input.nextLine();
            }
        }
        return input.nextInt();
    }

    public double BACCalc(int alcoholIntake, int weight, int hours, float distributionRatio) {
        return (alcoholIntake * 5.14 / weight * distributionRatio) - (.015 * hours);
    }

    public String buildOutput(double BAC) {
        String BACString = String.format("%.6f", BAC);
        String legal;

        if (BAC < 0.08) {
            legal = " ";
        } else {
            legal = " not ";
        }

        return "Your BAC is " + BACString + "\nIt is" + legal + "legal for you to drive.";
    }

    public void printOutput(String output) {
        System.out.print(output);
    }
}
