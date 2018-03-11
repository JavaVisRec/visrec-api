import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PokerHand
{
    public enum Result { TIE, WIN, LOSS }

    Card[] cards;
    String hand;
    Category[] categories;

    PokerHand(String hand)
    {
        cards = new Card[5];
        this.hand = hand;
        String[] cardStrings = hand.split(" ");
        for(int i = 0; i < 5 ; i++) {
            cards[i] = new Card(cardStrings[i]);
        }
        Arrays.sort(cards);
        categories = new Category[9];
        categories[0] = new StraightFlush();
        categories[1] = new FourOfAKind();
        categories[2] = new FullHouse();
        categories[3] = new Flush();
        categories[4] = new Straight();
        categories[5] = new ThreeOfAKind();
        categories[6] = new TwoPair();
        categories[7] = new OnePair();
        categories[8] = new HighCard();
    }

    public Card[] getCards() {
        return cards;
    }

    public Result compareWith(PokerHand hand) {
        Category myCategory = getCategory();
        Category otherCategory = hand.getCategory();

        if (myCategory.compareTo(otherCategory) < 0)
            return Result.LOSS;
        else if (myCategory.compareTo(otherCategory) > 0)
            return Result.WIN;

        int c = myCategory.compareToSameCategory(getCards(), hand.getCards());

        if (c < 0) {
            return Result.LOSS;
        } else if (c > 0) {
            return Result.WIN;
        }

        return Result.TIE;
    }

    public Category getCategory() {
        for(Category category: categories) {
            if (category.isMatch(cards))
                return category;
        }
        return new HighCard();
    }

    private boolean isStraightFlush() {
        return false;
    }

    public class Card implements Comparable{
        private char suit;
        private int value;

        public Card(String cardString) {
            suit = cardString.charAt(1);
            char val = cardString.charAt(0);
            switch (val) {
                case 'T':
                    value = 10;
                    break;
                case 'J':
                    value = 11;
                    break;
                case 'Q':
                    value = 12;
                    break;
                case 'K':
                    value = 13;
                    break;
                case 'A':
                    value = 14;
                    break;
                default:
                    value = val - 48;
                    break;
            }
        }

        public int compareTo(Object o) {
            return Integer.compare(value, ((Card)o).value);
        }

        @Override
        public String toString() {
            return "Card{" + "suit=" + suit + ", value=" + value + '}';
        }
    }

    public abstract class Category implements Comparable {
        public abstract int getRank();
        public abstract boolean isMatch(Card[] cards);
        public abstract int compareToSameCategory(Card[] myCards, Card[] otherCards);

        @Override
        public int compareTo(Object o) {
            return Integer.compare(((Category) o).getRank(), getRank());
        }

        public Map<Character, Integer> countMaxSameSuit(Card[] cards) {
            Map<Character, Integer> map = new HashMap<>();
            for (Card card: cards) {
                    map.put(card.suit, map.getOrDefault(card.suit, 0) + 1);
            }
            char maxSuit = ' ';
            int maxCount = Integer.MIN_VALUE;
            for (Character key : map.keySet()) {
                if (maxCount < map.get(key)) {
                    maxSuit = key;
                    maxCount = map.get(key);
                }
            }
            Map<Character, Integer> resultMap = new HashMap<>();
            resultMap.put(maxSuit, maxCount);
            return resultMap;
        }

        public Map<Integer, Integer> countMaxSameValue(Card[] cards) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Card card: cards) {
                map.put(card.value, map.getOrDefault(card.value, 0) + 1);
            }
            int maxValue = ' ';
            int maxCount = Integer.MIN_VALUE;
            for (Integer key : map.keySet()) {
                if (maxCount < map.get(key)) {
                    maxValue = key;
                    maxCount = map.get(key);
                }
            }
            Map<Integer, Integer> resultMap = new HashMap<>();
            resultMap.put(maxValue, maxCount);
            return resultMap;
        }

        public Map<Integer, Integer> groupByValue(Card[] cards) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Card card: cards) {
                map.put(card.value, map.getOrDefault(card.value, 0) + 1);
            }
            return map;
        }

        public int ofAKindComparision(Card[] myCards, Card[] otherCards) {
            Integer ofAKind = countMaxSameValue(myCards).keySet().iterator().next();
            Integer opponentOfAKind = countMaxSameValue(otherCards).keySet().iterator().next();
            int c = Integer.compare(ofAKind, opponentOfAKind);
            if (c != 0)
                return c;

            return highCardComparision(myCards, otherCards);
        }

        public int highCardComparision(Card[] myCards, Card[] otherCards) {
            for (int i = 4; i >= 0; i--) {
                if (myCards[i].value != otherCards[i].value)
                    return Integer.compare(myCards[i].value, otherCards[i].value);
            }
            return 0;
        }
    }

    public class StraightFlush extends Category {

        @Override
        public int getRank() {
            return 1;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            boolean sameSuit =  countMaxSameSuit(cards).values().iterator().next() == 5;
            if (!sameSuit)
                return false;

            for(int i = 1; i < 5; i++) {
                if (cards[i-1].value + 1 != cards[i].value)
                    return false;
            }
            return true;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return highCardComparision(myCards, otherCards);
        }
    }

    public class FourOfAKind extends Category {

        @Override
        public int getRank() {
            return 2;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            return countMaxSameValue(cards).values().iterator().next() == 4;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return ofAKindComparision(myCards, otherCards);
        }
    }

    public class FullHouse extends Category {

        @Override
        public int getRank() {
            return 3;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            Map<Integer, Integer> groupMap = groupByValue(cards);
            return groupMap.size() == 2;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return ofAKindComparision(myCards, otherCards);
        }
    }

    public class Flush extends Category {

        @Override
        public int getRank() {
            return 4;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            return countMaxSameSuit(cards).values().iterator().next() == 5;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return highCardComparision(myCards, otherCards);
        }
    }

    public class Straight extends Category {

        @Override
        public int getRank() {
            return 5;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            for(int i = 1; i < 5; i++) {
                if (cards[i-1].value + 1 != cards[i].value)
                    return false;
            }
            return true;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return highCardComparision(myCards, otherCards);
        }
    }

    public class ThreeOfAKind extends Category {

        @Override
        public int getRank() {
            return 6;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            Map<Integer, Integer> groupMap = groupByValue(cards);
            Integer myThreeOfAKind = countMaxSameValue(cards).values().iterator().next();
            return groupMap.size() == 3 && myThreeOfAKind == 3;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return ofAKindComparision(myCards, otherCards);
        }
    }

    public class TwoPair extends Category {

        @Override
        public int getRank() {
            return 7;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            Map<Integer, Integer> groupMap = groupByValue(cards);
            boolean threeGroups = groupMap.size() == 3;
            if (!threeGroups)
                return false;
            return groupMap.values().contains(1) && groupMap.values().contains(2);
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            Map<Integer, Integer> myGroupMap = groupByValue(cards);
            Map<Integer, Integer> otherGroupMap = groupByValue(otherCards);

            Set<Integer> myKeys = myGroupMap.keySet();
            Set<Integer> otherKeys = otherGroupMap.keySet();

            List<Integer> mySortedTwoPairs = new ArrayList<>();
            List<Integer> otherSortedTwoPairs = new ArrayList<>();
            Integer myHighCard = 0;
            Integer otherHighCard = 0;
            for (Integer myKey: myKeys) {
                if (myGroupMap.get(myKey) == 2)
                    mySortedTwoPairs.add(myKey);
                else
                    myHighCard = myKey;
            }
            for (Integer otherKey: otherKeys) {
                if (otherGroupMap.get(otherKey) == 2)
                    otherSortedTwoPairs.add(otherKey);
                else
                    otherHighCard = otherKey;
            }

            mySortedTwoPairs.sort(Integer::compareTo);
            otherSortedTwoPairs.sort(Integer::compareTo);

            int c = Integer.compare(mySortedTwoPairs.get(1), otherSortedTwoPairs.get(1));
            if (c != 0)
                return c;

            c = Integer.compare(mySortedTwoPairs.get(0), otherSortedTwoPairs.get(0));
            if (c != 0)
                return c;

            c = Integer.compare(myHighCard,otherHighCard);
            if (c != 0)
                return c;

            return 0;
        }
    }

    public class OnePair extends Category {

        @Override
        public int getRank() {
            return 8;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            return countMaxSameValue(cards).values().iterator().next() == 2;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return ofAKindComparision(myCards, otherCards);
        }
    }

    public class HighCard extends Category {

        @Override
        public int getRank() {
            return 9;
        }

        @Override
        public boolean isMatch(Card[] cards) {
            return true;
        }

        @Override
        public int compareToSameCategory(Card[] myCards, Card[] otherCards) {
            return highCardComparision(myCards, otherCards);
        }
    }
}
