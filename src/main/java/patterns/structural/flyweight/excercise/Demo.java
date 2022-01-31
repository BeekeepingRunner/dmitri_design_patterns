package patterns.structural.flyweight.excercise;
import java.util.*;

class Sentence
{
    String plainText;
    SortedSet<WordToken> words = new TreeSet<>(Comparator.comparingInt(w -> w.index));

    public Sentence(String plainText)
    {
        this.plainText = plainText;
    }

    public WordToken getWord(int wordIndex)
    {
        WordToken token = new WordToken(wordIndex);
        this.words.add(token);
        return token;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        Iterator<WordToken> wordIterator = this.words.iterator();
        WordToken word;
        if (wordIterator.hasNext()) {
            word = wordIterator.next();
        } else {
            return plainText;
        }

        int currentWord = 0;
        int plainTextLength = plainText.length();
        for (int i = 0; i < plainTextLength; ++i)
        {
            if (currentWord == word.index)
            {
                do {
                    char c = plainText.charAt(i);
                    sb.append(word.capitalize ? Character.toUpperCase(c) : c);
                    ++i;
                } while (i < plainTextLength && plainText.charAt(i) != ' ');

                if (i == plainTextLength)
                    break;

                sb.append(' ');
                ++currentWord;
                if (wordIterator.hasNext())
                    word = wordIterator.next();

            }
            else
            {
                do {
                    sb.append(plainText.charAt(i));
                    ++i;
                } while (i < plainTextLength && plainText.charAt(i) != ' ');

                if (i == plainTextLength)
                    break;

                sb.append(' ');
                ++currentWord;
            }
        }

        return sb.toString();
    }


    class WordToken
    {
        int index;
        public boolean capitalize;

        public WordToken(int index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WordToken)) return false;
            WordToken token = (WordToken) o;
            return index == token.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }
}

public class Demo {

    public static void main(String[] args) {

        Sentence s = new Sentence("hello world yay man thats cool");
        s.getWord(1).capitalize = true;
        s.getWord(3).capitalize = true;
        System.out.println(s);
    }
}
