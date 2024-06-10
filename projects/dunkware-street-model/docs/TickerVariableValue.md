

# TickerVariableValue

Value of the ticker variable, type depends on the dataType field

## oneOf schemas
* [BigDecimal](BigDecimal.md)
* [Boolean](Boolean.md)
* [GenDate](GenDate.md)
* [GenDateTime](GenDateTime.md)
* [GenTime](GenTime.md)
* [Integer](Integer.md)
* [String](String.md)

## Example
```java
// Import classes:
import com.dunkware.street.model.TickerVariableValue;
import com.dunkware.street.model.BigDecimal;
import com.dunkware.street.model.Boolean;
import com.dunkware.street.model.GenDate;
import com.dunkware.street.model.GenDateTime;
import com.dunkware.street.model.GenTime;
import com.dunkware.street.model.Integer;
import com.dunkware.street.model.String;

public class Example {
    public static void main(String[] args) {
        TickerVariableValue exampleTickerVariableValue = new TickerVariableValue();

        // create a new BigDecimal
        BigDecimal exampleBigDecimal = new BigDecimal();
        // set TickerVariableValue to BigDecimal
        exampleTickerVariableValue.setActualInstance(exampleBigDecimal);
        // to get back the BigDecimal set earlier
        BigDecimal testBigDecimal = (BigDecimal) exampleTickerVariableValue.getActualInstance();

        // create a new Boolean
        Boolean exampleBoolean = new Boolean();
        // set TickerVariableValue to Boolean
        exampleTickerVariableValue.setActualInstance(exampleBoolean);
        // to get back the Boolean set earlier
        Boolean testBoolean = (Boolean) exampleTickerVariableValue.getActualInstance();

        // create a new GenDate
        GenDate exampleGenDate = new GenDate();
        // set TickerVariableValue to GenDate
        exampleTickerVariableValue.setActualInstance(exampleGenDate);
        // to get back the GenDate set earlier
        GenDate testGenDate = (GenDate) exampleTickerVariableValue.getActualInstance();

        // create a new GenDateTime
        GenDateTime exampleGenDateTime = new GenDateTime();
        // set TickerVariableValue to GenDateTime
        exampleTickerVariableValue.setActualInstance(exampleGenDateTime);
        // to get back the GenDateTime set earlier
        GenDateTime testGenDateTime = (GenDateTime) exampleTickerVariableValue.getActualInstance();

        // create a new GenTime
        GenTime exampleGenTime = new GenTime();
        // set TickerVariableValue to GenTime
        exampleTickerVariableValue.setActualInstance(exampleGenTime);
        // to get back the GenTime set earlier
        GenTime testGenTime = (GenTime) exampleTickerVariableValue.getActualInstance();

        // create a new Integer
        Integer exampleInteger = new Integer();
        // set TickerVariableValue to Integer
        exampleTickerVariableValue.setActualInstance(exampleInteger);
        // to get back the Integer set earlier
        Integer testInteger = (Integer) exampleTickerVariableValue.getActualInstance();

        // create a new String
        String exampleString = new String();
        // set TickerVariableValue to String
        exampleTickerVariableValue.setActualInstance(exampleString);
        // to get back the String set earlier
        String testString = (String) exampleTickerVariableValue.getActualInstance();
    }
}
```


