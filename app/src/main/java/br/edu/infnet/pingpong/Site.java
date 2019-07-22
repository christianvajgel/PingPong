/*
Ping-Pong - Make it Ping. Hope for the Pong.

PROJETO DE BLOCO - Desenvolvimento Android [18E2-18E3] - Instituto Infnet.
Christian Vajgel - 25/09/2018 - Android Studio - Conceito DML (10/10).
Classe Site

Google Play -> https://play.google.com/store/apps/details?id=br.edu.infnet.pingpong
Android 6.0+

Christian Vajgel
christian.vajgel@al.infnet.edu.br
linkedin.com/in/christianvajgel/

All Rights Reserved.
*/

package br.edu.infnet.pingpong;

import java.io.Serializable;

public class Site implements Serializable {
    public Site() {
    }

    public Site (String siteNome, String siteStatus){
        this.siteNome = siteNome;
        this.siteStatus = siteStatus;
    }
    String siteNome;
    String siteStatus;
    boolean online;

    public String getSiteNome() {
        return siteNome;
    }

    public void setSiteNome(String siteNome) {
        this.siteNome = siteNome;
    }

    public String getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(String siteStatus) {
        this.siteStatus = siteStatus;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnlineStatus(boolean status) {
        this.online = status;
    }
}