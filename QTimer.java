import java.io.*;
import javax.swing.*;

public class QTimer extends Thread {
    int inter = 1;
    ImagePanel imgP;
    int sec1;

    Q q;
    QTimer(ImagePanel imgP) {
        this.imgP = imgP;
    }
    QTimer(Q q) {
        this.q = q;
    }
    public void run() {
		try {
			if(isAlive()==true)
					JOptionPane.showMessageDialog(null,"info. \nOK를 누르면 3초 뒤 시작됩니다. \n중복 시작시 타이머가 불안정할 수 있습니다.");
			imgP.timer.setText("3");
			Thread.sleep(1000);
			imgP.timer.setText("2");
			Thread.sleep(1000);
			imgP.timer.setText("1");
			Thread.sleep(1000);
			imgP.timer.setText("Go");
			sec1 = Integer.parseInt(imgP.sec);
			sec1 +=1;
			while (true) {
				//sec1 = Integer.parseInt(imgP.sec);
				Thread.sleep(1000);
				sec1--;
				imgP.sec = String.valueOf(sec1);
				if (sec1 % inter == 0) imgP.timer.setText(imgP.sec);
				if (sec1 == 0){
					imgP.timer.setText("시간 종료 점수 추가 X");
					imgP.quiz.setText("퀴즈 종료!!");
					break;
				}
			}
		} catch (InterruptedException ie) {}
    }
}