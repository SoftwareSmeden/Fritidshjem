package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Opg. 2 ---> Opret et array af VareData objekter.
        Vare[] vareArr = new Vare[8];
        Vare vare = new Vare();

        for (int i = 0; i < 8; i++) {
            vareArr[i] = new Vare();
        }

        int antalVarer;
        antalVarer = indlaesFraFil(vareArr);

        System.out.println("Opg. 4");
        udskrivArr(vareArr, antalVarer);
        skrivTilDatFil(vareArr, antalVarer);
        antalVarer = indlaesFraSerFil(vareArr);

        System.out.println("\nOpg. 6");
        udskrivArr(vareArr,antalVarer);

        System.out.println("\nOpg. 7 og 8");
        udskrivSamledePris(vareArr,antalVarer);
        totalSamlet(vareArr,antalVarer, vare);

        System.out.println("\nOpg. 9");
        faktura(vareArr,antalVarer, vare);
    }

    //Opg. 3 ---> Indlæs informationerne fra tekstfilen ”bestilling.txt” i VareData-objekter.
    public static int indlaesFraFil(Vare[] a) throws FileNotFoundException {

        File ordre = new File("bestilling.txt");
        Scanner input = new Scanner(ordre);

        int i = 0;
        while(input.hasNext()){
            a[i].setStk(input.nextInt());
            a[i].setNavn(input.next());
            a[i].setPris(input.nextDouble());
            i++;
        }
        input.close();

        return i;
    }

    //Opg. 4 ---> Lav en funktion udskriv, som modtager et array af VareData og som udskriver indholdet til skærm.
    public static void udskrivArr(Vare[] a, int antal){

        for (int i = 0; i < antal; i++) {
            System.out.format("%d %s %.2f \n", a[i].getStk(), a[i].getNavn(),a[i].getPris());
        }
    }

    //Opg. 5 ---> Skriv indholdet af arrayet med VareData til en datafil “varer.ser” med ObjectOutputStream.
    public static void skrivTilDatFil(Vare[] a2, int antal) throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("varer.ser"));

        for (int i = 0; i < antal; i++) {
            out.writeObject(a2[i]);
        }
            out.close();
    }

    //Opg. 6 ---> Indlæs fra datafil “varer.ser” med ObjectInputStream til et array af VareData objekter. Kald på funktionen udskriv.
    public static int indlaesFraSerFil(Vare[] a3) throws IOException, ClassNotFoundException {

        FileInputStream f = new FileInputStream("varer.ser");
        ObjectInputStream in = new ObjectInputStream(f);

        int i = 0;
        while(f.available() > 0){
            a3[i] = (Vare) in.readObject();
            i++;
        }
        in.close();

        return i;
    }

    //Opg. 7 ---> Lav funktion som beregner den samlede pris for hver vare med og uden rabat.
    public static void udskrivSamledePris(Vare[] a4, int antal){

        System.out.format("\n%-20s %-20s %-20s %s\n", "Varenavn", "Stk", "Samlet pris", "Samlet pris med rabat");

        for (int i = 0; i < antal; i++) {
            if (a4[i].getStk() > 10) {
                System.out.format("\n%-20s %-25d %-30.2f %.2f\n",a4[i].getNavn(),a4[i].getStk(),a4[i].getPris() * a4[i].getStk(),(a4[i].getPris() * a4[i].getStk() * 0.85));
            }else {
                System.out.format("\n%-20s %-25d %-31.2f\n",a4[i].getNavn(),a4[i].getStk(),a4[i].getPris() * a4[i].getStk());
            }
        }
    }

    //Opg. 8 ---> Lav funktion, som beregner saldoen for det samlede varekøb med og uden rabatter.
    public static void totalSamlet(Vare[] a5, int antal, Vare total){

        System.out.format("\n%53s %30s\n","Total pris", "Total pris med rabat");

        double rabatPrisTotal = 0.0;
        double prisTotal = 0.0;

        for (int i = 0; i < antal; i++) {
            if (a5[i].getStk() > 10){
                rabatPrisTotal += (a5[i].getPris() * 0.85) * a5[i].getStk();
            }
            prisTotal += a5[i].getPris() * a5[i].getStk();
        }

        total.setTotalPris(prisTotal);
        total.setTotalPrisRabat(rabatPrisTotal);

        System.out.format("\n%53.2f %30.2f",prisTotal, rabatPrisTotal);
    }

    //Opg. 9 ---> Lav funktion, som udskriver faktura hvor der er informationer om: hvilke varer der er købt, antallet af hver vare, prisen for hver vare med og uden rabat, og den samlede saldo med og uden rabat.
    //Fakturaen skal både udskrives til skærm og til en ny tekstfil ”faktura.txt”.
    public static void faktura(Vare[] a6, int antal, Vare total) throws IOException {

        //Opretter file:
        File nyFil = new File("Faktura.txt");
        if (nyFil.createNewFile()){
            System.out.println("Faktura oprettet");
        }

        //Printer fakturaen til skærm:
        System.out.println("________________________________________________FAKTURA__________________________________________________");
        System.out.format("\n%-20s %-20s %-20s %-20s %s\n", "Varenavn", "Stk", "Stk-pris", "Samlet pris", "Samlet pris med rabat");

        for (int i = 0; i < antal; i++) {
            if (a6[i].getStk() > 10) {
                System.out.format("\n%-20s %-23d %-22.2f %-30.2f %.2f\n",a6[i].getNavn(),a6[i].getStk(),a6[i].getPris(),a6[i].getPris() * a6[i].getStk(),(a6[i].getPris() * a6[i].getStk() * 0.85));
            }else {
                System.out.format("\n%-20s %-23d %-22.2f %-31.2f\n",a6[i].getNavn(),a6[i].getStk(),a6[i].getPris(),a6[i].getPris() * a6[i].getStk());
            }
        }

        System.out.println("\n\n\n__________________________________________________________________________________________________________");
        System.out.format("\n%75s %30s\n","Total pris", "Total pris med rabat");
        System.out.format("\n%75.2f %30.2f",total.getTotalPris(), total.getTotalPrisRabat());

        //Udskriver fakturaen til .txt fil:
        PrintWriter ud = new PrintWriter(new BufferedWriter(new FileWriter("Faktura.txt")));

        ud.write("________________________________________________FAKTURA__________________________________________________\n");
        ud.write("Varenavn             Stk                  Stk-pris             Samlet pris          Samlet pris med rabat\n");

        for (int i = 0; i < antal; i++) {
            if (a6[i].getStk() > 10){
            ud.format("\n%-20s %-23d %-22.2f %-30.2f %.2f\n",a6[i].getNavn(),a6[i].getStk(),a6[i].getPris(),a6[i].getPris() * a6[i].getStk(),(a6[i].getPris() * a6[i].getStk() * 0.85));
            }else
            ud.format("\n%-20s %-23d %-22.2f %-31.2f\n",a6[i].getNavn(),a6[i].getStk(),a6[i].getPris(),a6[i].getPris() * a6[i].getStk());
        }

        ud.write("\n\n\n_________________________________________________________________________________________________________\n");
        ud.write("                                                                   Total pris        Total pris med rabat\n");
        ud.format("%77.2f %27.2f",total.getTotalPris(), total.getTotalPrisRabat());

        ud.close();
    }
}
