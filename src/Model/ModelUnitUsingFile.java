package Model;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by User on 2016-05-19.
 */
public class ModelUnitUsingFile extends ModelUnit {
    static class SavedTextCanFileRR extends SavedText
    {
        public void ReadFromOuter(String s) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(s);
            String type = EncodingType(fileInputStream);
            System.out.println(type);
            fileInputStream.close();
            fileInputStream = new FileInputStream(s);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, type);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            lines = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
             //   if(line.compareTo(this.retEmptyString(line.length()))!=0)
                   lines.add(line);
            }
            reader.close();

        }
        public void WriteFromOuter(String s) throws IOException {
            FileOutputStream fileOutputStream = new FileOutputStream(s);
            OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "EUC-KR");
            BufferedWriter writer = new BufferedWriter(OutputStreamWriter);
            String line;
            for(int i=0;i<lines.size();i++) {
                writer.write(lines.get(i));
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
    ModelUnitUsingFile()
    {
        super();
        SavedTextCanFileRR[] s = {new SavedTextCanFileRR(), new SavedTextCanFileRR()};
        codes = s;
    }



}
