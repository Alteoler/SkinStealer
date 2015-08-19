package com.averydenman.ss;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JProgressBar;

import java.awt.TextArea;

public class Core {

	private JFrame frmNoslowdownsSkinstealer;
	private JTextField input;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Core window = new Core();
					window.frmNoslowdownsSkinstealer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Core() {
		initialize();
	}

	private void initialize() {
		frmNoslowdownsSkinstealer = new JFrame();
		frmNoslowdownsSkinstealer.setResizable(false);
		frmNoslowdownsSkinstealer.setTitle("Alteoler's SkinStealer");
		frmNoslowdownsSkinstealer.setBounds(100, 100, 480, 480);
		frmNoslowdownsSkinstealer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNoslowdownsSkinstealer.getContentPane().setLayout(null);
		
		JLabel lblPlayerName = new JLabel("Username:");
		lblPlayerName.setBounds(10, 11, 79, 14);
		frmNoslowdownsSkinstealer.getContentPane().add(lblPlayerName);
		
		input = new JTextField();
		input.setBounds(85, 8, 107, 20);
		frmNoslowdownsSkinstealer.getContentPane().add(input);
		input.setColumns(10);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.setBounds(10, 407, 100, 23);
		frmNoslowdownsSkinstealer.getContentPane().add(btnDownload);
		
		JProgressBar status = new JProgressBar();
		status.setBounds(120, 407, 334, 24);
		frmNoslowdownsSkinstealer.getContentPane().add(status);
		
		TextArea console = new TextArea();
		console.setEditable(false);
		console.setBounds(10, 42, 444, 359);
		frmNoslowdownsSkinstealer.getContentPane().add(console);
		
		JLabel version = new JLabel("v1.2.0");
		version.setBounds(408, 11, 46, 14);
		frmNoslowdownsSkinstealer.getContentPane().add(version);
		
		btnDownload.addActionListener(new ActionListener() {
			

			
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) {
				String target = input.getText();
				status.setValue(0);
				
				if(target.equalsIgnoreCase("")){
					
					if(!(console.getText() == "")){
						console.setText(console.getText() + "Please enter a username to Steal their skin.");
					} else {
						console.setText(console.getText() + "\nPlease enter a username to Steal their skin.");
					}

					return;
				}
				
				if(console.getText() == ""){
					console.setText("Downloading skin of " + target + ", please wait.");
					
				} else {
					console.setText(console.getText() + "\n---------------------------------------------------");
					console.setText(console.getText() + "\nDownloading skin of " + target + ", please wait.");
				}
				status.setValue(status.getValue() + 20);

				
				URL website = null;
				try {
					website = new URL("http://skins.minecraft.net/MinecraftSkins/" + target + ".png");
				} catch (MalformedURLException e2) {
					e2.printStackTrace();
					console.setText(console.getText() + "\nDownload failed, please try again.");
					console.setText(console.getText() + "\nAre you connected to the internet?");
					return;
				}
				status.setValue(status.getValue() + 20);
				ReadableByteChannel rbc = null;
				try {
					rbc = Channels.newChannel(website.openStream());
					console.setText(console.getText() + "\nRetrieving file.");
				} catch (IOException e2) {
					
					e2.printStackTrace();
					console.setText(console.getText() + "\nDownload failed, please try again.");
					console.setText(console.getText() + "\nDid you enter the name correctly?");
					console.setText(console.getText() + "\nAre you connected to the internet?");
					return;
				}
				status.setValue(status.getValue() + 20);
				FileOutputStream fos = null;
				try {
					console.setText(console.getText() + "\nAttempting to save the file.");
					fos = new FileOutputStream(target + ".png");

				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					console.setText(console.getText() + "\nFile could not be saved.");
					return;
				}
				status.setValue(status.getValue() + 20);
				try {
					fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					console.setText(console.getText() + "\nDownload failed, please try again.");
					return;
				}
				status.setValue(status.getValue() + 20);
				console.setText(console.getText() + "\nSuccess. File has been saved.");
				
				
				
			}
		});

	}
}
