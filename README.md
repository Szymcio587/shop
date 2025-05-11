# Promocje dla metod płatności

Projekt został napisany z wykorzystaniem Javy 21.
Składa się z: 
- aplikacji głównej przyjmującej 2 argumenty w postaci ścieżek do plików i na ich podstawie oblicza wartości wydane dla 
poszczególnych metod
- trzech klas testowych, zbudowanych dla przetestowania kolejno wczytywania oraz obliczania wartości dla plików testowych podanych wraz z instrukcją

# Sposoby uruchomienia

Projekt można uruchomić poprzez:
- uruchomienie samego jara komendą java -jar shop.jar arg_1 arg_2
- uruchomienie skryptu PowerShella .\run.ps1 (argumenty są podawane wewnątrz skryptu jako $ordersFile i $paymentsFile)
- uruchomienie z wykorzystaniem IDE poprzez uruchomienie funkcji Main z podaniem argumentów w konfiguracji
Ponadto, uruchamiając .\run_tests.ps1 uruchamiane są trzy wyżej wspomniane klasy testowe 

# Algorytm

Złożoność obliczeniową algorytmu można streścić do poziomu O(m * n), natomiast złożoność pamięciową do O(m), 
gdzie m i n to kolejno rozmiary list z metodami płatności i zamówieniami.
Algorytm według mojej najlepszej wiedzy nie jest idealny i będę dawał błędne rezultaty dla pewnej grupy danych wejściowych, co przy moim zrozumieniu podanego problemu wydaje się nieuniknione.

Zakładając nawet prosty przykład, w którym mamy wyłącznie jedną metodę płatności i nie są to PUNKTY, oraz zbiór zamówień o różnych kosztach które nie muszą być liczbami całkowitymi, znalezienie optymalnej kwoty sprowadza się do znalezienia największej sumy zamówień nie przekraczającej limitu metody płatności, co w sposób dokładny da się obliczyć wyłącznie algorytmami o złożoności w pesymistycznych przypadkach sięgających wartości O(2^n), co przy 10000 zamówieniach jest w praktyce awykonalne. Nie uwzględniając więc nawet bardziej złożonych przypadków z większą liczbą metod, albo nie zrozumiałem czegoś w treści tego zadania albo jest ono niemożliwe do pełnego zrealizowania. 