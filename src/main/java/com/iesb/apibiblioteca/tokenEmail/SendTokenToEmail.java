package com.iesb.apibiblioteca.tokenEmail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
public class SendTokenToEmail {
    public static void main(String[] args) {
        RefazSenha senha = new RefazSenha();
        String EMAIL = "gmail";
        //String Email = EMAIL;//email do integrante

        String SENHA = "senha";
        //String Senha = SENHA;//senha do integrante


        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
        email.setSSLOnConnect(true);

        try{
            email.setFrom(EMAIL);//email que vai enviar o token
            email.setSubject("Novo token! ");
            email.setMsg("Senhor(a) o seu novo token acabou de chegar: ");
            senha.TOKEN();//token
            email.addTo(EMAIL);//email que vai receber o token
            email.send();
            System.out.println("Email enviado com sucesso");
        }catch(Exception e){
            e.printStackTrace();

        }


    }
}
