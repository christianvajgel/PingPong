/*
Ping-Pong - Make it Ping. Hope for the Pong.

PROJETO DE BLOCO - Desenvolvimento Android [18E2-18E3] - Instituto Infnet.
Christian Vajgel - 25/09/2018 - Android Studio - Conceito DML (10/10).
Classe FirebaseDAO

Google Play -> https://play.google.com/store/apps/details?id=br.edu.infnet.pingpong
Android 6.0+

Christian Vajgel
christian.vajgel@al.infnet.edu.br
linkedin.com/in/christianvajgel/

All Rights Reserved.
*/

package br.edu.infnet.pingpong;

public class FirebaseDAO {

    private String SiteLink;

    public FirebaseDAO() {
    }

    public String getSiteLink() {
        return SiteLink;
    }

    public void setSiteLink(String siteLink) {
        SiteLink = siteLink;
    }
}
