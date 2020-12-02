import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.sound.sampled.*;

public class ImagePanel extends JPanel implements ActionListener {
    Vector < String > list = new Vector < String > ();
    Image image;
    JButton b1, b2, b3, b4, b5, b6, quiz;
    JButton timer, mod;
    JPanel p;
    ImageIcon i1, i2, i3, i4, i5, i6, i7;
    Clip clip;
    Test test;
    BufferedImage pic;
    String teamName;
    Random r = new Random();
    static int score = 0;
    Q q = new Q();
    QTimer qt;
    String sec;

	

    public ImagePanel() {}
    public ImagePanel(Test test) {
        this.test = test;
        makeMusic();
		test.mod = "프로그래밍";
        makeSource();
    }
    void makeSource() {
        //이미지
        image = new ImageIcon("imgs/screen.png").getImage(); // Wallpaper
        i1 = new ImageIcon(getClass().getResource("imgs/testsend.gif"));
        i2 = new ImageIcon(getClass().getResource("imgs/play.png"));
        i3 = new ImageIcon(getClass().getResource("imgs/passed.png"));
        i4 = new ImageIcon(getClass().getResource("imgs/score-board.png"));
        i5 = new ImageIcon(getClass().getResource("imgs/ok.png"));
        i6 = new ImageIcon(getClass().getResource("imgs/cancel.png"));
		i7 = new ImageIcon(getClass().getResource("imgs/choose.png"));

        //버튼
        b1 = new JButton(i4); //스코어보드
        b2 = new JButton(i2); //음악 재생
        b3 = new JButton(i1); //모래시계
        b4 = new JButton(i5); //OK
        b5 = new JButton(i3); //Pass
        b6 = new JButton(i6); //EXIT
        timer = new JButton("Time");
		mod = new JButton(i7);

        quiz = new JButton("Quiz Start");

        //quiz font 설정
        quiz.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        quiz.setForeground(Color.WHITE);
        quiz.setHorizontalAlignment(JButton.CENTER);

        //타이머 font
        timer.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        timer.setForeground(Color.WHITE);
        timer.setHorizontalAlignment(JButton.CENTER);

        //이벤트 리스너 추가
        quiz.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
		mod.addActionListener(this);

        //JButton 외곽선 제거
        b1.setBorderPainted(false);
        b2.setBorderPainted(false);
        b3.setBorderPainted(false);
        b4.setBorderPainted(false);
        b5.setBorderPainted(false);
        b6.setBorderPainted(false);
		mod.setBorderPainted(false);
        timer.setBorderPainted(false);
        quiz.setBorderPainted(false);

        //클릭시 생기는 테두리 제거
        b1.setFocusPainted(false);
        b2.setFocusPainted(false);
        b3.setFocusPainted(false);
        b4.setFocusPainted(false);
        b5.setFocusPainted(false);
        b6.setFocusPainted(false);
		mod.setFocusPainted(false);
        timer.setFocusPainted(false);
        quiz.setFocusPainted(false);

        //절대위치 배치
        setLayout(null);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(timer);
        add(quiz);
		add(mod);

        setBackground(new Color(255, 255, 255));
        b1.setBackground(Color.white);
        b2.setBackground(Color.white);
        b3.setBackground(Color.white);
        b4.setBackground(Color.white);
        b5.setBackground(Color.white);
        b6.setBackground(Color.white);
		mod.setBackground(Color.white);
        timer.setBackground(Color.white);
        quiz.setBackground(Color.white);

        //투명하게
        b1.setOpaque(false);
        b2.setOpaque(false);
        b3.setOpaque(false);
        b4.setOpaque(false);
        b5.setOpaque(false);
        b6.setOpaque(false);
		mod.setOpaque(false);
        timer.setOpaque(false);
        quiz.setOpaque(false);
    }
    //이벤트
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        String imgFile = "";
        if (obj == quiz) { // 퀴즈
            try {
                sec = JOptionPane.showInputDialog("진행하실 시간을 입력해주세요~");
                qt = new QTimer(this);
                //quiz.setText("문자열이 긴 문장 테스트 내용");
                qt.start();
                start();
            } catch (Exception ie) {}

        } else if (obj == b1) { //스코어보드 누르면 창 뜨게~ (나중에 퀴즈의 스코어 변수를 넣을 예정)
            JOptionPane.showMessageDialog(null, "스코어보드 \n " + test.teamName + " : " + score, "Score Board",
                JOptionPane.QUESTION_MESSAGE,
                null);
        } else if (obj == b2) { // BGM 이벤트
            if (clip.isRunning() == false) { //bgm이 나오고 있지 않을때 시작~
                clip.start();
                imgFile = "imgs/stop.png";
                try {
                    pic = ImageIO.read(new File(imgFile));
                } catch (IOException e1) {
                    System.out.println("이미지 없음!");
                }
                i2.setImage(pic);
            } else if (clip.isRunning() == true) { //bgm이 나오고 있을때 스탑~
                clip.stop();
                imgFile = "imgs/play.png";
                try {
                    pic = ImageIO.read(new File(imgFile));
                } catch (IOException e2) {
                    System.out.println("이미지 없음!");
                }
                i2.setImage(pic);
            }
        } else if (obj == b6) {
            System.exit(-1); // 나가기 버튼
        } else if (obj == b4) {
			try{
				if (qt.sec1 != 0) {
					int size = list.size();
					if (size > 0) {
						int i = r.nextInt(size);
						String line = list.get(i);
						quiz.setText(line);
						score++;
						list.remove(i);
					} else {
						quiz.setText("문제 끝~");
					}
				} 
			}catch(NullPointerException ne){}
        } else if (obj == b5) {
			try{
				if (qt.sec1 != 0) {
					int size = list.size();
					if (size > 0) {
						int i = r.nextInt(size);
						String line = list.get(i);
						quiz.setText(line);
					}
				}
			}catch(NullPointerException ne){}
        }else if(obj== mod){
			String mod[] ={"프로그래밍","나라 이름"};
			
			Object sk=JOptionPane.showInputDialog(null,"퀴즈 종류를 선택해 주세요~","Select Mode",JOptionPane.QUESTION_MESSAGE, null, mod, mod[0]);
			if((String)sk==null){
			}else{
				JOptionPane.showMessageDialog(null, sk+"으로 퀴즈 내용이 바뀝니다.");
				if(sk=="나라 이름"){
					test.mod=(String)sk;
				}else{
					test.mod=(String)sk;
				}
			}
		}
		

    }
    // 오버라이딩
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false); //투명하게
        super.paint(g);
    }
    //BGM 생성
    void makeMusic() {
        try {
            File morlau = new File("music/morlau.wav");
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(morlau));
        }catch(LineUnavailableException ne){
			JOptionPane.showMessageDialog(null,"스피커가 연결돼었는지 확인해주세요.");
		}catch (Exception e) {}
    }
    void start() {
        FileReader fr = null;
        BufferedReader br = null;
        String line = "";
		if(test.mod=="프로그래밍"){	
			list.clear();
			try {
				fr = new FileReader("filtered.txt");
				br = new BufferedReader(fr);
				while ((line = br.readLine()) != null) {
					list.add(line);
				}
				inputTeamNum();
			} catch (FileNotFoundException fe) {
			} catch (IOException ie) {}
		}else if(test.mod=="나라 이름"){
			list.clear();
			try {
            fr = new FileReader("country.txt");
            br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            inputTeamNum();
			} catch (FileNotFoundException fe) {
			} catch (IOException ie) {}
		}
    }
    void inputTeamNum() {
        try {
            String line = test.teamName;
            if (line != null) line = line.trim();
            //System.out.println(teamName);

            showQ();
        } catch (NumberFormatException ne) {
            inputTeamNum();
        }
    }
    void showQ() {
        int size = list.size();
        if (size > 0) {
            int i = r.nextInt(size);
            quiz.setText(list.get(i));
        } else {
            quiz.setText("문제 끝~");
        }
    }
}