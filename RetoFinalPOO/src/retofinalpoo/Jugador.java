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
    
    int vidamax=300;
    int vida=300;
    int damage=20;
    int hadasInv=0;
    int hadasResc=0;
    int poderHadas=0;
    
    boolean bloqueando=false;
    
    void Jugador(int vidamax,int vida,int damage,int poderHadas, int hadasInv, int hadasResc,boolean bloqueando){
        this.vidamax = vidamax;
        this.vida = vida;
        this.damage = damage;
        this.poderHadas = poderHadas;
        this.hadasInv = hadasInv;
        this.hadasResc= hadasResc;
        this.bloqueando = bloqueando;
    }
    
    public int calcularDamage(){
        int damageF = 0;
        int critico = ThreadLocalRandom.current().nextInt(1, 10 + 1);
        if(critico==10){
            //damageF = (int) (((this.damage * (0.8 + Math.random() * (1.2 - 0.8))) + (this.damage * (0.3 * this.poderHadas))) * 2);
            damageF = (this.damage + (this.damage * (int) (0.3*this.poderHadas))) * 2;
        }
        if(critico==1){
            //damageF = (int) (((this.damage * (0.8 + Math.random() * (1.2 - 0.8))) + (this.damage * (0.3 * this.poderHadas))) / 2);
            damageF = (this.damage + (this.damage * (int) (0.3*this.poderHadas))) / 2;
        }
        else{
            //damageF = (int) ((this.damage * (0.8 + Math.random() * (1.2 - 0.8))) + (this.damage * (0.3 * this.poderHadas))) ;
            damageF = (this.damage + (this.damage * (int) (0.3*this.poderHadas)));
        }
        return damageF;
    }
    
    public void recibirDamage(int dano){
        if(bloqueando){
            this.vida = (this.vida - (dano/2));
            this.bloqueando = false;
        }else{
            this.vida = (this.vida - dano);
        }
    }
//**************************GETTERS**********************************
    public int getVidaMax() {
        return this.vidamax;
    }
    
    public int getVida() {
        return this.vida;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getHadasInv() {
        return this.hadasInv;
    }

    public int getHadasResc() {
        return this.hadasResc;
    }

    public int getPoderHadas() {
        return this.poderHadas;
    }
    
    public boolean getBloqueando(){
        return this.bloqueando;
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
