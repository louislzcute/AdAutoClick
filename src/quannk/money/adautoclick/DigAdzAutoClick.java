package quannk.money.adautoclick;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DigAdzAutoClick extends AutoClick {
	public DigAdzAutoClick() {
		super();
	}

	public static void main(String args[]) throws AWTException,
			HeadlessException, UnsupportedFlavorException, IOException {
		DigAdzAutoClick theAuto = new DigAdzAutoClick();
		// click quality ad

		theAuto.delay(1500);
		Color quality_adsColor = new Color(45, 204, 112);
		for (int times = 0; times < 20; times++) {
			theAuto.clickMouse(5, 400, InputEvent.BUTTON1_MASK);
			theAuto.delay(100);
			theAuto.pressAKey(KeyEvent.VK_END);
			theAuto.delay(500);
			Point p = findCoordinate(quality_adsColor, 210, 300);
			if (p == null)
				return;
			theAuto.clickMouse(p.x, p.y, InputEvent.BUTTON1_MASK);

			// click view add
			theAuto.delay(9000);
			theAuto.pressAKey(KeyEvent.VK_END);
			theAuto.delay(6000);
			p = findCoordinate(quality_adsColor, 900, theAuto.height
					- (1024 - 780));
			if (p == null)
				return;
			theAuto.clickMouse(p.x, p.y, InputEvent.BUTTON1_MASK);

			// capture capcha image
			theAuto.delay(25000);
			int correctCapcha = -1;

			int[] xHome = new int[] { 340, 390, 460, 520, 570 };
			int[] xCompany = new int[] { 310, 370, 440, 495, 550 };
			int[] x;
			if (theAuto.width == 1280)
				x = xCompany;
			else
				x = xHome;
			String[] url = new String[5];
			for (int i = 0; i < 5; i++) {
				theAuto.clickMouse(x[i], 100, InputEvent.BUTTON3_MASK);
				theAuto.delay(200);
				theAuto.pressAKey(KeyEvent.VK_P);
				theAuto.delay(100);
				url[i] = (String) Toolkit.getDefaultToolkit()
						.getSystemClipboard().getData(DataFlavor.stringFlavor);
				System.out.println(url[i]);
			}
			outerloop: for (int i = 0; i < 5; i++) {
				for (int j = 4; j > i; j--) {
					if (url[i].compareTo(url[j]) == 0) {
						correctCapcha = j;
						break outerloop;
					}
				}
			}
			// click the correct captcha
			theAuto.clickMouse(x[correctCapcha], 100, InputEvent.BUTTON1_MASK);
			theAuto.delay(2500);
			// click the close window button
			theAuto.clickMouse(460, 130, InputEvent.BUTTON1_MASK);
			// wait to comeback to main
			theAuto.delay(3000);
		}
	}
}