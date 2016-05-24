package Model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public class ModelUnitRealizeUsingFile extends ModelUnitRealize {
    static class SavedTextCanFileRR extends SavedText
    {
        protected void ReadFromOuter(String s) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(s);
            String type = EncodingType(fileInputStream);
            System.out.println(type);
            fileInputStream.close();
            fileInputStream = new FileInputStream(s);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, type);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            ArrayList<String> newLines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
             //   if(line.compareTo(this.retEmptyString(line.length()))!=0)
                newLines.add(line);
            }
            this.WriteAll(newLines);
            reader.close();

        }
        protected void WriteFromOuter(String s) throws IOException { ///////////////////////
            FileOutputStream fileOutputStream = new FileOutputStream(s);
            OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "EUC-KR");
            BufferedWriter writer = new BufferedWriter(OutputStreamWriter);
            ArrayList<String> newLines = this.ReadAll();
            for(int i=0;i<newLines.size();i++) {
                writer.write(newLines.get(i));
                writer.newLine();
            }
            writer.close();


        }

        static private String EncodingType(FileInputStream f) throws IOException {
            byte[] BOM = new byte[4];
            f.read(BOM, 0, 4);

            if( (BOM[0] & 0xFF) == 0xEF && (BOM[1] & 0xFF) == 0xBB && (BOM[2] & 0xFF) == 0xBF )
                return new String("UTF-8");
            else if( (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF )
                return new String("UTF-16BE");
            else if( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE )
                return new String("UTF-16LE");
            else if( (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 &&
                    (BOM[0] & 0xFF) == 0xFE && (BOM[1] & 0xFF) == 0xFF )
                return new String("UTF-32BE");
            else if( (BOM[0] & 0xFF) == 0xFF && (BOM[1] & 0xFF) == 0xFE &&
                    (BOM[0] & 0xFF) == 0x00 && (BOM[1] & 0xFF) == 0x00 )
                return new String("UTF-32LE");
            else
                return new String("EUC-KR");
        }
        private String retEmptyString(int length)
        {
            String a;
            if((length/3)==0) a = new String("");
            else a = retEmptyString(length/3);
            if(length%3==0) return a+a+a;
            else if(length%3==1) return a+a+a+" ";
            else return a+a+a+"  ";
        }

    }
    ModelUnitRealizeUsingFile()
    {
        super();
        SavedTextCanFileRR[] s = {new SavedTextCanFileRR(), new SavedTextCanFileRR()};
        codes = s;
    }


    public void open(String s, int i) throws IOException, IndexOutOfBoundsException //open from string
    {
        codes[i].ReadFrom(s);
        groupNull();
    }
    public void save(String s, int i) throws IOException, IndexOutOfBoundsException //close to string
    {
        codes[i].WriteFrom(s);
    }
    public void save(int i) throws IOException {
        codes[i].WriteFrom();
    }

    public String filepath(int i) {
        return codes[i].filepath();
    }



}