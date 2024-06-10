

# UIAction


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**type** | [**TypeEnum**](#TypeEnum) | Type of action or component to instantiate on the client. |  [optional] |
|**link** | **String** | URL or URI for navigation actions. Relevant when type is &#39;link&#39;. |  [optional] |
|**component** | **String** | Name of the React component to instantiate. Relevant when type is &#39;component&#39;. |  [optional] |
|**props** | **Map&lt;String, Object&gt;** | Properties to pass to the React component. These are dynamic and defined as needed per component. |  [optional] |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| LINK | &quot;link&quot; |
| COMPONENT | &quot;component&quot; |



