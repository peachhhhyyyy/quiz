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
					JOptionPane.showMessageDialog(null,"info. \nOK�� ������ 3�� �� ���۵˴ϴ�. \n�ߺ� ���۽� Ÿ�̸Ӱ� �Ҿ����� �� �ֽ��ϴ�.");
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
					imgP.timer.setText("�ð� ���� ���� �߰� X");
					imgP.quiz.setText("���� ����!!");
					break;
				}
			}
		} catch (InterruptedException ie) {}
    }
}