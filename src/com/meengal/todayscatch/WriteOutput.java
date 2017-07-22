package com.meengal.todayscatch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class WriteOutput {

	public WriteOutput() {

		try (FileWriter fw = new FileWriter("C:\\Users\\Annamalai\\Desktop\\Fish.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			Date date = new Date();
			out.println("\r\n============Fish Prices from Meengal.com on "+date.toString()+" =============");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeOutputTxt(String fishName, String fishPrice) {

		try (FileWriter fw = new FileWriter("C:\\Users\\Annamalai\\Desktop\\Fish.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {

			if (fishName != "No Fish Available") {
				String[] price = fishPrice.split(":");
				out.println(fishName + " is priced at" + price[1]);
			} else {
				out.println("No Fish is available right now, please check later");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
