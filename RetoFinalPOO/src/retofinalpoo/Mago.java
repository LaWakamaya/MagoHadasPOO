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
    
    int vidamax=100;
    int vida=100;
    int hadasRobadas=60;
    int poder=hadasRobadas/10;
    
    
    void Mago(int vidamax,int vida, int hadasRobadas,int poder)
    {
        this.vidamax = vidamax;
        this.vida = vida;
        this.hadasRobadas = hadasRobadas;
        this.poder = poder;
    }

    public void robarHadas(Jugador j)
    {
        
        int hadasActual;
        int hadasRob;
        int robo = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        int aumentoP;
        hadasActual=j.getHadasInv();
        if(hadasActual<robo){
            robo = hadasActual;
        }
        hadasRob=hadasActual-robo;
        if(hadasRob<0)
        {
            hadasRob=0;
        }
        j.setHadasInv(hadasRob);
        aumentoP=getHadasRobadas();
        aumentoP=aumentoP+robo;
        setHadasRobadas(aumentoP);
        calcularPoder();
    }           
    
    public void calcularPoder(){
        this.poder = (int) (Math.floor(hadasRobadas/10));
    }

    public void curarOgro(Ogro o)
    {
        int vidaActual;
        int vidaNueva;
        vidaActual=o.getVida();
        vidaNueva=vidaActual+20;
        if(vidaNueva>=o.vidamax)
        {
            vidaNueva=vidamax;
        }
        o.setVida(vidaNueva);
    }

    public void boostOgro(Ogro o)
    {
        o.boost = o.boost + 10;
    }
    
    public void revivirOgro(Ogro o){
        o.vida = o.vidamax;
        boostOgro(o);
        o.dead=false;
    }
    
    public void recibirDamage(int dano){
        //float coefDano = (10-this.poder)/10;
        //this.vida = (int) (this.vida - ((dano * coefDano)));
        //this.vida = this.vida - dano;
        this.vida = this.vida - (dano/this.poder);
    }


//*******************GETTERS***********************

    public int getVidaMax() {
        return this.vidamax;
    }
    
    public int getVida() {
        return this.vida;
    }
    
    public int getHadasRobadas(){
        return this.hadasRobadas;
    }

    public int getPoder() {
        return this.poder;
    }
    
//******************SETTERS***************************

    public void setVidaMax(int vidamax) {
        this.vidamax = vidamax;
    }
    
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
