# :file_folder: `Documentação` :portugal: :brazil:
# PingPong
## Make it Ping. Hope for the Pong.

<p align="center">
<img width="720" height="480" src="https://storage.googleapis.com/pingpongapp/ping_pong_logo_nome.jpg">
</p>

> Aplicativo Android "Ping Pong" desenvolvido como Projeto de Bloco no Bloco de Desenvolvimento Android <br/>
> PROJETO DE BLOCO - Desenvolvimento Android [18E2-18E3] - Instituto Infnet. <br/>
> Christian Vajgel - 25/09/2018 - Android Studio - Conceito DML (10/10). <br/>
> All Rights Reserved. <br/>

:raising_hand_man: Christian Vajgel <br/>
:e-mail: [E-mail](mailto:christian.vajgel@al.infnet.edu.br) <br/>
:globe_with_meridians: [LinkedIn](https://linkedin.com/in/christianvajgel/) <br/>

:link: [Google Play (Android 6.0+)](https://play.google.com/store/apps/details?id=br.edu.infnet.pingpong)<br/>

***

### :earth_americas: Essência
`Verificar status de um website com um toque.`

Desenvolvido com a ideia de ser uma ferramenta essencial no dia-a-dia de qualquer profissional de TI.
Com apenas um toque o link do website é adicionado na lista e a cada dez segundos a lista é atualizada, 
realizando novamente uma nova verificação e apresentando por meio de um círculo ao lado de cada link as 
cores Verde (on-line) ou Vermelha (off-line) que referenciam o estado atual de cada website.

### :man_technologist: Desenvolvimento

* IDE: Android Studio
* Metodologia: Scrum
* API: 23 (Android 6.0 Marshmallow)
* Testes: Emuladores, Samsung J2, Samsung S9 e Xiaomi Mi A1
* Linguagem: Java
* Requisição HTTP: Síncrona e Assíncrona.

### :hammer_and_wrench: Bibliotecas

* Inclusas no Android Studio e Java 
* [OKHTTP3](https://square.github.io/okhttp/)
* [Firebase](https://firebase.google.com/)

### :iphone: Design

* Fonte: Roboto Light.
* Cores: Azul vívido e branco.
* Princípio do seis.

### :zap: Versões

:ping_pong: **v 1.0 (teste)** <br/>

<p align="center">
<img width="363" height="604" src="https://storage.googleapis.com/pingpongapp/app_v1%20copy.jpg">
</p>

* Conceitos da ideia e design desenvolvidos. 
* Requisição Assíncrona.
* Usuário digita o link completo `prefixo + domínio + extensão do domínio` e toca no botão `check`.
* Retornava o `<body>` da página caso existisse o site e estivesse on-line.
* Não retornava qualquer informação, caso o site estivesse off-line, link inexistente ou inválido.
* Muitos erros: inputs inválidos quebravam o app, nada automatizado, concatenação automática de `http://` inexistente.
* Demora na resposta. <br/>

:ping_pong: **v 2.0 (teste)** <br/>

<p align="center">
<img width="390" height="648" src="https://storage.googleapis.com/pingpongapp/app_v2%20copy.jpg">
</p>

* Requisição Assíncrona.
* Retorno do `<body>` da página caso existisse o site e estivesse on-line foi trocado por um texto.
* Informava também se o link digitado era inválido por meio do texto exibido abaixo do botão `check`.
* Adicionado outros tons de azul para testes. <br/>

:ping_pong: **v 3.0 (teste)** <br/>

<p align="center">
<img width="390" height="645" src="https://storage.googleapis.com/pingpongapp/app_v3_1%20copy.jpg">
</p>

<p align="center">
<img width="390" height="645" src="https://storage.googleapis.com/pingpongapp/app_v3_2%20copy.jpg">
</p>

* Requisição Assíncrona.
* Novas cores e fontes foram sendo testadas também.
* Foi adicionado uma lista para prototipação de como ficaria ao final a ideia.
* Botão `check` foi substituído por um botão circular azul com o protocolo `HTTP` escrito nele. 
* O usuário entra com o link e toca no botão `HTTP`, após o app verifica se está on-line ou off-line e adiciona à lista com este círculo verde ao lado. Embora fosse sempre verde, descobriu-se que era possível juntá-los (link + círculo).
* Caso o link estivesse on-line, aparecia um bolão temporário na parte inferior (do Android: `Toast`) dizendo que estava on-line. 
* O prefixo `http://` do domínio foi adicionado como `hint (dica)` no campo de texto remetendo a ideia que já está incluso ao usuário e houve a concatenação automática do `http://` no campo de entrada do link, precisando apenas entrar com o `domínio + prefixo do domínio`. Caso o usuário entrasse com `https://`, `http://` ou `www.`, o aplicativo por meio de um `Toast` iria avisá-lo que o prefixo já está incluso, apagaria o texto e ele digitaria novamente sem qualquer prefixo. Isso foi possibilitado pela concatenação do prefixo na criação da URL da requisição no código do aplicativo. <br/>
* Porém o aplicativo continuava a aceitar qualquer texto como entrada e não dava nenhum retorno se o site estivesse off-line.
* O botão `Atualizar` nada fazia, a ideia naquele momento era fazer a lista atualizar o status dos sites e as cores dos círculos apenas quando o usuário tivesse acionado o botão, novamente prototipação.
* Design por caixas, botões com e sem sombras e Material Design começaram a serem testados. <br/>

:ping_pong: **v 4.0 (teste)** <br/>

<p align="center">
<img width="390" height="645" src="https://storage.googleapis.com/pingpongapp/app_v4%20copy.jpg">
</p>

* Requisição Assíncrona.
* As cores finais começaram a ser pensadas e teste foi iniciado.
* Layout da lista começou a parecer com o final.
* A cor vermelha foi escolhida de forma aleatória, ninguém enxergaria com branco.
* Os círculos foram reduzidos de tamanho seguindo influências de minimalismo.
* A fonte foi definida `Roboto`, fonte criada pelo [Google](https://fonts.google.com/specimen/Roboto) (Christian Robertson) e tendo por objetivo manter o estilo do sistema operacional e sua conexão com o Material Design e minimalismo. 
* O botão `Atualizar` foi substituído pelo `Adicionar` e realmente desta vez fazia alguma coisa, ele checava o site antes de adicionar e adicionava apenas se existisse, independente de estar on-line ou off-line.
* Caracteres e links inválidos não eram mais aceitos.
* Integração com Firebase apresentou problemas, a lista era carregada mas não iniciava a atualização dela. <br/>

:ping_pong: **v 5.0 (teste)** <br/>

<p align="center">
<img width="390" height="645" src="https://storage.googleapis.com/pingpongapp/app_v5%20copy.jpg">
</p>

* Requisição Assíncrona.
* Continuaram os esforços em direção ao design final, desta vez a grossura da fonte foi trocada para deixar o design mais fluido e leve.
* O tipo de layout foi trocado, adotando por base o layout recomendado pelas diretrizes da Google em Setembro de 2018, que era o Constraint Layout. Ajudou bastante pois em qualquer tela de smartphone o aplicativo ficava bem definido e organizado.
* Um aspecto da essência do projeto ainda não funcionava, a verificação do site se estivesse off-line. 
* Integração com Firebase foi resolvida, buscava a lista após inicialização do app porém atualizava apenas o primeiro site. <br/>

:ping_pong: **v 6.0 (teste) / v 1.0 (final)** :checkered_flag: <br/>

<p align="center">
<img width="360" height="640" src="https://storage.googleapis.com/pingpongapp/app_v6%20copy.png">
</p>

* Requisição Síncrona foi desenvolvida no lugar da Assíncrona por problemas na Thread de interação com usuário pois estava travando o app quando iniciava a verificação de status da lista de sites.
* Continuaram os esforços em direção ao design final, desta vez a grossura da fonte foi trocada para deixar o design mais fluido e leve.
* O tipo de layout foi trocado, adotando por base o layout recomendado pelas diretrizes da Google em Setembro de 2018, que era o Constraint Layout. Ajudou bastante pois em qualquer tela de smartphone o aplicativo ficava bem definido e organizado.
* Um aspecto da essência do projeto ainda não funcionava, a verificação do site se estivesse off-line. 
* Integração com Firebase foi resolvida, buscava a lista após inicialização do app porém atualizava apenas o primeiro site. 
* Site `nuvem.app` era apenas uma página web simples hospedada no Google Cloud Platform com intuito de simular um site off-line.<br/>

<p align="center">
<img width="720" height="479" src="https://storage.googleapis.com/pingpongapp/ping_pong_grafico_recursos%20copy.png">
</p>
