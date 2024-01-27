3 exchange-api
- aplikacja ma wystawiać endpointy, które pozwalają na uzyskanie
  informacji takich jak
    - wszystkie dostępne waluty (brak autentykacji)
    - informacje o kursach wskazanej waluty (brak autentykacji)
    - wymiana walut (wskazanie "z", "do", "kwota") (admin)
    - wymiana walut z potwierdzeniem (każdy użytkownik)
- aplikacja ma używać security i weryfikować użytkowników zgodnie
  z powyższą rozspiską usług.
- wysyłka na maila ma się odbywać na adres przypisany do użytkownika
  wysyłającego zapytanie i ma być wywoływana eventem.