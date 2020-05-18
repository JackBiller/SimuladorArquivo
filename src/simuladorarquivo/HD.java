/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladorarquivo;

import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public final class HD {
    private int num_bloco;
    private int tamanhoHD;
    private char conteudo[];
    private ArrayList<Atributo> blocos_livres;
    private char nulo;


    public HD() {
        this.setConteudo(new char[0]);
    }
    
    public int getNum_bloco() {
        return num_bloco;
    }

    public void setNum_bloco(int num_bloco) {
        this.num_bloco = num_bloco;
    }

    public int getTamanhoHD() {
        return tamanhoHD;
    }

    public void setTamanhoHD(int tamanhoHD) {
        this.tamanhoHD = tamanhoHD;
    }

    public char[] getConteudo() {
        return conteudo;
    }

    public void setConteudo(char[] conteudo) {
        this.conteudo = conteudo;
    }

    public ArrayList<Atributo> getBlocos_livres() {
        return blocos_livres;
    }

    public void setBlocos_livres(ArrayList<Atributo> blocos_livres) {
        this.blocos_livres = blocos_livres;
    }

    public char getNulo() {
        return nulo;
    }

    
    
    
    


    /*************************************************************************/
    /* Funções padrão */
    /*************************************************************************/
    /**
     * @return  */
    @Override
    public String toString(){
        // return super.toString();
        return ""
            // +   "\nTamanho: "               + this.getTamanhoHD()
            // +   "\nTamanho do Bloco: "      + this.getNum_bloco()
            // +   "\nTamanho disponivel: "    + this.tamanhoDisponivel()
            // +   "\nBlocos Livres:"          + this.drawBloco_Livre()
            +   "\n";
    }






    /*************************************************************************/
    /* Funções especificas */
    /*************************************************************************/
    /**
     * @param tamanho
     * @param bloco
     * @return  */
    public String criarHD(int tamanho, int bloco){
        if(tamanho < bloco){
            return "O tamanho do HD não pode ser menor que o tamanho do bloco!";
        }
        if(tamanho%bloco != 0){
            return "O tamanho do HD não pode ser dividido por esse número do bloco!";
        }
        this.setTamanhoHD(tamanho);
        this.setConteudo(new char[tamanho]);
        this.setNum_bloco(bloco);
        this.formatarHD();
        this.updateBlocoLivre();
        return "OK";
    }

    public void formatarHD(){
        for (int i = 0; i < this.conteudo.length; i++) {
            this.conteudo[i] = this.getNulo();
        }
    }

    public void updateBlocoLivre(){
        if(this.conteudo.length == 0){
            System.out.println("O HD não tem memoria usável");
            return;
        }
        int cont = 0, indiceSet;
        Atributo atributo;
        this.blocos_livres = new ArrayList<>();

        
        for (int i = 0; i < this.conteudo.length; i++) {
            if(i % this.getNum_bloco() == 0 && this.conteudo[i] == this.getNulo()){  
                cont++;
            } else if(cont != 0 && i % this.getNum_bloco() == 0){
                indiceSet = i; //  == 1 ? 0 : i + 1;
                System.out.println("indiceSet: " + indiceSet);
                System.out.println("cont: " + cont);
                System.out.println("this.getNum_bloco(): " + this.getNum_bloco());
                System.out.println("((indiceSet) - (cont * this.getNum_bloco())): " + ((indiceSet) - (cont * this.getNum_bloco())));
                atributo = new Atributo(((indiceSet) - (cont * this.getNum_bloco())), cont);
                // if(i != 0) i += 1;
                System.out.println("bloco livre: " + atributo.getBlocos_livre());
                System.out.println("indice: " + atributo.getIndice());
                blocos_livres.add(atributo);
                cont = 0;
            }
        }
        if(cont != 0){
            blocos_livres.add(new Atributo(((this.conteudo.length) - (cont * this.getNum_bloco())), cont));
        }
    }

    public String drawBloco_Livre(){
        if(this.blocos_livres.isEmpty())
            return "Nenhum, o está HD lotado";

        
        String text = "";
        for(int i = 0; i < this.blocos_livres.size(); i++){
            text += "\n"
                + "Indice: " + this.blocos_livres.get(i).getIndice() 
                + " - "
                + "Número de blocos livres a frente: " + (this.blocos_livres.get(i).getBlocos_livre()-1);
        }
        return text;
    }
    
    public String drawConteudo(){
        String textoCel = "";
        String corCel = "";
        boolean isBlock = false;
        String print = "<html>"
                +   "<div style='width:250px;overflow-y:scroll;'>"
                +   "<table style='background-color:white;' border='1' width='300px'>"
                +   "<tr>";

        for (int i = 0; i < this.getConteudo().length; i++) {
            if (i != 0 && i % this.getNum_bloco() == 0) {
                print += "</tr><tr>";
                isBlock = false;
            }
            
            if (this.getConteudo()[i] == this.getNulo()){
                textoCel = this.linhaNop();
                if(isBlock){
                    corCel = "#FFA500";
                } else {
                    corCel = "#3CB371";
                }
            } else {
                for (int j = 0; j < this.numCasa(this.getConteudo().length); j++) {
                    textoCel = "&nbsp;";
                }
                textoCel += this.getConteudo()[i];
                // corCel = "#FF6347";
                corCel = "#1E90FF";
                isBlock = true;
            }

            print +=    "<td align='center' style='background-color:" + corCel + "'>"
                    +       "<b>" + zeroEsquerda(i) + "</b>"
                    +       "<br>"
                    +       textoCel
                    +   "</td>";
        }

        print += "</tr>";

        /* for (int i = 0; i < this.getConteudo().length; i++) {
            if(i != 0 && i % this.getNum_bloco() == 0) print += " | ";
            if (this.getConteudo()[i] == this.getNulo()){
                print += this.linhaNop() + " ";
            } else {
                for (int j = 0; j < this.numCasa(this.getConteudo().length); j++) {
                    print += " ";
                }
                print += this.getConteudo()[i] + " ";
            }
        } */
        
        print += "" 
            +   "</table>"
            +   "</div>"
            +   "</html>";
        return print;
    }
    
    public String linhaNop(){
        String linha = "";
        for (int i = 0; i < this.numCasa(this.getConteudo().length)+1; i++) linha += "-";
        return linha;
    }
    
    public String zeroEsquerda(int num){
        String zeros = "";
        int casas = 0, result = this.conteudo.length;
        while(result > 9){
            result /= 10;
            casas++;
        }
        for (int i = 0; i < casas-this.numCasa(num); i++) zeros += "0";
        zeros += Integer.toString(num);
        return zeros;
    }
    
    public int numCasa(int num){
        int casas = 0, result = num;
        while(result > 9){
            result /= 10;
            casas++;
        }
        return casas;
    }

    public int tamanhoDisponivel(){
        int disponivel = 0;
        for(int i = 0; i < this.blocos_livres.size(); i++)
            disponivel += (this.blocos_livres.get(i).getBlocos_livre() * this.getNum_bloco());
        return disponivel;
    }

    public ArrayList<Integer> gravarArquivo(String conteudo){
        char chars[] = conteudo.toCharArray(); 
        int indice = 0;
        /* int indiceStop[] = new int[2];
        indiceStop[0] = -1; */
        boolean isIndice = false;
        ArrayList<Integer> posicoes = new ArrayList<>();
        ArrayList<Integer> blocosLivres = this.returnIndiceLivres();
        
        System.out.println(blocosLivres);
        
        for (int i = 0; i < blocosLivres.size(); i++) {
            isIndice = false;
            for (int j = 0; j < this.getNum_bloco(); j++) {
                if (indice < chars.length) {
                    if (!isIndice) {
                        posicoes.add(blocosLivres.get(i));
                        isIndice = true;
                    }
                    this.getConteudo()[(blocosLivres.get(i) + j)] = chars[indice];
                    indice++;
                }
            }
        }
        this.updateBlocoLivre();
        return posicoes;
    }
    
    public boolean isValidArmazenamento(String texto){
        if(this.tamanhoDisponivel() < texto.toCharArray().length)
            return false;
        return true;
    }
    
    public ArrayList<Integer> returnIndiceLivres(){
        ArrayList<Integer> blocosLivresLocal = new ArrayList<>();
        // System.out.println(this.blocos_livres);
        for (int i = 0; i < this.blocos_livres.size(); i++) {
            for (int j = 0; j < this.blocos_livres.get(i).getBlocos_livre(); j++) {
                System.out.println(this.blocos_livres.get(i).getIndice());
                /* if (j == 0) {
                    blocosLivresLocal.add(this.blocos_livres.get(i).getIndice());
                } else { */
                    blocosLivresLocal.add(this.blocos_livres.get(i).getIndice() + (j * this.getNum_bloco()));
                // }
            }
        }
        return blocosLivresLocal;
    }
    
    public boolean apagarBlocoHD(int indice) {
        if (indice % this.getNum_bloco() != 0) {
            System.out.println("Indice inválido!");
            return false;
        }

        boolean isNulo = false;
        for (int i = 0; i < this.getBlocos_livres().size(); i++) 
            if (this.getBlocos_livres().get(i).getIndice() == indice)
                isNulo = true;
        if(isNulo){
            System.out.println("O bloco já está livre!");
            return false;
        }

        for (int i = 0; i < this.getNum_bloco(); i++) 
            this.getConteudo()[indice + i] = this.getNulo();
            // this.getConteudo()[indice + i] = this.getNulo();

        this.updateBlocoLivre();
        return true;
    }
}
