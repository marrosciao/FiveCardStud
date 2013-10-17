package fivecardstud;
import java.util.Arrays;

public class Hand {
    Card[] cards;
    Card highCard;
    String handName;
    String[] suits;
    String suitTemp;
    int player;
    int rank;
    int valueTemp;
    int[] values;
    
    public Hand(Card[] dealt, int player){
        this.cards = deal(Status.handSize, Status.numPlayers);
        this.player = player;
        this.suits = new String[Status.handSize];
        this.values = new int[Status.handSize];
        
        // create array of suits and array of values for comparrisons in ranking
        for (int i=0; i<Status.handSize; i++){
            this.suits[i] = this.cards[i].suit;
            this.values[i] = this.cards[i].value;
        }
        Arrays.sort(this.suits);
    }
    
    public static Card[] deal(int toDeal, int players){
        Card[] hand;
        hand = new Card[toDeal];

        // check to see if card is already dealt
        int i = 0, j = 0;
        while (j<toDeal){
            // if card already used...iterate
            if(Deck.usedCardIndex.contains(i)){
                i++;
            // if card is unused, use it, add to used list, iterate all
            } else {
                Deck.usedCardIndex.add(i);
                hand[j] = FiveCardStud.deck.deck[i];
                i+=players;
                j++;
            }
        }
        return hand;
    }
    
    private boolean getHighCard(){
        for (Card card: this.cards) {
            if (card.value > this.highCard.value) this.highCard = card;
        }
        return false;
    }
 
    private boolean checkFlush(){
        suitTemp = this.suits[0];
        for (String suit: this.suits) {
            if (suitTemp.equals(suit)) {
                suitTemp = suit;
            } else {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkStrait(){
        valueTemp = this.values[0];
        for (int value: this.values) {
            if (valueTemp == value) {
                valueTemp = value;
            } else {
                return false;
            }
        }
        return true;
    }
    
    // rank hands from 0 being the worst and up to the best
    public int evaluate(){

        return this.rank;
    }
}
