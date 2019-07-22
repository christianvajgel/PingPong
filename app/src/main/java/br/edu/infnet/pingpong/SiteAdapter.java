/*
Ping-Pong - Make it Ping. Hope for the Pong.

PROJETO DE BLOCO - Desenvolvimento Android [18E2-18E3] - Instituto Infnet.
Christian Vajgel - 25/09/2018 - Android Studio - Conceito DML (10/10).
Classe SiteAdapter

Google Play -> https://play.google.com/store/apps/details?id=br.edu.infnet.pingpong
Android 6.0+

Christian Vajgel
christian.vajgel@al.infnet.edu.br
linkedin.com/in/christianvajgel/

All Rights Reserved.
*/

package br.edu.infnet.pingpong;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SiteAdapter extends ArrayAdapter <Site> {

    private Context contexto;

    public SiteAdapter (@NonNull Context context, List<Site> sites) {
        super(context, R.layout.listview_websites_itens, sites);
        this.contexto = context;
    }

    public void setSiteStatus(int position, boolean status){
        Site site = getItem(position);
        site.setOnlineStatus(status);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Site site = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from((contexto)).inflate(R.layout.listview_websites_itens, parent, false);
        }
        TextView siteNome = convertView.findViewById(R.id.website_item);
        TextView siteStatus = convertView.findViewById(R.id.website_status);
        siteNome.setText(site.getSiteNome());
        siteStatus.setText(site.getSiteStatus());
        if (site.isOnline()){
            siteStatus.setTextColor(contexto.getColor(R.color.cor_verde));
        } else {
            siteStatus.setTextColor(contexto.getColor(R.color.cor_vermelha));
        }
        return convertView;
    }
}
