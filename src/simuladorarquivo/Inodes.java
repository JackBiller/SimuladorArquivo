/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorarquivo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jack
 */
public final class Inodes {
    private String nome;
    private double tamanho;
    private Date data_criacao;
    private Date data_modificacao;
    private ArrayList<Integer> posicoes;


    public Inodes() {
        this.setData_criacao(new Date());
        posicoes = new ArrayList<>();
    }

    @Override
    public String toString(){
        // return super.toString();
        SimpleDateFormat formatarDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return 
              "\nNome: "                + this.getNome()
            + "\nData Criação: "        + formatarDate.format(this.getData_criacao())
            // + "\nData Modificação: "    + formatarDate.format(this.getData_modificacao())
            + "\nTamanho: "             + this.getTamanho()
            + "\nPosições: "            + this.printPos();
    }
    
    public String printPos(){
        String pos = "";
        
        for (int i = 0; i < this.getPosicoes().size(); i++) {
            if (!"".equals(pos)) pos += ",";
            pos += this.getPosicoes().get(i);
        }
        
        return pos;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public String getData_criacao(boolean print) {
        String formato = "yyyy-MM-dd HH:mm:ss";
        if(print) formato = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat formatarDate = new SimpleDateFormat(formato);
        return formatarDate.format(this.getData_criacao());
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Date getData_modificacao() {
        return data_modificacao;
    }

    public String getData_modificacao(boolean print) {
        String formato = "yyyy-MM-dd HH:mm:ss";
        if(print) formato = "dd-MM-yyyy HH:mm:ss";
        SimpleDateFormat formatarDate = new SimpleDateFormat(formato);
        return formatarDate.format(this.getData_modificacao());
    }

    public void setData_modificacao(Date data_modificacao) {
        this.data_modificacao = data_modificacao;
    }

    public ArrayList<Integer> getPosicoes() {
        return posicoes;
    }

    public void setPosicoes(ArrayList<Integer> posicoes) {
        this.posicoes = posicoes;
    }
}
