/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retofinalpoo;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author Alan Franco
 */
public class Ventana extends JFrame implements ActionListener{
    
    private Jugador jugador1 = new Jugador();
    private Ogro ogro1 = new Ogro();
    private Mago mago1 = new Mago();
    
    private boolean ogroDead = false, revivirOgro = false;
    
    private JButton atacar, bloquear, rescatar, contruir, atacarMago, atacarOgro;
    private JLabel texto,timer,hadasRes,contadorCasas,contadorError,casasHadas,poderMago,hInv;
    private JPanel mainPanel, panelJuego, panelRescatar, panelConstruir, panelVictoria, panelDerrota;
    
    public int VIDA1 = 0;
    
    public int curarJugador = 0;
    private JButton volverPanel1, rescatarHadas;
    
    private JButton volverPanel2, boton1, boton2, boton3, boton4, boton5, boton6, boton7, boton8, boton9, boton10, boton11, boton12, boton13, boton14, boton15, boton16, construirCasa;
    
    private int contador=0;
    private int barra=0;
    private boolean rescate=false;
    
    private int contadorC=0;
    private int exitoC=0,falloC=0;
    private int segundos=1000;
    private boolean b1=false,b2=false,b3=false,b4=false,b5=false,b6=false,b7=false,b8=false,b9=false,b10=false,b11=false,b12=false,b13=false,b14=false,b15=false,b16=false;
    private int casasConstruidas=0,errores=0;
    private int acumulacionCasa=0;
    
    private int casasCompletas=0;
    private int hadasAux=0;
    private int invAux1=0,invAux2=0;
    
    public Ventana(){
        inicioComponentes();
    }
    //***************CREA EL PANEL PARA GUARDAR PANELES*****************************
    private void inicioComponentes(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800,500);
        this.setTitle("Reto Final");
        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout(0,0));
        this.getContentPane().add(mainPanel);
        
 //************************PANEL DE RESCATE DE HADAS********************************
        panelRescatar = new JPanel();
        panelRescatar.setBackground(Color.blue);
        panelRescatar.setLayout(null);
        componentesRescatar();
        mainPanel.add(panelRescatar);
        panelRescatar.setEnabled(false);
        panelRescatar.setVisible(false);
        
//************************PANEL DE CONSTRUCCION DE CASA*****************************    
        panelConstruir = new JPanel();
        panelConstruir.setBackground(Color.red);
        panelConstruir.setLayout(null);
        componentesConstruir();
        mainPanel.add(panelConstruir);
        panelConstruir.setEnabled(false);
        panelConstruir.setVisible(false);
        
//**********************PANEL DE LA BATALLA PRINCIPAL*******************************       
        panelJuego = new JPanel();
        panelJuego.setBackground(Color.green);
        panelJuego.setLayout(null);
        componentesJuego();
        mainPanel.add(panelJuego);
        panelJuego.setEnabled(true);
        panelJuego.setVisible(true);
        
        //************************PANEL DE PANTALLA DE VICTORIA*****************************    
        panelVictoria = new JPanel();
        panelVictoria.setBackground(Color.cyan);
        panelVictoria.setLayout(null);
        componentesConstruir();
        mainPanel.add(panelVictoria);
        panelVictoria.setEnabled(false);
        panelVictoria.setVisible(false);
        
        //************************PANEL DE PANTALLA DE DERROTA*****************************    
        panelDerrota = new JPanel();
        panelDerrota.setBackground(Color.red);
        panelDerrota.setLayout(null);
        componentesConstruir();
        mainPanel.add(panelDerrota);
        panelDerrota.setEnabled(false);
        panelDerrota.setVisible(false);
        
        inicioJuego();
    }
    
    private void inicioJuego(){
        
        
    }
    //**************TURNO DEL MAGO*******************
    private void turnoMago(){
        if(!ogroDead){
            int turnoM = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            if(turnoM == 1){
                texto.setText("Ogro ataca");
                jugador1.recibirDamage(ogro1.calcularDamage());
                if(jugador1.getVida()<=0){
                    panelJuego.setEnabled(false);
                    panelJuego.setVisible(false);
                    panelDerrota.setEnabled(true);
                    panelDerrota.setVisible(true);
                }
            }
            if(turnoM == 2){
                texto.setText("Mago cura ogro");
                mago1.curarOgro(ogro1);
            }
            if(turnoM == 3){
                texto.setText("Mago potencia ogro");
                mago1.boostOgro(ogro1);
            }
            if(turnoM == 4){
                texto.setText("Mago roba hadas");
                mago1.robarHadas(jugador1);
                hInv.setText(""+jugador1.hadasInv);
            }
        }
        if(revivirOgro){
            texto.setText("El mago revive al ogro y aumenta su poder");
            mago1.revivirOgro(ogro1);
            revivirOgro = false;
            ogroDead = false;
        }
        if(ogroDead){
            texto.setText("El mago revivira al ogro");
            revivirOgro = true;
        }
        poderMago.setText(""+mago1.getHadasRobadas());
    }
//******************BOTONES DE LA BATALLA PRINCIPAL********************************
    private void componentesJuego(){
        
        atacar = new JButton("Atacar");
        atacar.setBounds(25, 380, 100, 50);
        atacar.setBackground(Color.white);
        atacar.addActionListener(this);
        panelJuego.add(atacar);
        
        bloquear = new JButton("Bloquear");
        bloquear.setBounds(135, 380, 100, 50);
        bloquear.setBackground(Color.white);
        bloquear.addActionListener(this);
        panelJuego.add(bloquear);
        
        rescatar = new JButton("Rescatar");
        rescatar.setBounds(245, 380, 100, 50);
        rescatar.setBackground(Color.white);
        rescatar.addActionListener(this);
        panelJuego.add(rescatar);
        
        contruir = new JButton("Construir");
        contruir.setBounds(355, 380, 100, 50);
        contruir.setBackground(Color.white);
        contruir.addActionListener(this);
        panelJuego.add(contruir);
        
        atacarMago = new JButton("Mago");
        atacarMago.setBounds(570, 250, 100, 50);
        atacarMago.setBackground(Color.white);
        atacarMago.addActionListener(this);
        panelJuego.add(atacarMago);
        atacarMago.setVisible(false);
        
        atacarOgro = new JButton("Ogro");
        atacarOgro.setBounds(450, 250, 100, 50);
        atacarOgro.setBackground(Color.white);
        atacarOgro.addActionListener(this);
        panelJuego.add(atacarOgro);
        atacarOgro.setVisible(false);
        
        texto = new JLabel("SAMPLE TEXT XD");
        texto.setBounds(25, 320, 735, 50);
        texto.setOpaque(true);
        panelJuego.add(texto);
        
        casasHadas = new JLabel(""+jugador1.hadasResc+"/"+casasCompletas);
        casasHadas.setBounds(300, 20, 50, 50);
        casasHadas.setOpaque(true);
        panelJuego.add(casasHadas);
        
        poderMago = new JLabel(""+mago1.getHadasRobadas());
        poderMago.setBounds(350, 20, 50, 50);
        poderMago.setOpaque(true);
        panelJuego.add(poderMago);
        
        hInv = new JLabel(""+jugador1.hadasInv);
        hInv.setBounds(50, 250, 50, 50);
        hInv.setOpaque(true);
        panelJuego.add(hInv);
        
        
        
        
    }
//******************************BOTONES DEL RESCATE DE LA HADA**********************
    private void componentesRescatar(){
        
        hadasRes = new JLabel("                             Presiona el boton para iniciar el rescate");
        hadasRes.setBounds(200, 250, 400, 50);
        hadasRes.setOpaque(true);
        panelRescatar.add(hadasRes);
        
        volverPanel1 = new JButton("Panel 1");
        volverPanel1.setBounds(20, 20, 100, 50);
        volverPanel1.setBackground(Color.white);
        volverPanel1.addActionListener(this);
        panelRescatar.add(volverPanel1);
        
        rescatarHadas = new JButton("Rescatar!");
        rescatarHadas.setBounds(350, 320, 100, 50);
        rescatarHadas.setBackground(Color.white);
        rescatarHadas.addActionListener(this);
        panelRescatar.add(rescatarHadas);
    
    }
//******************************BOTONES DE LA CONSTRUCCION DE LA CASA**************** 
    private void componentesConstruir(){
        
        timer = new JLabel("10");
        timer.setBounds(600, 50, 100, 50);
        timer.setOpaque(true);
        panelConstruir.add(timer);
        
        contadorCasas = new JLabel(""+casasConstruidas+"/5");
        contadorCasas.setBounds(600, 100, 100, 50);
        contadorCasas.setOpaque(true);
        panelConstruir.add(contadorCasas);
        
        contadorError = new JLabel(""+errores+"/2");
        contadorError.setBounds(600, 150, 100, 50);
        contadorError.setOpaque(true);
        panelConstruir.add(contadorError);
        
        construirCasa = new JButton("Construir!");
        construirCasa.setBounds(350, 390, 100, 50);
        construirCasa.setBackground(Color.white);
        construirCasa.addActionListener(this);
        panelConstruir.add(construirCasa);
        
        volverPanel2 = new JButton("Panel 1");
        volverPanel2.setBounds(20, 20, 100, 50);
        volverPanel2.setBackground(Color.white);
        volverPanel2.addActionListener(this);
        panelConstruir.add(volverPanel2);
        
        boton1 = new JButton("");
        boton1.setBounds(270, 100, 50, 50);
        boton1.setBackground(Color.white);
        boton1.addActionListener(this);
        panelConstruir.add(boton1);
        
        boton2 = new JButton("");
        boton2.setBounds(340, 100, 50, 50);
        boton2.setBackground(Color.white);
        boton2.addActionListener(this);
        panelConstruir.add(boton2);
        
        boton3 = new JButton("");
        boton3.setBounds(410, 100, 50, 50);
        boton3.setBackground(Color.white);
        boton3.addActionListener(this);
        panelConstruir.add(boton3);
        
        boton4 = new JButton("");
        boton4.setBounds(480, 100, 50, 50);
        boton4.setBackground(Color.white);
        boton4.addActionListener(this);
        panelConstruir.add(boton4);
        
        boton5 = new JButton("");
        boton5.setBounds(270, 170, 50, 50);
        boton5.setBackground(Color.white);
        boton5.addActionListener(this);
        panelConstruir.add(boton5);
        
        boton6 = new JButton("");
        boton6.setBounds(340, 170, 50, 50);
        boton6.setBackground(Color.white);
        boton6.addActionListener(this);
        panelConstruir.add(boton6);
        
        boton7 = new JButton("");
        boton7.setBounds(410, 170, 50, 50);
        boton7.setBackground(Color.white);
        boton7.addActionListener(this);
        panelConstruir.add(boton7);
        
        boton8 = new JButton("");
        boton8.setBounds(480, 170, 50, 50);
        boton8.setBackground(Color.white);
        boton8.addActionListener(this);
        panelConstruir.add(boton8);
        
        boton9 = new JButton("");
        boton9.setBounds(270, 240, 50, 50);
        boton9.setBackground(Color.white);
        boton9.addActionListener(this);
        panelConstruir.add(boton9);
        
        boton10 = new JButton("");
        boton10.setBounds(340, 240, 50, 50);
        boton10.setBackground(Color.white);
        boton10.addActionListener(this);
        panelConstruir.add(boton10);
        
        boton11 = new JButton("");
        boton11.setBounds(410, 240, 50, 50);
        boton11.setBackground(Color.white);
        boton11.addActionListener(this);
        panelConstruir.add(boton11);
        
        boton12 = new JButton("");
        boton12.setBounds(480, 240, 50, 50);
        boton12.setBackground(Color.white);
        boton12.addActionListener(this);
        panelConstruir.add(boton12);
        
        boton13 = new JButton("");
        boton13.setBounds(270, 310, 50, 50);
        boton13.setBackground(Color.white);
        boton13.addActionListener(this);
        panelConstruir.add(boton13);
        
        boton14 = new JButton("");
        boton14.setBounds(340, 310, 50, 50);
        boton14.setBackground(Color.white);
        boton14.addActionListener(this);
        panelConstruir.add(boton14);
        
        boton15 = new JButton("");
        boton15.setBounds(410, 310, 50, 50);
        boton15.setBackground(Color.white);
        boton15.addActionListener(this);
        panelConstruir.add(boton15);
        
        boton16 = new JButton("");
        boton16.setBounds(480, 310, 50, 50);
        boton16.setBackground(Color.white);
        boton16.addActionListener(this);
        panelConstruir.add(boton16);
    }
   
//*****************************EJECUCION DE FUNCION DE BOTONES**********************
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        if(e.getSource()==atacar){
            texto.setText("A quien deseas atacar?");
            //texto.setText(""+jugador1.calcularDamage());
            atacarMago.setVisible(true);
            atacarOgro.setVisible(true);
        }
        
        if(e.getSource()==atacarMago){
            texto.setText("Jugador ataca al mago");
            mago1.recibirDamage(jugador1.calcularDamage());
            texto.setText("Mago: "+mago1.getVida()+"/"+mago1.getVidaMax());
            atacarMago.setVisible(false);
            atacarOgro.setVisible(false);
            if(mago1.getVida()<=0){
                panelJuego.setEnabled(false);
                panelJuego.setVisible(false);
                panelVictoria.setEnabled(true);
                panelVictoria.setVisible(true);
            }
            turnoMago();
            repaint();
        }
        
        if(e.getSource()==atacarOgro){
            texto.setText("Jugador ataca al ogro");
            ogro1.recibirDamage(jugador1.calcularDamage());
            texto.setText("Ogro: "+ogro1.getVida()+"/"+ogro1.getVidaMax());
            atacarMago.setVisible(false);
            atacarOgro.setVisible(false);
            if(ogro1.getDead())
            {
                ogroDead = true;
            }
            turnoMago();
            repaint();
        }
        
        if(e.getSource()==bloquear){
            texto.setText("JUGADOR BLOQUEA");
            atacarMago.setVisible(false);
            atacarOgro.setVisible(false);
            jugador1.setBloqueando(true);
            
            if(casasCompletas!=jugador1.getHadasResc())
            {
                hadasAux=(casasCompletas-jugador1.getHadasResc());
                for (int i = 0; i < hadasAux; i++) {
                    if(jugador1.getHadasInv()!=0)
                    {
                        jugador1.setHadasResc(jugador1.getHadasResc()+1);
                        jugador1.setHadasInv(jugador1.getHadasInv()-1);
                        mago1.setHadasRobadas(mago1.getHadasRobadas()-1);
                        jugador1.setPoderHadas(jugador1.getHadasResc()/10);
                        
                        curarJugador = curarJugador + 1;
                        if(curarJugador==10){
                            jugador1.setVida(jugador1.getVidaMax());
                            curarJugador = 0;
                        }
                    }
                    
                }
                casasHadas.setText(""+jugador1.getHadasResc()+"/"+casasCompletas);
                hInv.setText(""+jugador1.hadasInv);
                //mago1.setHadasRobadas(mago1.getHadasRobadas()-jugador1.getHadasResc());
                poderMago.setText(""+mago1.getHadasRobadas());
                mago1.calcularPoder();
            }
            
            turnoMago();
            repaint();
        }
        
        if(e.getSource()==rescatar){
            texto.setText("JUGADOR RESCATA HADA");
            atacarMago.setVisible(false);
            atacarOgro.setVisible(false);
            panelJuego.setEnabled(false);
            panelJuego.setVisible(false);
            panelRescatar.setEnabled(true);
            panelRescatar.setVisible(true);
            repaint();
            
            
            turnoMago();
        }
        
        if(e.getSource()==contruir){
            texto.setText("JUGADOR CONSTRUYE CASA");
            atacarMago.setVisible(false);
            atacarOgro.setVisible(false);
            panelJuego.setEnabled(false);
            panelJuego.setVisible(false);
            panelConstruir.setEnabled(true);
            panelConstruir.setVisible(true);
            repaint();
            
            turnoMago();
        }
        
       
        
        if(e.getSource()==rescatarHadas){
                   
          if(contador==1)
          {
              System.out.println(minijuegoHada(getBarra(),jugador1.getHadasInv())+" hadas rescatadas");
              invAux1=5-jugador1.getHadasInv();
              invAux2=minijuegoHada(getBarra(),jugador1.getHadasInv());
              for (int i = 0; i < invAux1; i++) 
              {
                  if(invAux2==0)
                  {
                      break;
                  }
                  else
                  {
                      invAux2--;
                      jugador1.setHadasInv(jugador1.getHadasInv()+1);
                  }
              }
                  
              //hadasRes.setText("                                            Haz rescatado "+ minijuegoHada(getBarra(),jugador1.getHadasInv())+ " hadas");
              hadasRes.setText("                                            Haz rescatado "+ minijuegoHada(getBarra(),jugador1.getHadasInv())+ " hadas");
              
              
              
          }
          contador++;  
            
        }
        
        if(e.getSource()==construirCasa)
        {
            
            contadorC++;
            construirCasa.setEnabled(false);
            construirCasa.setBackground(Color.gray);
            minijuegoCasa();
        }
        
        if(e.getSource()==boton1)
        {
           if(b1==true)
           {
               boton1.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton1.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton1.setEnabled(false);
        }
        
        if(e.getSource()==boton2)
        {
           if(b2==true)
           {
               boton2.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton2.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton2.setEnabled(false);
        }
        
        if(e.getSource()==boton3)
        {
           if(b3==true)
           {
               boton3.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton3.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton3.setEnabled(false);
        }
        
        if(e.getSource()==boton4)
        {
           if(b4==true)
           {
               boton4.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton4.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton4.setEnabled(false);
        }
        
        if(e.getSource()==boton5)
        {
           if(b5==true)
           {
               boton5.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton5.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton5.setEnabled(false);
        }
        
        if(e.getSource()==boton6)
        {
           if(b6==true)
           {
               boton6.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton6.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton6.setEnabled(false);
        }
        
        if(e.getSource()==boton7)
        {
           if(b7==true)
           {
               boton7.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton7.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton7.setEnabled(false);
        }
        
        if(e.getSource()==boton8)
        {
            if(b8==true)
           {
               boton8.setBackground(Color.blue);
               acumulacionCasa++;
           }
            else
           {
               boton8.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
            boton8.setEnabled(false);
        }
        
        if(e.getSource()==boton9)
        {
            if(b9==true)
           {
               boton9.setBackground(Color.blue);
               acumulacionCasa++;
           }
            else
           {
               boton9.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
            boton9.setEnabled(false);
        }
        
        if(e.getSource()==boton10)
        {
            if(b10==true)
           {
               boton10.setBackground(Color.blue);
               acumulacionCasa++;
           }
            else
           {
               boton10.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
            boton10.setEnabled(false);
        }
        
        if(e.getSource()==boton11)
        {
           if(b11==true)
           {
               boton11.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton11.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton11.setEnabled(false);
        }
        
        if(e.getSource()==boton12)
        {
           if(b12==true)
           {
               boton12.setBackground(Color.blue);
               acumulacionCasa++;
           }
           else
           {
               boton12.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton12.setEnabled(false);
        }
        
        if(e.getSource()==boton13)
        {
           if(b13==true)
           {
               boton13.setBackground(Color.blue);
               acumulacionCasa++;
           }else
           {
               boton13.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton13.setEnabled(false);
        }
        
        if(e.getSource()==boton14)
        {
            if(b14==true)
           {
               boton14.setBackground(Color.blue);
               acumulacionCasa++;
           }else
           {
               boton14.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton14.setEnabled(false); 
        }
        
        if(e.getSource()==boton15)
        {
           if(b15==true)
           {
               boton15.setBackground(Color.blue);
               acumulacionCasa++;
           }else
           {
               boton15.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton15.setEnabled(false);
        }
        
        if(e.getSource()==boton16)
        {
           if(b16==true)
           {
               
               boton16.setBackground(Color.blue);
               acumulacionCasa++;
           }else
           {
               boton16.setBackground(Color.red);
               errores++;
               contadorError.setText(""+errores+"/2");
           }
           boton16.setEnabled(false);
        }
        
        if(e.getSource()==volverPanel1){
            panelJuego.setEnabled(true);
            panelJuego.setVisible(true);
            panelRescatar.setEnabled(false);
            panelRescatar.setVisible(false);
            repaint();
            hadasRes.setText("                             Presiona el boton para iniciar el rescate");
            hInv.setText((""+jugador1.hadasInv));
            contador=0;
            barra=0;
        }
        
        if(e.getSource()==volverPanel2){
            panelJuego.setEnabled(true);
            panelJuego.setVisible(true);
            panelConstruir.setEnabled(false);
            panelConstruir.setVisible(false);
            casasCompletas+=casasConstruidas;
            casasHadas.setText(""+jugador1.hadasResc+"/"+casasCompletas);
            contadorC=0;
            construirCasa.setEnabled(true);
            construirCasa.setBackground(Color.white);
            segundos=10000;
            refresh();
            casasConstruidas=0;
            errores=0;
            contadorCasas.setText(""+casasConstruidas+"/5");
            contadorError.setText(""+errores+"/2");
            repaint();
        }
    }
    
    public void paint(Graphics g){
        
        super.paint(g);
        if(panelJuego.isEnabled()){
            //****BARRA DE VIDA JUGADOR****
            g.setColor(Color.black);
            g.fillRect(25, 100, 29, 240);
            g.setColor(Color.red);
            g.fillRect(29, (int) (105.0+(229.0-((229.0/300.0)*jugador1.vida))), 21, (int) (230.0-(229.0-((229.0/300.0)*jugador1.vida))));
            //g.fillRect(29, 105+VIDA1, 21, 230-VIDA1);
            //****BARRA DE VIDA OGRO****
            g.setColor(Color.black);
            g.fillRect(700, 100, 29, 240);
            g.setColor(Color.green);
            g.fillRect(704, (int) (105.0+(229.0-((229.0/100.0)*ogro1.vida))), 21, (int) (230.0-(229.0-((229.0/100.0)*ogro1.vida))));
            //****BARRA DE VIDA MAGO****
            g.setColor(Color.black);
            g.fillRect(750, 100, 29, 240);
            g.setColor(Color.magenta);
            g.fillRect(754, (int) (105.0+(229.0-((229.0/100.0)*mago1.vida))), 21, (int) (230.0-(229.0-((229.0/100.0)*mago1.vida))));
        }
       if(panelRescatar.isEnabled()){
           g.setColor(Color.black);
           g.fillRect(150, 200,500, 60);
           g.setColor(Color.white);
           g.fillRect(154, 204,492, 52);
           g.setColor(Color.green);
           g.fillRect(250, 204,280, 52);
           g.setColor(Color.orange);
           g.fillRect(450, 204,120, 52);
           g.setColor(Color.red);
           g.fillRect(570, 204,40, 52);
           
           
                g.setColor(Color.gray);
                g.fillRect(150+barra, 190,5, 79);
                if(contador==1)
                {
                    
                  
               try {
                   hadasRes.setText("");
                   barra++;
                   Thread.sleep(2);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
               }
                    
                    
                }
                
                if(barra>500)
                {
                    barra=0;
                }
                
                
                repaint();
           
           
       }
       if(panelConstruir.isEnabled())
       {
           if(contadorC==1&&segundos!=0&&casasConstruidas!=5&&errores!=2)
                {
                    
                        
               try {
                   timer.setText(""+(segundos-1));
                   segundos--;
                   if(acumulacionCasa==5)
                   {
                       casasConstruidas++;
                       segundos=10000;
                       acumulacionCasa=0;
                       contadorCasas.setText(""+casasConstruidas+"/5");
                       
                       refresh();
                       minijuegoCasa();
                       
                   }
                   
                   System.out.println(casasCompletas);
                   Thread.sleep(10);
               } catch (InterruptedException ex) {
                   Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
               }
                    
                    
                }
           if(errores==2)
           {
               defresh();
           }
           
                
                repaint();
                
                if(segundos==0||casasConstruidas==5||errores==2)
                {
                    refresh();
                }
       }
       
       
       
        
    }
////////////MINIJUEGO HADAS////////////////////////////////////////
    public int getBarra() {
        return barra;
    }

    public void setBarra(int barra) {
        this.barra = barra;
    }
    
     public int minijuegoHada(int barra,int hadasInv)
    {                    
                   
        
        if(barra>=100 &&barra<=295 )
        {
            if(hadasInv!=5)
            {
                return 1;
            }
            
        }
        else if(barra>=296 &&barra<=420 )
        {
            if(hadasInv>=4)
            {
                return 5;
            }
            else
            {
                return 2;
            }
            
        }
        else if(barra>=421 &&barra<=460 )
        {
            if(hadasInv>=3)
            {
                return 5;
            }
            else
            {
                return 3;
            }
            
        }
        else if(barra>500)
        {
            
            barra=0;
            
        }
        
        
        
        System.out.println(barra);
        System.out.println("ejecucion");
                    
            
        return 0;
            
    } 
     
///////////////MINIJUEGO CASAS//////////////////////////////////////
     public void minijuegoCasa()
    {
///////////////GENERADOR DE NUMEROS ALEATORIOS UNICOS////////////////////    
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<17; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<5; i++) {
            iluminacionB(list.get(i));
            repaint();
        }
        
    }
     
     public void iluminacionB(int iluminar)
     {
         switch(iluminar)
         {
             case 1:
                 boton1.setBackground(Color.green);
                 b1=true;
                 
                 break;
             case 2:
                 boton2.setBackground(Color.green);
                 b2=true;
                 break;
             case 3:
                 boton3.setBackground(Color.green);
                 b3=true;
                 break;
             case 4:
                 boton4.setBackground(Color.green);
                 b4=true;
                 break;   
             case 5:
                 boton5.setBackground(Color.green);
                 b5=true;
                 break;
             case 6:
                 boton6.setBackground(Color.green);
                 b6=true;
                 break;
             case 7:
                 boton7.setBackground(Color.green);
                 b7=true;
                 break;
             case 8:
                 boton8.setBackground(Color.green);
                 b8=true;
                 break;
             case 9:
                 boton9.setBackground(Color.green);
                 b9=true;
                 break;
             case 10:
                 boton10.setBackground(Color.green);
                 b10=true;
                 break;
             case 11:
                 boton11.setBackground(Color.green);
                 b11=true;
                 break;
             case 12:
                 boton12.setBackground(Color.green);
                 b12=true;
                 break;   
             case 13:
                 boton13.setBackground(Color.green);
                 b13=true;
                 break;
             case 14:
                 boton14.setBackground(Color.green);
                 b14=true;
                 break;
             case 15:
                 boton15.setBackground(Color.green);
                 b15=true;
                 break;
             case 16:
                 boton16.setBackground(Color.green);
                 b16=true;
                 break; 
         }
                 
     }
     
     public void refresh(){
         boton1.setEnabled(true);
         boton1.setBackground(Color.white);
         boton2.setEnabled(true);
         boton2.setBackground(Color.white);
         boton3.setEnabled(true);
         boton3.setBackground(Color.white);
         boton4.setEnabled(true);
         boton4.setBackground(Color.white);
         boton5.setEnabled(true);
         boton5.setBackground(Color.white);
         boton6.setEnabled(true);
         boton6.setBackground(Color.white);
         boton7.setEnabled(true);
         boton7.setBackground(Color.white);
         boton8.setEnabled(true);
         boton8.setBackground(Color.white);
         boton9.setEnabled(true);
         boton9.setBackground(Color.white);
         boton10.setEnabled(true);
         boton10.setBackground(Color.white);
         boton11.setEnabled(true);
         boton11.setBackground(Color.white);
         boton12.setEnabled(true);
         boton12.setBackground(Color.white);
         boton13.setEnabled(true);
         boton13.setBackground(Color.white);
         boton14.setEnabled(true);
         boton14.setBackground(Color.white);
         boton15.setEnabled(true);
         boton15.setBackground(Color.white);
         boton16.setEnabled(true);
         boton16.setBackground(Color.white);
         b1=false;
         b2=false;
         b3=false;
         b4=false;
         b5=false;
         b6=false;
         b7=false;
         b8=false;
         b9=false;
         b10=false;
         b11=false;
         b12=false;
         b13=false;
         b14=false;
         b15=false;
         b16=false;
         acumulacionCasa=0;
         
         
         
     }
         
     public void defresh(){
         boton1.setEnabled(false);
         boton2.setEnabled(false);
         boton3.setEnabled(false);
         boton4.setEnabled(false);
         boton5.setEnabled(false);
         boton6.setEnabled(false);
         boton7.setEnabled(false);
         boton8.setEnabled(false);
         boton9.setEnabled(false);
         boton10.setEnabled(false);
         boton11.setEnabled(false);
         boton12.setEnabled(false);
         boton13.setEnabled(false);
         boton14.setEnabled(false);
         boton15.setEnabled(false);
         boton16.setEnabled(false);
         
     }
}
