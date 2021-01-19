# Checkpoints for the semestrial project  

---



## Výsledné SRS však musí nutně obsahovat

- [x] Pro jaké uživatele (druhy uživatelů) bude systém určen (je nutné mít alespoň 2 funkčně různé uživatele)
- [x] Jaké bude plnit funkce (k čemu bude jednotlivým druhům uživatelů sloužit)
- [x] Jaká bude mít systém omezení (k čemu sloužit nebude, ačkoliv by to mohlo být očekáváno)
- [x] Objektový model (UML class diagram)
- [x] minimálně 5 tříd - několik vazeb 1:N a minimálně jedna vazba M:N
- [x] část funkcionality. Zamyslete se nad tím (a v dokumentu zřetelně vyznačte) (EDITOR)


## Zárodek aplikace obsahující:

- [x] Projekt kompilovatelný Mavenem (Gradle)
- [x] JPA entity odpovídající doménovému modelu
  - [x] alespoň `@MappedSuperClass`
- [x] DAO vrstva
- [x] Netriviální část business logiky propojené pomocí dependency injection (tedy ne pouze čistý CRUD)
- [x] Netriviální testy existující části aplikace. Mirne. Dost jich padlo pri premapovani domain entit. Jelikoz nove porusovaly constraints a neslo ani buildit, tak byly smazane, s tim, ze casem se dedelaji. Ale jelikoz cas neni, tak to dopadlo, jak to dopadlo. Je otestovana konfigurace a pruchod StateMachiny. Avasak to nepomohlo, protoze v Controlleru je machina nullpointer.

## Výsledná fungující aplikace splňující tyto podmínky

- [x] Projekt je sestavitelný Mavenem a build prochází včetně testů
- [x] Vyhovuje SRS vytvořené v rámci checkpointu 1
- [ ] Používá persistentní vrstvu, navrženou a schválenou v rámci checkpointu 1, obohacenou o alespoň tři z následujících technik/funkcí:
  - [x] Ordering (uspořádání kolekce podle atributu - `@OrderBy`
  - [x] Složené primární klíče - Review ma klic Manuscript, Reviewer
  - [x] Kaskádní persist/merge
- [ ] REST rozhraní využívající business logiku aplikace - mirne. Natvrdo napsane metody, pro ukazku fungovani. Plan byl, ze to obstara `State Machine` - nastavovani stavu, casu apod,  a `Observer` bude posilat emaily. `EventHandler` se stara o nastaveni zakladnich udaju u Manuscriptu pri vytvoreni.
- [x] Použít security podporu
 - [ ] funkcionalita aplikace podle role uživatele) - zakomentovano nebo smazano, jelikoz me to pak nepusti k nicemu jinemu nez `GET`. Z casove tisne neni doreseno.
- [x] Ověřitelnost funkcionality aplikace - sadou dotazu v  `Postman`

# Ukazky

## Pokusy o frontend v Reactu

![Editors dashboard](/img/editorsDashboardNew.png)

Dale nebylo rozvijeno. Neznam React, JWT prihlasovani se nepodarilo rozchodit. Ponechano do budoucna.

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
