# Ukazky



## Postman    



![All manusscripts from DB](/img/allManuscripts.png)

`POST` novy Manuscript pres `HAL Browser`  

![Novy Manuscript HAl Browser](/img/postNewManuscript.png)

![Novy manusscript v DB](/img/newManInDB.png)

`OnCreate EventHandler` posle email editorovi.  
Zamer byl, ze dle kategorie noveho manusscriptu bude mu automaticky prirazen editor, zodpovedny za danou kategorii.
editorovi se automaticky odesle email. A u manuscriptu se nastavi cas, kdy byl odeslan, kdy prirazen k editorovi. Ale u `POST` noveho manuscriptu mu vypadla kategorie, tak pro ukazku je tam natvrdo vybran editor.

![email editorovi](/img/newManEditorEmail.png)

Ukazka prirazeni reviewera k manuscriptu. Zvoleny manuscript s ID 1, ten s migrenou v nazvu. A priradim reviewera s ID 1.

![prirazei manuscriptu k revieweru](/img/assignToReviewer.png)

Autorovi a reviewerovi prijdou emaily o teto skutecnosti.
Zamer byl, ze je a taky board of direcors bude notifikovat `Observer`. Z casove tisne zatim neni implementovano. V dane chvili servisa posila emaily.

![Emaily o prirazeni reviewera](/img/emailsManAssignedToReviewer.png)

![Email autorovi](/img/emailToAuthor.png)

![Email reviewerovi](/img/emailToReviewer.png)

Vyhledam reviewera s ID 1 a podivam se na jeho manuscripty

![Reviewerovi manuscripty](/img/reviewerGetManuscripts.png)

Ukazka zamitnuti Manuscriptu s ID 9.

![zamitnuti manuscriptu](/img/rejectManuscript.png)

Zde je mozne si vsimnout, ze manuscript ma stav `REJECTED`. A take manuscript s ID 1, o migrene, ma stav `PEER_REVIEW`, Na obrazku vsech manuscriptu v dashboardu z frontendu v Reactu je videt, ze mel stav PENDING.

![zamitnuti v db](/img/rejectedInDB.png)

![email autorovi o zamitnuti](/img/emailToAuthorRejected.png)

Ukazka dotazovani se neprihlaseneho a prihlasenych uzivatelu.
Tady je videt krasna odpoved serveru. Tato je myslim vygenerovana automaticky `Spring security`. Ne vsechny chyby, vyjimky apod jsou takto osetrene. Napr. `POST` noveho manuscriptu s nevalidnim nazvem hodi `500` chybu serveru a osklivou dlouhou odpoved o chybe. To je tim, ze chybi osetreni takovych situaci. Je treba jeste doimplementovat `@ControllerAdvice` a `ExceptionHandler`.

![neprihlaseny uzivatel](/img/unauth.png)



![prihlaseny uzivatel editor](/img/auth.png)  


![prihlaseny autor](/img/auth2.png)
