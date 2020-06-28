

# Projekt na przedmiot Programowanie w języku Java
## Tytuł: Strategiczna Gra Turowa


## 1. Opis projektu.
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Strategiczna gra turowa opiera się głównie na turowym zarządzaniu jednostkami na planszy 10 na 10 podzielonej na kafelki. Gra jest przeznaczona dla dwóch osób. Gracz pierwszy (nr 1) posiada jednostki z barwą niebieską, a gracz drugi (nr 2) z czerwoną. Zostały użyte przeciwne kolory, aby ułatwić łatwe i szybkie rozpoznawanie swoich oraz wroga jednostek. Każdy z graczy posiada jedną akcję na turę, czyli może się poruszyć lub zaatakować dowolną swoją jednostką. Po tej akcji następuje tura przeciwnika. Każdy kafelek ma przydzielony punkt na planszy oraz zapamiętuje jaka jednostka się na nim znajduje. W obecnej chwili w grze występuje 6 jednostek: Warrior, Archer, Mercenary, Siege, Archer Tower, Barracks. Każda z nich posiada statystyki życia, ataku, pancerza, zasięg ruchu, ataku. 4 z nich są to jednostki piechoty i posiadają możliwość poruszania się po planszy. Ostatnie dwie Archer Tower oraz Barracks nie posiadają takiej możliwości, gdyż są to budynki. Gracz zwycięża, kiedy na planszy nie pozostaną żadne jednostki przeciwnika. Pojawia się okno ze zwycięzcom i przyciskiem, który odpowiada za powrót do menu głównego.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Grę rozpoczynamy w oknie reprezentującym menu główne, gdzie znajdują się 4 przyciski. Pierwszy z nich „New Game” odpowiada za otworzenie nowego okna wraz z planszą i rozpoczęcie gry. Przycisk ten nie działa jeżeli żadne jednostki nie zostały wcześniej ustawione. Oprócz widoku planszy w górnym rogu widnieje dwie zakładki. Pierwsza odpowiada za wyświetlenie planszy, a druga odpowiada za statystyki. Po wykonaniu każdego ruchu  statystyki powinny się aktualizować i pokazywać aktualne żywe jednostki wraz z ich życiem oraz siłą ataku. Drugi przycisk „Settings” w menu obecnie umożliwia ustawienie nazwy graczy. Trzeci przycisk „Create your map” otwiera nowe okno z edytorem mapy. W nim możemy ustawiać jednostki według naszych marzeń. Na dole okna widnieją wszystkie dostępne jednostki, po wciśnięci którejkolwiek z nich można  ustawiać je na planszy. Klikając dwukrotnie na jednostką na mapie możemy ją usunąć.  Klikając pierwsze na postać na mapie i drugi raz na postać na mapie zamienimy je miejscami. Po lewej stronie występuje przełącznik między armią gracza pierwszego lub drugiego. Także znajduje się tam przycisk „Return”, który umożliwia powrót do głównego menu. Ostatni przycisk „Quit” umożliwia wyłączenie okna menu głównego.

## Opis kodu:
* Plik „Main” zajmuje się tylko rozpoczęciem pracy. Wywołuje:  
* Plik „AppFrame” zajmuje się utworzeniem okna głównego menu i obsługą przycisków, które w nim występują. Inicjuje nowych  graczy oraz ich puste armie;  
* Plik „Menu” zajmuje się stworzeniem przycisków występujących w głównym menu oraz nasłuchuje na akacje związane z przyciskami;  
* Plik „Map panel” zajmuje się planszą gry. Implementuje listener odpowiedzialny za podświetlanie panelu mapy nad którym znajduje się aktualnie kursor;  
* Plik „Player” zajmuje się graczami oraz ich armiami;  
* Plik „Scoreboard” odpowiada za okno wyskakujące po wygranej informujące o wygranym graczu;  
* Plik „GameLogic” zajmuje się akcjami występującymi podczas gry na planszy. Obsługuje poruszanie się, atakowanie, wyświetlanie zasięgu ruchu lub ataku;  
* Plik „GameLayout” zajmuje się stworzeniem i aktualizowaniem okna oraz planszy. Obsługuje nasłuchiwanie myszy, początkowe rozstawienie armii, aktualizacje mapy (każdego kafelka) oraz tworzy dodatkowe okno wraz ze statystykami jednostek;  
* Plik „CreatorLogic” odpowiedzialny za akcje występujące po kliknięciu na planszę będąc w trybie dodawania jednostek do planszy;  
* Plik „CreatorMap” zajmuje się obsługą i tworzeniem interfejsu służącego do rozmieszczania jednostek, jednostki do wyboru są umieszczone w panelu, każda z nich stanowi prototyp każdej jednostki która następnie pojawia się na mapie;  
* Plik „Entity” tworzy klasę abstrakcyjną po której dziedziczą konkretniejsze już typy jednostek np. warrior;  
* Plik „EntityFactory” umożliwia stworzenie nowej jednostki wg wzorca projektowego fabryki;  
* Plik „Building” rozszerza „Entity” i obsługuje budowle;  
* Pliki „Archer”, „ArcherTower”, „Warrior”, „Barracks”, „Siege”, „Mercenary” rozszerza „MovingUnit” zajmują się danym rodzajem jednostek. Obsługują wzór ataku, ruchu (jeśli nie jest budynkiem) oraz  tworzenie danej jednostki wraz z statystykami podstawowymi;  
* Plik „MovingUnit” rozszerza „Entity” oraz implementuje interfejs „iMovable” odpowiada za jednostki, które mogą się poruszać po mapie. Ponadto zapewnia template w metodzie Move();  
* Plik „iLogic”,  interfejs odpowiedzialny za logikę gry;  
* Plik „iMovable” interfejs odpowiedzialny za poruszanie się postaci.  
## Testy:  
* Plik „CreatorLogicTest” sprawdza poprawność ustawiania jednostek w kreatorze rozgrywki;  
* Plik  „DamageTest”” sprawdza poprawność odejmowania życia podczas ataku;  
* Plik „MapPanelTest” sprawdza poprawność equals(), które jest odpowiedzialne za porównywanie kafelków mapy;  
* Plik „MoveEntityTest” sprawdza poprawność metody odpowiedzialnej za przemieszczenie jednostki;  
* Plik „AttackEntityTest” sprawdza poprawność metody odpowiedzialnej za atak na wskazany kafelek mapy, na którym znajduje się jednostka.  

## Twórcy:  
1.Kozik Robert  
2.Krasiński Mateusz  
3.Kurzak Krzysztof  
4.Kwieciński Michał  
