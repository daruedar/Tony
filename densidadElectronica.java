package vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class densidadElectronica extends JPanel implements ActionListener{
	JTextArea enunciado;
	JTextArea label2,label3,label4,campo3;
	JTextField campo,campo2,campo8;
	JLabel rta,label4a;
	JPanel inf= new JPanel();
	JButton b1=new JButton("Calcular");
	JTextField salida= new JTextField(18);
	JPanel ac;
	JLabel uni= new JLabel("electrones/metro cubico");
	
	public densidadElectronica(){
		enunciado= new JTextArea(5,6);
		campo= new JTextField(3);
		campo.setToolTipText("1E14");
		campo2= new JTextField(3);
		campo2.setToolTipText("28");
		label3= new JTextArea(5,6);
		enunciado.setText("De cada");
		campo3= new JTextArea(5,6);
		campo8= new JTextField(3);
		campo8.setToolTipText("1");
		
		
		label2= new JTextArea(5,6);
		label2.setText("atomos de silicio puro");
		
		b1.addActionListener(this);
		label3= new JTextArea(5,6);
		label3.setText(", proporcionan ");
		//campo8.setText();
		
		label3= new JTextArea(5,6);
		label4a= new JLabel("Proporcionan");
		campo3.setText("electron de conduccion.¿Cual sera la densidad \nde electrones de conduccion n? ");
		inf.add(b1);
		inf.add(salida);
		rta= new JLabel("RESPUESTA");
		ac= new JPanel();
		ac.setAlignmentX(CENTER_ALIGNMENT);
		ac.add(uni);
		setLayout(new GridLayout(10,1));
		add(enunciado);
		add(campo);
		add(label2);
		add(campo2);
		add(label4a);
		add(campo8);
		add(campo3);
		add(label3);
		add(inf);
		add(ac);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){salida.setText(""+getDensidadDeElectrones());}
		
	}
	public double getDensidadAtomicaSilicio(){
		double densidadSilicio=2.33;//g/cm3
		
		double aux3=Double.parseDouble(campo8.getText());
		double factordeConversion=1000000;//cm3 to m3
		double masaAtomicaSilicio=28.09;//uma
		double avogadro=0.6023*(Math.pow(10, 24));
		double sal=densidadSilicio*factordeConversion*(1/masaAtomicaSilicio)*avogadro;
		//double cas=0;
		
		return sal;
	}
	
	
	
