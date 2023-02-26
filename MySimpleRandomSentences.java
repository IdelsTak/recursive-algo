
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Syntax rules.
 *
 * <p>&nbsp;{@code sentence ::= simple_sentence [conjunction sentence]}<br>
 * &nbsp;{@code simple_sentence ::= noun_phrase verb_phrase}<br>
 * &nbsp;{@code noun_phrase ::= proper_noun | determiner [adjective] common_noun [who verb_phrase]}
 * <br>
 * &nbsp;{@code verb_phrase ::= intransitive_verb | transitive_verb noun_phrase | is adjective |
 * believes that simple_sentence}<br>
 * &nbsp;{@code conjunction ::= and | or | but | because}<br>
 * &nbsp;{@code proper_noun ::= Fred | Jane | Richard Nixon | Miss America}<br>
 * &nbsp;{@code common_noun ::= man | woman | fish | elephant | unicorn}<br>
 * &nbsp;{@code determiner ::= a | the | every | some}<br>
 * &nbsp;{@code adjective ::= big | tiny | pretty | bald}<br>
 * &nbsp;{@code intransitive_verb ::= runs | jumps | talks | sleeps}<br>
 * &nbsp;{@code transitive_verb ::= loves | hates | sees | knows | looks for | finds}<br>
 *
 * <p>This program implements these rules to generate random sentences.
 *
 * <p>The program generates and outputs one random sentence every <em>three seconds</em> until it is
 * halted (for example, by typing {@code Control-C} in the terminal window where it is running).
 */
public class MySimpleRandomSentences {
  private static final Logger LOG = Logger.getLogger(MySimpleRandomSentences.class.getName());
  private static final String[] CONJUCTIONS = {"and", "or", "but", "because"};
  private static final String[] PROPER_NOUNS = {"Fred", "Jane", "Richard Nixon", "Miss America"};
  private static final String[] COMMON_NOUNS = {"man", "woman", "fish", "elephant", "unicorn"};
  private static final String[] DETERMINERS = {"a", "the", "every", "some"};
  private static final String[] ADJECTIVES = {"big", "tiny", "pretty", "bald"};
  private static final String[] INTRANSITIVE_VERBS = {"runs", "jumps", "talks", "sleeps"};
  private static final String[] TRANSITIVE_VERBS = {
    "loves", "hates", "sees", "knows", "looks for", "finds"
  };

  public static void main(String[] args) {
    //Loop endlessly until the program
    //is halted externally, for example
    while (true) {
      //Create random sentences
      System.out.println(capitalize(randomSentence()) + ".\n");
      //Every 3 seconds
      try {
        Thread.sleep(3000);
      } catch (InterruptedException ex) {
        LOG.log(Level.INFO, "Routine was interrupted!");
      }
    }
  }

  static String randomSentence() {
    String randomSentence = randomSimpleSentence();
    
    if(shouldChooseOption()){
      randomSentence = randomSentence + " " + randomItem(CONJUCTIONS) + " " + randomSentence();
    }
    
    return randomSentence;
  }

  static String randomSimpleSentence() {
    return randomNounPhrase() + " " + randomVerbPhrase();
  }
  
  static String randomNounPhrase() {
    String randomNounPhrase;
    
    if(shouldChooseOption()) {
      randomNounPhrase = randomItem(PROPER_NOUNS);
    }else{
      randomNounPhrase = randomItem(DETERMINERS) 
                            + " " + (shouldChooseOption() ? randomItem(ADJECTIVES) + " " + randomItem(COMMON_NOUNS) : randomItem(COMMON_NOUNS))
                            + (shouldChooseOption() ? " who " + randomVerbPhrase() : "");
    }
    
    return randomNounPhrase;
  }
  
  static String randomVerbPhrase() {
    String randomVerbPhrase = null;
    
    List<Integer> phraseIndex = Arrays.asList(0, 1, 2, 3);
    Collections.shuffle(phraseIndex);
  
    switch(phraseIndex.get(0)) {
      case 0:
        randomVerbPhrase = randomItem(INTRANSITIVE_VERBS);
        break;
      case 1:
        randomVerbPhrase = randomItem(TRANSITIVE_VERBS) + " " + randomNounPhrase();
        break;
      case 2:
        randomVerbPhrase = "is " + randomItem(ADJECTIVES);
        break;
      case 3:
        randomVerbPhrase = "believes that " + randomSimpleSentence();
        break;
        default: throw new AssertionError();
    }
  
    return randomVerbPhrase;
  }
  
  private static boolean shouldChooseOption() {
    Random random = ThreadLocalRandom.current();
    //Get the result of AND operations on three random booleans
    return random.nextBoolean() && random.nextBoolean() && random.nextBoolean();
  }
  
  private static String randomItem(String[] stringsArr) {
    //Get an immutable copy of the provided array
    String[] copyOfArr = Arrays.copyOf(stringsArr, stringsArr.length);
    //Turn the array into a list
    List<String> list = Arrays.asList(copyOfArr);
    //Shuffle the list
    Collections.shuffle(list);
    //Get the first item from the shuffled list
    return list.get(0);
  }
  
  private static String capitalize(String s){
    return s == null ? s : s.substring(0, 1).toUpperCase() + s.substring(1);
  }
}
