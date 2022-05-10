import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
public class Fasta {

private String dna = "";
private String header = ">";

    public Fasta() {
        this.dna = "ACTACT";
        this.header = ">g5835";
    }
    public Fasta(String dna, String header) {
        this.header = header;
        this.dna = dna;
        istdna(dna);
        istheader(header);
    }
    public Fasta(Fasta Fasta) {
        this.header = Fasta.header;
        this.dna = Fasta.dna;
    }
    public void reset() {
        dna = "ACTACT";
        header = ">g5835";
    }

    public String getHeader() {
        return header;
    }
    public String getdna() {
        return dna;
    }
    public Integer getdnalaenge(){
        return dna.length();
    }
    public String zusammenfassen(String dna,String dna2){
        String neuedna= dna + dna2;

        return neuedna;
    }
    public void teilsequenz(int x){
        int m=0;
        for (int k=0; k<getdnalaenge()-x;k+=x){
            System.out.println(dna.substring(k,k+x));
            m=k;
        }
        if(getdnalaenge()%x!=0){
            System.out.println(dna.substring(m+x));
        }
    }
    public boolean istdna(String dna) {
        boolean checkdna = true;
        boolean checkchar = false;

        for (int i = 0; i < dna.length(); ++i) {
            if ((dna.charAt(i) == 'C') || (dna.charAt(i) == 'A') || (dna.charAt(i) == 'T') || (dna.charAt(i) == 'G')) {
                checkchar = true;
            } else {
                checkchar = false;
            }
            if (checkchar == false) {
                checkdna = false;
                throw new IllegalSequenceException();
            }
        }
        return checkdna;
    }
    public boolean istheader(String header){
        boolean checkheader=false;
        if (header.charAt(0)== '>'){
            checkheader= true;
        }
        else{
            throw new IllegalHeaderException();
        }
        return checkheader;
    }
    public void plotteplot(String dna, String dna2){
        System.out.print(" ");
        for (int m=0; m< getdnalaenge();m++){
            System.out.print(" "+dna.charAt(m)+" ");
        }
        System.out.println();
        for (int i=0; i< dna2.length();i++){
            System.out.print(dna2.charAt(i));
            for (int k=0;k<dna.length();k++){
                if((dna2.charAt(i) != dna.charAt(k))){
                    System.out.print("   ");
                } else {
                    System.out.print(" * ");
                }
            }System.out.println(" ");
        }
    }
    public void schreiben(){
        File datei = new File("Testt.txt");
        try{
            FileWriter writer= new FileWriter(datei,false);
            writer.write(this.header+"\n");
            for(int i=0;i<getdnalaenge();i++){
                if(i%80==0){
                    writer.write("\n");
                }
                writer.write(dna.charAt(i));
            }
            writer.flush();
            writer.close();
        }catch(IOException e){
            System.out.println("ging nicht");
        }
    }
    public void lesen(String x){
        File datei = new File(x);
        Scanner scan=null;
        try{
            scan = new Scanner(datei);
        } catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        try{
            this.header =scan.nextLine();
            istheader(header);
        } catch(IllegalHeaderException e){
            System.out.println(e);
        }
        System.out.println(header);
        String insgesammt= "";
        while (scan.hasNext()){
            insgesammt = insgesammt + scan.nextLine();
        }
        try{
            istdna(insgesammt);
            this.dna=insgesammt;
        }catch (IllegalSequenceException e){
            System.out.println(e);
        }
        System.out.println(insgesammt);
    }

    public static void main(String[] args) {

        Fasta fasta1 = new Fasta();
        Fasta fasta2 = new Fasta("AACAAGCAAGGAAAAAGG", ">Proteine sind toll");
        Fasta fasta3 = new Fasta(fasta2);

        fasta2.schreiben();
        fasta2.lesen("Testt.txt");

    }
}
