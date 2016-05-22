package Model;

import java.io.*;

/**
 * Created by User on 2016-05-19.
 */
public class ModelUnitUsingFile extends ModelUnit {
    static class SavedTextCanFileRR extends SavedText
    {
        public void ReadFromOuter(String s) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(s);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        }
        public void WriteFromOuter(String s) throws IOException {
            FileOutputStream fileOutputStream = new FileOutputStream(s);
            OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            BufferedWriter writer = new BufferedWriter(OutputStreamWriter);
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
