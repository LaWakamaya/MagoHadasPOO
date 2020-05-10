/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retofinalpoo;

/**
 *
 * @author Alan Franco
 */
public class Ogro {
    
    int vida;
    int damage;
    boolean MBoost;
    int boost;
    Jugador j = new Jugador();
    void Ogro(){
        this.vida = 100;
        this.damage = 20;
        this.boost = 0;
    }
    
    void calcularDamage(){
        if(MBoost==true)
        {
            boost=10;
        }
        else
        {
            boost=0;
        }
        
        int damageF = 0;
        int critico = (int) Math.random() * 11;
        int vJ=0;
        if(critico==10){
            damageF = (int) (((damage * (0.6 + Math.random() * (1.4 - 0.6))) + (boost)) * 2);
            vJ=(j.getVida())-damageF;
            if(vJ<0)
            {
                vJ=0;
            }
            j.setVida(vJ);
        }
        if(critico==1){
            damageF = (int) (((damage * (0.6 + Math.random() * (1.4 - 0.6))) + (boost)) / 2);
            vJ=(j.getVida())-damageF;
            if(vJ<0)
            {
                vJ=0;
            }
            j.setVida(vJ);
        }
        else{
            damageF = (int) ((damage * (0.6 + Math.random() * (1.4 - 0.6))) + (boost));
            vJ=(j.getVida())-damageF;
            if(vJ<0)
            {
                vJ=0;
            }
            j.setVida(vJ);
        }
        
    }
    
//********************GETTERS***********************

    public int getVida() {
        return vida;
    }

    public int getDamage() {
        return damage;
    }

//*******************SETTERS***********************

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    
}
