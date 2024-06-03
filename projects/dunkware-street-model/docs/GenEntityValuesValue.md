

# GenEntityValuesValue

## oneOf schemas
* [BigDecimal](BigDecimal.md)
* [Boolean](Boolean.md)
* [Integer](Integer.md)
* [String](String.md)

## Example
```java
// Import classes:
import com.dunkware.street.model.GenEntityValuesValue;
import com.dunkware.street.model.BigDecimal;
import com.dunkware.street.model.Boolean;
import com.dunkware.street.model.Integer;
import com.dunkware.street.model.String;

public class Example {
    public static void main(String[] args) {
        GenEntityValuesValue exampleGenEntityValuesValue = new GenEntityValuesValue();

        // create a new BigDecimal
        BigDecimal exampleBigDecimal = new BigDecimal();
        // set GenEntityValuesValue to BigDecimal
        exampleGenEntityValuesValue.setActualInstance(exampleBigDecimal);
        // to get back the BigDecimal set earlier
        BigDecimal testBigDecimal = (BigDecimal) exampleGenEntityValuesValue.getActualInstance();

        // create a new Boolean
        Boolean exampleBoolean = new Boolean();
        // set GenEntityValuesValue to Boolean
        exampleGenEntityValuesValue.setActualInstance(exampleBoolean);
        // to get back the Boolean set earlier
        Boolean testBoolean = (Boolean) exampleGenEntityValuesValue.getActualInstance();

        // create a new Integer
        Integer exampleInteger = new Integer();
        // set GenEntityValuesValue to Integer
        exampleGenEntityValuesValue.setActualInstance(exampleInteger);
        // to get back the Integer set earlier
        Integer testInteger = (Integer) exampleGenEntityValuesValue.getActualInstance();

        // create a new String
        String exampleString = new String();
        // set GenEntityValuesValue to String
        exampleGenEntityValuesValue.setActualInstance(exampleString);
        // to get back the String set earlier
        String testString = (String) exampleGenEntityValuesValue.getActualInstance();
    }
}
```


