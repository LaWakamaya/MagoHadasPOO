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
public class Jugador {
    
    int vida;
    int damage;
    int hadasInv;
    int hadasResc;
    int poderHadas;
    
    void Jugador(){
        this.vida = 300;
        this.damage = 20;
        this.poderHadas = 0;
        this.hadasInv = 0;
        this.hadasResc= 0;
    }
    
    int calcularDamage(){
        int damageF = 0;
        int critico = (int) Math.random() * 11;
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
}
