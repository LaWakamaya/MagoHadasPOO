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
    int boost;
    
    void Ogro(){
        this.vida = 100;
        this.damage = 20;
        this.boost = 0;
    }
    
    int calcularDamage(){
        int damageF = 0;
        int critico = (int) Math.random() * 11;
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
}
