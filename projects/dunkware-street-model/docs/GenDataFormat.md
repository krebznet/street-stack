

# GenDataFormat


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**formatType** | [**FormatTypeEnum**](#FormatTypeEnum) | The type of data formatting to apply. |  [optional] |
|**precision** | **Integer** | Number of decimal places for rounding (used with roundToDecimalPlaces). |  [optional] |
|**truncateLength** | **Integer** | Maximum length for truncating strings (used with truncateString). |  [optional] |
|**currencySymbol** | **String** | Symbol for currency formatting (used with currency). |  [optional] |
|**abbreviationStyle** | **String** | Style for abbreviating large numbers (used with abbreviateNumber). |  [optional] |



## Enum: FormatTypeEnum

| Name | Value |
|---- | -----|
| ROUNDTODECIMALPLACES | &quot;roundToDecimalPlaces&quot; |
| DISPLAYASPERCENT | &quot;displayAsPercent&quot; |
| ADDCOMMAS | &quot;addCommas&quot; |
| TRUNCATESTRING | &quot;truncateString&quot; |
| CURRENCY | &quot;currency&quot; |
| ABBREVIATENUMBER | &quot;abbreviateNumber&quot; |



