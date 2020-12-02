import java.util.*;
import java.io.*;

class Q {
    Vector < String > list = new Vector < String > ();
    TreeSet < String > ts = new TreeSet < String > ();
    Random r = new Random();
    BufferedReader brkey = new BufferedReader(new InputStreamReader(System.in));
    String teamName;
    int score;
    QTimer timer;
    Test t;

    void init() {
        readOriginal();
    }
    void readOriginal() {
        FileReader fr = null;
        BufferedReader br = null;
        File f = null;
        String line = null;
        try {
            fr = new FileReader("original.txt");
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                if (line != null) line = line.trim();
                if (line.length() != 0) {
                    ts.add(line);
                }
            }
            saveFile();
        } catch (FileNotFoundException fe) {
            //P.pln("파일이 존재하지 않습니다.");
        } catch (IOException ie) {} finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (IOException ie) {}
            f = new File("original.txt");
            f.renameTo(new File("original.bak"));
        }
    }
    void saveFile() {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("filtered.txt");
            pw = new PrintWriter(fw, true);
            for (String item: ts)
                pw.println(item);
            P.pln("filtered.txt에 문제 저장 완료");
        } catch (IOException ie) {} finally {
            try {
                if (pw != null) pw.close();
                if (fw != null) fw.close();
            } catch (IOException ie) {}
        }
    }
}