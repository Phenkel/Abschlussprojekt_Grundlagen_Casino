package Blackjack

// Klasse, die ein Kartendeck für das Blackjack-Spiel repräsentiert
class Deck(var deck: MutableList<Card> = mutableListOf()) {
    // Der Konstruktor der Klasse wird beim Erstellen eines Deck-Objekts aufgerufen
    init {
        repeat(6) { // Erstellt 6 Kartenspiele (typischerweise besteht ein Blackjack-Deck aus 6 Kartenspielen)
            for (suit in Suit.values()) { // Durchlaufe alle Kartenfarben (Suit) im Enum Suit
                for (rank in Rank.values()) { // Durchlaufe alle Kartenwerte (Rank) im Enum Rank
                    deck.add(Card(suit, rank)) // Füge eine Karte mit der aktuellen Farbe und dem aktuellen Wert zum Deck hinzu
                }
            }
        }
        deck.shuffle() // Mische das Deck, um die Karten zufällig anzuordnen
    }

    // Methode zum Ziehen einer Karte aus dem Deck
    fun cardDraw(): Card {
        return deck.removeAt(0) // Entferne die erste Karte aus dem Deck und gib sie zurück (Deck wird wie ein Stapel behandelt)
    }

    // Methode zum Wechseln des Decks (wird normalerweise verwendet, wenn das Deck fast leer ist)
    fun deckChange() {
        println("Die Decks werden gewechselt!") // Ausgabe einer Meldung, dass die Decks gewechselt werden
        deck.clear() // Entferne alle Karten aus dem Deck
        repeat(6) { // Erstelle erneut 6 Kartenspiele und füge sie zum Deck hinzu
            for (suit in Suit.values()) { // Durchlaufe alle Kartenfarben (Suit) im Enum Suit
                for (rank in Rank.values()) { // Durchlaufe alle Kartenwerte (Rank) im Enum Rank
                    deck.add(Card(suit, rank)) // Füge eine Karte mit der aktuellen Farbe und dem aktuellen Wert zum Deck hinzu
                }
            }
        }
        deck.shuffle() // Mische das Deck erneut, um die Karten zufällig anzuordnen
    }
}