import java.io.IOException;

import org.junit.Test;

import pacemaker.GUI;

public class testGUI {
	@Test
	public void test() throws IOException {

		GUI gui = new GUI("");
		gui.setTextfield(200);
		gui.runGUI();
		System.in.read();

	}

}
