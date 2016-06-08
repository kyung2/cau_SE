package Model.ModelUnit;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Chanwoo Park
 * ModelUnit 클래스의 파일 입출력 역할 메소드를 담은 클래스이다.
 */
class ModelUnitFileIO {

    /**
     * i번째 텍스트를 filepath에 있는 파일의 내용을 가져와 대체한다.
     * filepath에 있는 파일을 열 수 없을 때 IOException 발생.
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param m ModelUnitData
     * @param i 0 left 1 right
     * @param filepath filepath
     * @throws IOException,IndexOutOfBoundsException
     */
    static public void open(String filepath, int i, ModelUnitData m) throws IOException, IndexOutOfBoundsException
    {
        ReadFromOuter(filepath,i,m);
        m.codes[i].filepath = filepath;
        m.groupNull();
    }


    /**
     * 매개변수로 받은 filepath에 위치한 파일에 i번째 텍스트의 내용을 덮어쓴다.
     * filepath에 있는 파일을 쓸 수 없을 때 IOException 발생.
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param m ModelUnitData
     * @param i 0 left 1 right
     * @param filepath filepath
     * @throws IOException,IndexOutOfBoundsException
     */
    static public void save(String filepath, int i, ModelUnitData m) throws IOException, IndexOutOfBoundsException
    {
        WriteFromOuter(filepath,i,m);
        m.codes[i].filepath = filepath;
    }

    /**
     * open 시 받았던 filepath에 위치한 파일에 i번째 텍스트의 내용을 덮어쓴다.
     * filepath에 있는 파일을 쓸 수 없을 때 IOException 발생.
     * i가 0 또는 1이 아닐 때 IndexOutOfBoundsException 발생.
     * @param m ModelUnitData
     * @param i 0 left 1 right
     * @throws IOException
     */
    static public void save(int i, ModelUnitData m) throws IOException
    {
        WriteFromOuter(m.codes[i].filepath,i,m);
    }

    /**
     * UTF-8
     * @param f 현재 열려 있는 파일
     * @return convertString(UTF-8)*/
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

    /*
     파일을 읽을 때, 빈 줄을 무시하기 위한 length 길이의 공백으로만 이루어진 줄 반환 메소드.
     개행을 인정하여야 한다는 의견에 따라 사용되지는 앟았다.
     */
    /*
    private String retEmptyString(int length)

     {
        String a;
        if((length/3)==0) a = new String("");
        else a = retEmptyString(length/3);
        if(length%3==0) return a+a+a;
        else if(length%3==1) return a+a+a+" ";
        else return a+a+a+"  ";
    }
    */


    /**
    * 파일을 쓰기위한 함수
    * @param i 0 left 1 right
    * @param m ModelUnitData
    * @param s string
    * @throws IOException
    */
    static private void WriteFromOuter(String s, int i, ModelUnitData m) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(s);
        OutputStreamWriter OutputStreamWriter = new OutputStreamWriter(fileOutputStream, "EUC-KR");
        BufferedWriter writer = new BufferedWriter(OutputStreamWriter);
        ArrayList<String> newLines = m.codes[i].lines;
        for(int k=0;k<newLines.size();k++) {
            writer.write(newLines.get(k));
            writer.newLine();
        }
        writer.close();
    }

    /**
     * 파일을 읽기위한 함수
     * @param i 0 left 1 right
     * @param m ModelUnitData
     * @param s string
     * @throws IOException
     */
    static private void ReadFromOuter(String s, int i, ModelUnitData m) throws IOException {
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
        m.codes[i].lines = newLines;
        reader.close();
    }

}
