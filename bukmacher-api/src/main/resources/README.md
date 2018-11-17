Gotowe funkcjonalności do testów:
* test AsynchronicznaObslugaMeczyImplTest: 
test ma za zadanie sprawdzić funkcjonalności aktualizacji meczy 
i rozliczania typow, tworzony jest testowy uzytkownik na ktorego przypisujemy dwa typy - jeden bedzie wygrany, drugi nie,
oraz zmieniamy zakonczone mecze na nieropoczete, dodatkowo zmieniamy aktualna kolejke w tej lidze. Test ma za zadanie pokazać, iż 
następuje poprawna aktualizacja meczu, rozliczenia typu, oraz zmiana balansu konta uzytkownika

* Pobranie aktualnej kolejki w danej lidze:
GET `/liga/{idLigi}/pobierz-numer-kolejki` || 87 - LaLiga, 2 - Premier League
* Pobranie wydarzeń w danej kolejce: GET `/mecze/{idLigi}/{kolejka}`
* Zapisanie typu: POST `/typy/zapisz-typ`
przykładowy request: 

{
"idMeczu" : 27261,
"typMecz":"2",
"kurs":"1.45",
"stawka":250,
"idUzytkownika":1
}