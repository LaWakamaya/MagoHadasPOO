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
public class Jugador {
    
    int vidamax;
    int vida;
    int damage;
    int hadasInv;
    int hadasResc;
    int poderHadas;
    
    boolean bloqueando;
    
    void Jugador(){
        this.vidamax = 300;
        this.vida = vidamax;
        this.damage = 20;
        this.poderHadas = 0;
        this.hadasInv = 0;
        this.hadasResc= 0;
        this.bloqueando = false;
    }
    
    public int calcularDamage(){
        int damageF = 0;
        int critico = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if(critico==10){
            damageF = (int) (((damage * (0.8 + Math.random() * (1.2 - 0.8))) + (damage * (0.3 * poderHadas))) * 2);
        }
        if(critico==1){
            damageF = (int) (((damage * (0.8 + Math.random() * (1.2 - 0.8))) + (damage * (0.3 * poderHadas))) / 2);
        }
        else{
            damageF = (int) ((damage * (0.8 + Math.random() * (1.2 - 0.8))) + (damage * (0.3 * poderHadas))) ;
        }
        return damageF;
    }
    
    public void recibirDamage(int dano){
        if(bloqueando){
            this.vida = (this.vida - dano)/2;
            this.bloqueando = false;
        }else{
            this.vida = (this.vida - dano);
        }
    }
//**************************GETTERS**********************************
    public int getVidaMax() {
        return vidamax;
    }
    
    public int getVida() {
        return vida;
    }

    public int getDamage() {
        return damage;
    }

    public int getHadasInv() {
        return hadasInv;
    }

    public int getHadasResc() {
        return hadasResc;
    }

    public int getPoderHadas() {
        return poderHadas;
    }
    
    public boolean getBloqueando(){
        return bloqueando;
    }

//**********************SETTERS**************************************

    public void setVidaMax(int vidamax) {
        this.vidamax = vidamax;
    }
    
    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setHadasInv(int hadasInv) {
        this.hadasInv = hadasInv;
    }

    public void setHadasResc(int hadasResc) {
        this.hadasResc = hadasResc;
    }

    public void setPoderHadas(int poderHadas) {
        this.poderHadas = poderHadas;
    }
    
    public void setBloqueando(boolean bloquear){
        this.bloqueando = bloquear;
    }
    
}
