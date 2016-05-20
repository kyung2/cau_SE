package Model;

import java.io.*;

/**
 * Created by User on 2016-05-19.
 */
public class ModelUnitUsingFile extends ModelUnit {
    static class SavedTextCanFileRR extends SavedText
    {
        public void ReadFromOuter(String s) throws IOException {
            File aFile = new File(s);
            FileReader fileReader = new FileReader(aFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        }
        public void WriteFromOuter(String s) throws IOException {
            BufferedWriter writer = new BufferedWriter(new FileWriter(s));
            String line;
            for(int i=0;i<lines.size();i++) {
                writer.write(lines.get(i));
            }
            writer.close();
        }

    }
    ModelUnitUsingFile()
    {
        super();
        SavedTextCanFileRR[] s = {new SavedTextCanFileRR(), new SavedTextCanFileRR()};
        codes = s;
    }
}
