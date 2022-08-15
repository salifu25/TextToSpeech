import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Audio {

    static void TTs(String words){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice;
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice!=null){
            voice.allocate();
        }
        try{
            voice.setRate(150);
            voice.setDurationStretch(2);
            voice.setPitch(86);
            voice.setVolume(3);
            voice.speak(words);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    static void CreateFile(String name){
        try {
            File obj = new File("C:\\Users\\Deezy\\Documents\\GitHub\\TextToSpeech\\src\\" + name);
            System.out.println(obj.getName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    static void WriteToFile(String nameOfCreatedFile,String text){
        try {
            FileWriter writer = new FileWriter(nameOfCreatedFile);
            writer.write(text);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args){


        try {
            // loading the pdf file
            String filename = "C:\\Users\\Deezy\\Documents\\GitHub\\TextToSpeech\\src\\nabs.pdf";
            File f = new File(filename);
            String parsedText;
            PDFParser parser = new PDFParser(new RandomAccessFile(f, "r"));
            parser.parse();

            // Extraction of text
            COSDocument cosDoc = parser.getDocument();
            PDFTextStripper pdfStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            String[] test = new String[]{parsedText};
            //System.out.println(parsedText);

            String list = "Monday\n" +
                    "banku with tilapia\n" +
                    "kenkey with fish\n" +
                    "Tuesday\n" +
                    "rice and stew\n" +
                    "beans and plantain";
            String[] mess = new String[]{list};
            ArrayList<String> menu = new ArrayList<>();
           Scanner scanner = new Scanner(parsedText);
           while (scanner.hasNextLine()){
               String data = scanner.nextLine();
               String[] sin = data.split("  ");
               for (String s:sin) {
                   menu.add(s);
               }
               //menu.add(data);
           }

            System.out.println(menu);

        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
            System.out.println("Error");
        }


    }
}
