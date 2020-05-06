/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retofinalpoo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alan Franco
 */
public class Ventana extends JFrame implements ActionListener{
    
    private JButton atacar, magia, rescatar, contruir, vidajmas, vidajmenos;
    private JLabel texto;
    
    public int VIDA1 = 0;
    
    public Ventana(){
        inicio();
    }
    
    private void inicio(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,500);
        this.setTitle("Reto Final");
        this.getContentPane().setBackground(Color.green);
        this.setLayout(null);
        
        atacar = new JButton("Atacar");
        atacar.setBounds(25, 380, 100, 50);
        atacar.setBackground(Color.white);
        atacar.addActionListener(this);
        this.add(atacar);
        
        magia = new JButton("Magia");
        magia.setBounds(135, 380, 100, 50);
        magia.setBackground(Color.white);
        magia.addActionListener(this);
        this.add(magia);
        
        rescatar = new JButton("Rescatar");
        rescatar.setBounds(245, 380, 100, 50);
        rescatar.setBackground(Color.white);
        rescatar.addActionListener(this);
        this.add(rescatar);
        
        contruir = new JButton("Construir");
        contruir.setBounds(355, 380, 100, 50);
        contruir.setBackground(Color.white);
        contruir.addActionListener(this);
        this.add(contruir);
        
        texto = new JLabel("SAMPLE TEXT XD");
        texto.setBounds(25, 320, 735, 50);
        //texto.setBackground(Color.white);
        texto.setOpaque(true);
        this.add(texto);
        
        vidajmas = new JButton("Subir vida");
        vidajmas.setBounds(60, 20, 100, 50);
        vidajmas.setBackground(Color.white);
        vidajmas.addActionListener(this);
        this.add(vidajmas);
        
        vidajmenos = new JButton("Bajar vida");
        vidajmenos.setBounds(180, 20, 100, 50);
        vidajmenos.setBackground(Color.white);
        vidajmenos.addActionListener(this);
        this.add(vidajmenos);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if(e.getSource()==atacar){
            //JOptionPane.showMessageDialog(null,"Jugador atacara");
            texto.setText("JUGADOR ATACA");
        }
        
        if(e.getSource()==magia){
            //JOptionPane.showMessageDialog(null,"Jugador usara magia");
            texto.setText("JUGADOR USA MAGIA");
        }
        
        if(e.getSource()==rescatar){
            //JOptionPane.showMessageDialog(null,"Jugador intentara rescatar un hada");
            texto.setText("JUGADOR RESCATA HADA");
        }
        
        if(e.getSource()==contruir){
            //JOptionPane.showMessageDialog(null,"Jugador construira una casa para un hada");
            texto.setText("JUGADOR CONSTRUYE CASA");
        }
        
        if(e.getSource()==vidajmas){
            VIDA1 = VIDA1 - 10;
            repaint();
        }
        
        if(e.getSource()==vidajmenos){
            VIDA1 = VIDA1 + 10;
            repaint();
        }
    }
    
    public void paint(Graphics g){
        
        super.paint(g);
        //g.setColor(Color.black);
        g.fillRect(25, 100, 29, 240);
        g.setColor(Color.red);
        g.fillRect(29, 105+VIDA1, 21, 230-VIDA1);
    }
}
