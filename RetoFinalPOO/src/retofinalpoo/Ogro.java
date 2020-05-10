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
public class Ogro {
    
    int vidamax;
    int vida;
    int damage;
    int boost;
    boolean dead;
    void Ogro(){
        this.vidamax = 100;
        this.vida = vidamax;
        this.damage = 20;
        this.boost = 0;
        this.dead = false;
    }
    /*
    void Ogro(int vidamax, int vida, int damage, int boost){
        this.vidamax = vidamax;
        this.vida = vida;
        this.damage = damage;
        this.boost = boost;
    }
    */
    public int calcularDamage(){
        
        int damageF = 0;
        int critico = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if(critico==10){
            damageF = (int) (((damage * (0.6 + Math.random() * (1.4 - 0.6))) + (boost)) * 2);
        }
        if(critico==1){
            damageF = (int) (((damage * (0.6 + Math.random() * (1.4 - 0.6))) + (boost)) / 2);
        }
        else{
            damageF = (int) ((damage * (0.6 + Math.random() * (1.4 - 0.6))) + (boost));
        }
        return damageF;
    }
    
    public void recibirDamage(int dano){
        this.vida = (this.vida - dano);
        if(this.vida <=0){
            this.dead = true;
        }
    }
    
//********************GETTERS***********************

    public int getVidaMax() {
        return this.vidamax;
    }
    
    public int getVida() {
        return this.vida;
    }

    public int getDamage() {
        return this.damage;
    }
    
    public boolean getDead(){
        return this.dead;
    }

//*******************SETTERS***********************

    public void setVidaMax(int vidamax) {
        this.vidamax = vidamax;
    }
    
    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public void setDead(boolean ded){
        this.dead = ded;
    }
    
}
