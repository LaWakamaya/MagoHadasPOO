/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retofinalpoo;
import java.util.concurrent.ThreadLocalRandom;
/**
 *
 * @author Alan Franco
 */
public class Mago {
    
    int vida;
    int poder;
    
    Ogro o = new Ogro();
    Jugador j = new Jugador();
    
    void Mago()
    {
        this.vida=100;
        this.poder=100;
    }

    void robarHadas()
    {
        
        int hadasActual;
        int hadasRobadas;
        int robo = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        int aumentoP;
        hadasActual=j.getHadasResc();
        hadasRobadas=hadasActual-robo;
        if(hadasRobadas<0)
        {
            hadasRobadas=0;
        }
        j.setHadasResc(hadasRobadas);
        aumentoP=getPoder();
        aumentoP=aumentoP+robo;
        setPoder(aumentoP);
    }           

    void curarOgro()
    {
        int vidaActual;
        int vidaNueva;
        vidaActual=o.getVida();
        vidaNueva=vidaActual+20;
        if(vidaNueva>100)
        {
            vidaNueva=100;
        }
        o.setVida(vidaNueva);
    }

    void boostOgro()
    {
        o.MBoost=true;
    }


//*******************GETTERS***********************

    public int getVida() {
        return vida;
    }

    public int getPoder() {
        return poder;
    }
    
//******************SETTERS***************************

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }
    
}
