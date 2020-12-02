import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;

public class Test extends JFrame {
    Container cp;
    ImagePanel imgP;
    String teamName;
    Color b = new Color(0xFFFFFF);
    Q q = new Q();
	String mod;

    Test() {}
    void init() {
        teamName = JOptionPane.showInputDialog("팀 이름을 입력해주세요~"); //OK버튼 클릭시 입력창에 입력한 값이 teamNum에 리턴됨, cancle이나 창을 끄면 Null
        q.readOriginal();

        imgP = new ImagePanel(this); // 호출
        //init();
        setButton();
        setUI();
    }
    void setButton() {
        cp = getContentPane();
        cp.add(imgP);

    }
    void setUI() {
        // SetBounds(x,y,폭,높이)
        getContentPane().setBackground(b);
        imgP.b1.setBounds(280, 310, 140, 50); //스코어보드
        imgP.b2.setBounds(420, 310, 140, 50); //음악재생
        imgP.b3.setBounds(320, 0, 45, 45); //모래시계
        imgP.b4.setBounds(0, 310, 140, 50); //오케이버튼
        imgP.b5.setBounds(140, 310, 140, 50); //Pass
        imgP.b6.setBounds(560, 310, 140, 50); //Exit
        imgP.timer.setBounds(125, 60, 450, 60); //Pass
        imgP.quiz.setBounds(50, 135, 600, 100);
		imgP.mod.setBounds(10, 5, 50, 50); // mode
        setTitle("Team 3 Quiz Contest");
        setSize(700, 400);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        Test t = new Test();
        t.init();
    }
}