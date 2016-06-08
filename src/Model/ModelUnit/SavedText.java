package Model.ModelUnit;

import java.util.ArrayList;

/**
 * 하나의 탭이 담고 있는 텍스트에 관한 정보이다
 * @author Chanwoo park
 */
class SavedText
{
    protected ArrayList<String> lines;
    protected String filepath;
    protected SavedText() {
        lines = new ArrayList<String>();
        filepath = null;
    }

    /**
     *텍스트의 모든 줄의 끝 부분 공백을 지우는 메소드이다.
     */
    void deleteblank()
    {
        String line;
        ArrayList<String> newLines = new ArrayList<String>();
        for(int i=0;i<lines.size();i++)
        {
            line = lines.get(i);
            while(line.length()>0?line.charAt(line.length()-1)==' ':false)
            {
                if(line.length()!=1)line = line.substring(0,line.length()-2);
                else line = "";
            }
            newLines.add(line);
        }
        lines = newLines;
    }

}

