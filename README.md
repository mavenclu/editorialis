#### Checkpoints for the semestrial project  

checkpoint1 not ready for evaluation yet.

---

### Výsledné SRS však musí nutně obsahovat

- [x] Pro jaké uživatele (druhy uživatelů) bude systém určen (je nutné mít alespoň 2 funkčně různé uživatele)
- [x] Jaké bude plnit funkce (k čemu bude jednotlivým druhům uživatelů sloužit)
- [x] Jaká bude mít systém omezení (k čemu sloužit nebude, ačkoliv by to mohlo být očekáváno)
- [x] Objektový model (UML class diagram)
 - [x] minimálně 5 tříd - několik vazeb 1:N a minimálně jedna vazba M:N
- [ ] část funkcionality. Zamyslete se nad tím (a v dokumentu zřetelně vyznačte) (EDITOR)


### Zárodek aplikace obsahující:

- [x] Projekt kompilovatelný Mavenem (Gradle)
- [x] JPA entity odpovídající doménovému modelu
  - [x] dědičnost nebo alespoň `@MappedSuperClass`
- [ ] DAO vrstva (dodelat)
- [ ] Netriviální část business logiky propojené pomocí dependency injection (tedy ne pouze čistý CRUD)
- [ ] Netriviální testy existující části aplikace
