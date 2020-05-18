/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorarquivo;

import java.util.ArrayList;
import java.util.Date;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Jack
 */
public class Diretorio {
    private HD hd;
    private ArrayList<Inodes> inodes;

    public Diretorio() {
        inodes = new ArrayList<>();
    }
    
    
    public HD getHd() {
        return hd;
    }

    public void setHd(HD hd) {
        this.hd = hd;
    }

    public ArrayList<Inodes> getInodes() {
        return inodes;
    }

    public void setInodes(ArrayList<Inodes> inodes) {
        this.inodes = inodes;
    }

    public Inodes getInodes(int indice){
        return this.inodes.get(indice);
    }

    public void addInodes(Inodes inodes){
        this.inodes.add(inodes);
    }
    
    
    
    



    /*************************************************************************/
    /* Funções especificas */
    /*************************************************************************/
    /**
     * 
     */
    public void listarArquivos(){
        for (int i = 0; i < this.inodes.size(); i++){
            System.out.println("\n");
            System.out.println(this.inodes.get(i));
        }
    }
    
    public void criarArquivo(String nome){
        this.criarArquivo(nome, "");
    }
    
    public void criarArquivo(String nome, String conteudo){
        Inodes inodes = new Inodes();
        inodes.setNome(nome);
        this.getInodes().add(inodes);
        System.out.println("Indice: " + (this.getInodes().size()-1));
        System.out.println("Conteudo: " + conteudo);
        this.editarArquivo(this.getInodes().size()-1, conteudo, false);
    }
    
    public void editarArquivo(int indice, String arquivo){
        this.editarArquivo(indice, arquivo, false);
    }
    public void editarArquivo(int indice, String arquivo, boolean concat){
        String arquivoOld = this.readArquvo(indice);
        if(concat){
            arquivo = arquivoOld + arquivo;
        }
        this.apagarArquivo(indice);
        
        if(!this.getHd().isValidArmazenamento(arquivo)){
            System.out.println("O HD não tem espaço sufuciente!");
            showMessageDialog(null, "O HD não tem espaço sufuciente!");
            this.editarArquivo(indice, arquivoOld, false);
            return;
        }
        ArrayList<Integer> posicoes = this.getHd().gravarArquivo(arquivo);
        System.out.println(posicoes);
        this.getInodes(indice).setPosicoes(posicoes);
        this.getInodes(indice).setTamanho(arquivo.toCharArray().length);
        this.getInodes(indice).setData_modificacao(new Date());
    }

    public String readArquvo(int indice){
        ArrayList<Integer> posicoes = this.getInodes(indice).getPosicoes();
        String conteudo = "";

        for (int i = 0; i < posicoes.size(); i++) {
            for (int j = 0; j < this.getHd().getNum_bloco(); j++) {
                if (this.getHd().getConteudo()[(j + posicoes.get(i))] != this.getHd().getNulo()){
                    // System.out.println(this.getHd().getConteudo()[(j + posicoes.get(i))]);
                    conteudo += this.getHd().getConteudo()[(j + posicoes.get(i))];
                }
            }
        }

        return conteudo;
    }
    
    public void apagarArquivo(int indice) {
        this.apagarArquivo(indice, false);
    }
    
    public void apagarArquivo(int indice, boolean remover) {
        System.out.println(this.getInodes(indice).getPosicoes());
        for (int i = 0; i < this.getInodes(indice).getPosicoes().size(); i++)
            this.getHd().apagarBlocoHD(this.getInodes(indice).getPosicoes().get(i));

        if(remover){
            this.getInodes().remove(indice);
        } else {
            this.getInodes(indice).setPosicoes(new ArrayList<>());
        }
    }
    
    public String drawInode(int indice){
        String conteudo = "<html>"
                +   "<table border='1'>"
                +       "<tr>"
                /* +           "<td>"
                +               "<b></b>"
                +           "</td>" */
                +           "<td>"
                +               "<b>Blocos usados</b>"
                +           "</td>"
                +       "</tr>";
        
        for (int i = 0; i < this.getInodes(indice).getPosicoes().size(); i++) {
            conteudo += ""
                    +       "<tr>"
                    +           "<td align='center'>"
                    +               (this.getInodes(indice).getPosicoes().get(i) / this.getHd().getNum_bloco())
                    +           "</td>"
                    +       "</tr>";
        }
        
        conteudo += ""
                +     "</table>"
                + "</html>";
        
        return conteudo;
    }
    
    public String drawDiretorio(){
        String html = "<html>";
        
        html += "<center><h1>Diretório</h1></center>"
            +     "<table>"
            +          "<>";
        
        
        
        // lblConteudoDiretorio
        html += "</html>";
        return html;
    }
}
