import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

public class mainTimer extends JFrame {
	
	private JButton dragon;
	private JButton baron;
	JLabel dLabel, bLabel, buffer;
	Timer dTimer, bTimer;
	
	//Constructor - initialising buttons/labels, setting up gui layout
	public mainTimer(){
		super("League Timers");
		setLayout(new FlowLayout());
		
		//Setting up button display
		Icon dragimg = new ImageIcon(getClass().getResource("dragon.png"));
		Icon baronimg = new ImageIcon(getClass().getResource("nashor.png"));
		
		dragon = new JButton("", dragimg);
		dragon.setToolTipText("Dragon");
		add(dragon);
		
		baron = new JButton("", baronimg);
		baron.setToolTipText("Baron");
		add(baron);
		
		//timer labels
		dLabel = new JLabel("Drag");
		dLabel.setFont(new Font("Serif", Font.BOLD, 52));
		bLabel = new JLabel("Baron");
		bLabel.setFont(new Font("Serif", Font.BOLD, 52));
		buffer = new JLabel("    ");
		buffer.setFont(new Font("Serif", Font.BOLD, 52));
		add(dLabel);
		add(buffer);
		add(bLabel);
		
		//Event handling
		HandlerClass handler = new HandlerClass();
		dragon.addActionListener(handler);
		baron.addActionListener(handler);	
	}

	
	//Setting timers starting depending on which is pressed
	private class HandlerClass implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (event.getSource()==dragon){
				
				// try to stop timer if running already
				try{
					dTimer.stop();
					startDragonTimer();
					
				} catch (java.lang.NullPointerException e){
					startDragonTimer();
				}
				
				
			} else if (event.getSource()==baron){
				
				// try to stop timer if running already
				try{
					bTimer.stop();	
					startBaronTimer();
					
				} catch (java.lang.NullPointerException e){
					startBaronTimer();
				}
				
			}
		}
		
		//Methods for setting up and starting timers anew
		private void startDragonTimer(){
			int count = 360;
			dClass tc = new dClass(count);
			dTimer = new Timer(1000, tc);
			dTimer.start();
		}
		
		private void startBaronTimer(){
			int count = 420;
			bClass tb = new bClass(count);
			bTimer = new Timer(1000, tb);
			bTimer.start();
		}
	}
	
	//each timer needs it's own actionlistener class because they have to update
	//their seperate labels display independently
	
	//drag timer counting and finish
	public class dClass implements ActionListener{
		int counter;
		
		public dClass(int counter){
			this.counter = counter;
		}
		
		public void actionPerformed(ActionEvent tc){
			
			if(counter>=1){
				int secs = counter % 60;
				String disSec = (secs < 10 ? "0" : "") + secs;
				dLabel.setText(counter/60 + ":" + disSec);
			} else {
				dTimer.stop();
				dLabel.setText("Done");
				Toolkit.getDefaultToolkit().beep();
			}
			counter--;
		
		}
	}
	
	//baron timer counting and finish
	public class bClass implements ActionListener{
		int counter;
		
		public bClass(int counter){
			this.counter = counter;
		}
		
		public void actionPerformed(ActionEvent tb){
			
			if(counter>=1){
				int secs = counter % 60;
				String disSec = (secs < 10 ? "0" : "") + secs;
				bLabel.setText(counter/60 + ":" + disSec);
			} else {
				bTimer.stop();
				bLabel.setText("Done");
				Toolkit.getDefaultToolkit().beep();
			}
			counter--;
		
		}
	}
	
	
	//main
	public static void main(String[] args) {
				
		mainTimer mainTimer = new mainTimer();
		mainTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainTimer.setSize(350, 256);
		mainTimer.setVisible(true);

 }
}