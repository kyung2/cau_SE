package Model;
import java.io.*;
/**
 * Created by User on 2016-05-15.
 */
public class ModelUsingFile extends Model {
    static class SavedTextCanFileRR extends Model.SavedText
    {
        void ReadFromOuter(String s) throws IOException
        {
            File aFile = new File(s);
            FileReader fileReader = new FileReader(aFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while((line=reader.readLine())!=null) {
                lines.add(line);
            }
            reader.close();
        }
        void WriteFromOuter(String s) throws IOException
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(s));
            String line;
            for(int i=0;i<lines.size();i++) {
                writer.write(lines.get(i));
            }
            writer.close();
        }
        SavedTextCanFileRR()
        {
            super();
        }
    }

    ModelUsingFile()
    {
        super();
        SavedTextCanFileRR[] s = {new SavedTextCanFileRR(), new SavedTextCanFileRR()};
        textLinking(s);
    }
}
