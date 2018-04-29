# ELTECinema
Projekt eszközök beadandó

## Információ a futtatáshoz (IntelliJ IDEA-val tesztelve):

### Backend

- backend mappából a build.gradle fájl-t kell a fejlesztő környezetbe betallózni projekt importálása menüpont után.
- IntelliJ-n belül a File -> Settings -> Buil, Execution, Deployment -> Compiler -> Annotation Proccessor menüpontban engedélyezni kell az Annotation Proccessing-et, mert különben fordítási hibát kapunk.
- Ezek után futtathatjuk a backend részt.

### Frontend


1. command lineban a frontend mappájában:
  * npm install
  * npm install start
 
2. command lineban google chrome mappájában:
  * chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security
  * localhost:4200-as porton lehet ezután elérni az oldalt
