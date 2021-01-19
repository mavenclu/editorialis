#  Zpráva o projektu


## Popis aplikace a její struktury
Aplikace je postavena na `spring-data-rest`.
Build tool `Gradle`.
Database PostgreSQL.
Struktura:
* `domain` - entity, mapovani vztahu s pomoci JPA, validace pomoci JPA, Hibernate
* `dao` - @RepositoryRestResource ktery stavi na CrudRepository
*  `config` - nastaveni Spring State Machiny, jina nastaveni
  * `security` - nastaveni Spring security

* `events` - vyuziti jiz nabizenych moznosti pro usnadneni business logiky. (jen OnCreate u mauscriptu)
* `service` - zde osetreni navratu Optional z Repository, dao
  * email service
  * state machine service
  * UserDetailsService
* `web.controller`
  * @ReposotoryRestController. spring data rest vystavuje REST rozhrani domain entity. @RepositoryRestController dovoluje si pozmenit, rozsirit REST rozhrani. Pres hateoas se propoji entita vystavena spring data rest a ta moje.
  * @ControllerAdvice do budoucna, bude hezky zobrazovat chyby. Ted server 500 chyba, dlouha, user unfriendly.
* `application.properties` nastaveni emailu ze ktereho bude posilat aplikace, Data Source
* `import.sql` vlozeni pocatecnich dat do DB. Data jsou jiz s vazbami.


## Návod, jak aplikaci nainstalovat
Importovat jako gradle projekt. Pocatecni data jiz jsou nahrane do DB. Get funguje rovnou. Pro dalsi requesty je workaround v WebSecurityConfig u requestu nastavit permitAll(). Soucasne nastaveni je takove, ze nepusti nikam a nedovoli nic, kreome GETu. Hodiny jsou nad tim stravene, a bohuzel nevim, jak jsem si to tak nesikovne nastavila.

## Zkušenosti získané během této SP
I když jsem udělala vše podle dokumentace či tutoriálu, výsledek nebyl skoro nikdy jako inzerovaný. Ba naopak, skoro vždy to hodilo chybu, u které jsem vůbec nevěděla. Řešila jsem to tak, že jsem Googlila a prohledávala stackoverflow. Mně osobně řešeni chyb zabralo dost času. A taky co jednou už bylo opravené a fungovalo, po přidáni další funkcionality, už fungovat nemuselo. Celý ten proces byl plán, tutoriál, vyvíjení, rozbíjení, opravy, další vývoj, chyby, hledaní příčin chyb, zkoušení, ladění.
Zpětně ale musím říct, že na těch chybách jsem se naučila nejvíc. Když něco zafungovalo hned, tak jsem se nad tím nepozastavila. Kdežto u chyb, tam jsem musela hledat souvislosti, snažit se pochopit, jak to funguje, proč je tam chyba a jak ji opravit.

Vyzkoušela jsem si `spring data rest`. Musím říct, ze jsem z toho hodně nadšená, že něco takového vymyslely, a jak to funguje. Ale řekla bych, že pro mě, jako začátečníka, to bylo dost náročné. Základní funkcionalita je hned, jen dependency a mapovani entit. Ale napojit na to business logiku, přidat funkcionalitu, rozšířit, to už vyžaduje hlubší znalosti jak springu, tak spring data, tak RESTu. Je možné hodně toho přidat jen pres anotace, konfigurace. Ale na to už jsou potřeba hlubší znalosti, a i nějaké zkušenosti.
Hal Browser je zábava.
Naučila jsem se mazat cookie v Postmanu.
Zjistila jsem, ze nemam reálné odhady náročnosti implementace funkcionality. Návod může vypadat sebevíc jednoduchý a pochopitelný, ale vždy je třeba počítat, ze tam padnou chyby, a bude nějakou dobu trvat, než pochopím, co je to za chybu a jak ji vyřešit.
Vytvořila jsem si DB v terminálu pres psql, napojila se v IDEAi do DB.
