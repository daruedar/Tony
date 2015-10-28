package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JPanel {
	JPanel[]paneles;
	JTextArea[]areas;
	JList listaInsumos;
	JList listaPrecios;
	JButton[] botonera;
	JFrame ventana;

	public View(){
		paneles= new JPanel[4];
		areas= new JTextArea[4];
		ventana=new JFrame("Titulo");
		
		paneles[0]= new JPanel();
		paneles[1]= new JPanel();
		paneles[2]= new JPanel();
		
		areas[0]= new JTextArea(6,1);
		areas[0].setVisible(false);
		
		
		paneles[1].setBackground(new Color(224,90,21));
		String[] nombres= new String[40];
		String[] precios= new String[40];
		for(int i=0;i<nombres.length;i++){
				nombres[i]="Insumoddddssssssssfs"+i;
				precios[i]="Precio"+i;
			}
		JList listaNombres=new JList(nombres);
		JList listaPrecios= new JList(precios);
		JTextArea area51= new JTextArea(18,4);
		area51.setBackground(new Color(224,90,21));
		JTextArea area50= new JTextArea(18,2);
		area50.setBackground(new Color(224,90,21));
		
		
		//paneles[1].add(pane1);
		//paneles[1].add(pane2);
		
		paneles[1].add(listaNombres);
		paneles[1].add(area50);
		paneles[1].add(listaPrecios);
		paneles[1].add(area51);

		
		
		paneles[0].setBackground(new Color(124,190,210));
		areas[1]= new JTextArea(15,1);
		areas[1].setBackground(new Color(124,190,210));
		areas[1].setEditable(false);
		paneles[0].add(areas[1]);
		JPanel inter= new JPanel();
		inter.setLayout(new BorderLayout());
		JTextArea area2= new JTextArea(40,1);
		area2.setBackground(new Color(124,190,210));
		area2.setEditable(false);
		JButton boto1= new JButton("Agregar");
		JButton boto2= new JButton("Editar");
		JButton boto3= new JButton("Eliminar");
		JPanel interno= new JPanel();
		interno.setLayout(new GridLayout(3,1));
		interno.add(boto1);
		interno.add(boto2);
		interno.add(boto3);
		inter.add(area2,BorderLayout.CENTER);
		inter.add(interno,BorderLayout.NORTH);
		//paneles[0].add(interno);

		paneles[0].add(inter);
		paneles[2].setBackground(new Color(14,10,210));
		areas[2]= new JTextArea(45,7);
		paneles[2].add(areas[2]);
		String[] sopas= new String[40];
		for(int i=0;i<sopas.length;i++){
			sopas[i]="SopaNo"+i;
			}
		JList platos= new JList(sopas);
		paneles[2].add(platos);
		areas[2].setBackground(new Color(14,10,210));
		areas[2].setEditable(false);

		JScrollPane barra=new JScrollPane(paneles[1]);
		//add(paneles[1]);
		add(barra);
		

add(paneles[0]);
add(paneles[2]);
//add(paneles[3]);
//add(dos);
ventana.setSize(650,380);
ventana.setLayout(new BorderLayout());
ventana.add(barra,BorderLayout.WEST);
ventana.add(this,BorderLayout.CENTER);


ventana.setVisible(true);
ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public static void main(String[] args){
View app= new View();
}
}
