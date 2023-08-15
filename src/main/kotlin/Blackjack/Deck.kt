package Blackjack

// Klasse, die das Kartendeck im Blackjack-Spiel repräsentiert.
class Deck(var deck: MutableList<Card> = mutableListOf()) {

    // Konstruktor, der das Deck initialisiert und mischt.
    init {
        repeat(6) { // Hier wird das Deck mit 6 Kartensätzen erstellt (typisch für Blackjack)
            for (suit in Suit.values()) {
                for (rank in Rank.values()) {
                    deck.add(Card(suit, rank)) // Jede Karte wird dem Deck hinzugefügt
                }
            }
        }
        deck.shuffle() // Das Deck wird gemischt, um die Karten zufällig anzuordnen
    }

    // Methode, um eine Karte aus dem Deck zu ziehen.
    fun cardDraw(): Card {
        return deck.removeAt(0) // Die erste Karte aus dem Deck wird entfernt und zurückgegeben
    }

    // Methode, um das Deck neu zu mischen.
    fun deckChange() {
        deck.clear() // Alle Karten im Deck werden entfernt
        repeat(6) { // Das Deck wird erneut mit 6 Kartensätzen aufgefüllt und gemischt
            for (suit in Suit.values()) {
                for (rank in Rank.values()) {
                    deck.add(Card(suit, rank)) // Jede Karte wird dem Deck hinzugefügt
                }
            }
        }
        deck.shuffle() // Das Deck wird erneut gemischt
    }
}