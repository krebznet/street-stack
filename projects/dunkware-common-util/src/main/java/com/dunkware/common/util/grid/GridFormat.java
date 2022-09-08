package com.dunkware.common.util.grid;

public enum GridFormat {
TEXT, // assume value is string 
PERCENT, // assumes value is double and formats like percent 2.4%
CURRENCY,  // assumes value is double and formats like $34.34
INTEGER, // assume value is integer and formatted with commas like 23,232,32

DATE, // don't worry about date yet 
TIME, // don't worry about time  yet 
DATETIME // don't worry about datetime yet 

}
