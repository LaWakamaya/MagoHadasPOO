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
    int hadasRobadas;
    int poder;
    
    
    void Mago()
    {
        this.vida = 100;
        this.hadasRobadas = 100;
        this.poder = hadasRobadas/10;
    }

    void robarHadas(Jugador j)
    {
        
        int hadasActual;
        int hadasRob;
        int robo = ThreadLocalRandom.current().nextInt(1, 5 + 1);
        int aumentoP;
        hadasActual=j.getHadasInv();
        hadasRob=hadasActual-robo;
        if(hadasRob<0)
        {
            hadasRob=0;
        }
        j.setHadasResc (hadasRob);
        aumentoP=getHadasRobadas();
        aumentoP=aumentoP+robo;
        setHadasRobadas(aumentoP);
        
    }           

    void curarOgro(Ogro o)
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

    void boostOgro(Ogro o)
    {
        o.MBoost=true;
    }


//*******************GETTERS***********************

    public int getVida() {
        return vida;
    }
    
    public int getHadasRobadas(){
        return hadasRobadas;
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
    
    public void setHadasRobadas(int hadas){
        this.hadasRobadas = hadas;
    }
}
