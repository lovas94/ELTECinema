# ELTECinema
Projekt eszközök beadandó

## Információ a futtatáshoz (IntelliJ IDEA-val tesztelve):

### Backend

- backend mappából a build.gradle fájl-t kell a fejlesztő környezetbe betallózni projekt importálása menüpont után.
- IntelliJ-n belül a File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Proccessor menüpontban engedélyezni kell az Annotation Proccessing-et, mert különben fordítási hibát kapunk.
- Ezek után futtathatjuk a backend részt.

### Frontend


1. command lineban a frontend mappájában:
  * npm install
  * npm start
 
2. command lineban google chrome mappájában:
  * chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security
  * localhost:4200-as porton lehet ezután elérni az oldalt
  

Build system: Jenkins a backend részre. Localhoston fut, de elérhető jelenleg a http://8500632c.ngrok.io/ címen.
A szolgáltatás 8 óráig fut egyhuzamban, utána újra kell indítani, újraindítás után változik a cím, mindig frissítve lesz itt a leírásban. 
#### belépés: felhasználó: lovas94 jelszó: ++Projeszk++
