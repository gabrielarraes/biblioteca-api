package com.iesb.apibiblioteca.tokenEmail;

import java.util.Random;

public class RefazSenha {
    public void TOKEN() {

        //System.out.println(Math.random()*10);
        Random aleatorio = new Random();

        int valor = aleatorio.nextInt(10000)+1;

        System.out.println(valor);
    }
}
