package main;

import control.Control;

public class Main {

	public static void main(String[] args) {
		Control c=Control.instance();
		while(true) {
			c.openingPage();
		}
	}
}
