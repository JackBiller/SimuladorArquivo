/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorarquivo;

/**
 *
 * @author Jack
 */
public class Atributo {
    private int indice;
    private int blocos_livre;

    public Atributo() {
    }

    public Atributo(int indice, int blocos_livre) {
        this.indice = indice;
        this.blocos_livre = blocos_livre;
    }
    
    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public int getBlocos_livre() {
        return blocos_livre;
    }

    public void setBlocos_livre(int blocos_livre) {
        this.blocos_livre = blocos_livre;
    }
}
