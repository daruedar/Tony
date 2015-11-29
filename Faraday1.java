package controller;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vista.densidadElectronica;
/**Esta clase me permite calcular  la densidad de electrones de conduccion en una determinada cantidad de atomos,
 * sabiendo cada cuantos atomos proporcionan cuantos electrones
 * Funciona  junto con la parte grafia  una clase llamada densidadElectronica.java*/
public class Faraday  
	{	//Eventos even;
		densidadElectronica de;
		public Faraday(){
				//Eventos even;
				//even= new Eventos();
				System.out.println(GregorianCalendar.getInstance().getTime());
				de= new densidadElectronica();
				JFrame ventana= new JFrame();
				ventana.setSize(320,330);
				ventana.setVisible(true);
				ventana.add(de);
				ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			}
		public static void main(String[]args){
					Faraday app= new Faraday();	
				}
	}
