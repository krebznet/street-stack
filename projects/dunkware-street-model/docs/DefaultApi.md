# DefaultApi

All URIs are relative to *https://api.dunkware.com/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**apiExchangesExchangeIdSessionsGet**](DefaultApi.md#apiExchangesExchangeIdSessionsGet) | **GET** /api/exchanges/{exchangeId}/sessions | Get all sessions for a specific exchange |
| [**apiExchangesExchangeIdSessionsGetWithHttpInfo**](DefaultApi.md#apiExchangesExchangeIdSessionsGetWithHttpInfo) | **GET** /api/exchanges/{exchangeId}/sessions | Get all sessions for a specific exchange |
| [**apiExchangesExchangeIdTickersGet**](DefaultApi.md#apiExchangesExchangeIdTickersGet) | **GET** /api/exchanges/{exchangeId}/tickers | Get all tickers for a specific exchange |
| [**apiExchangesExchangeIdTickersGetWithHttpInfo**](DefaultApi.md#apiExchangesExchangeIdTickersGetWithHttpInfo) | **GET** /api/exchanges/{exchangeId}/tickers | Get all tickers for a specific exchange |
| [**apiExchangesExchangeIdTickersTickerIdSessionsGet**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdSessionsGet) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/sessions | Get all sessions for a specific ticker |
| [**apiExchangesExchangeIdTickersTickerIdSessionsGetWithHttpInfo**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdSessionsGetWithHttpInfo) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/sessions | Get all sessions for a specific ticker |
| [**apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/sessions/{sessionId}/stats | Get all stats for a specific ticker session |
| [**apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGetWithHttpInfo**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGetWithHttpInfo) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/sessions/{sessionId}/stats | Get all stats for a specific ticker session |
| [**apiExchangesExchangeIdTickersTickerIdStatsStatIdGet**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdStatsStatIdGet) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/stats/{statId} | Get a specific stat for a specific exchange ticker |
| [**apiExchangesExchangeIdTickersTickerIdStatsStatIdGetWithHttpInfo**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdStatsStatIdGetWithHttpInfo) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/stats/{statId} | Get a specific stat for a specific exchange ticker |
| [**apiExchangesExchangeIdTickersTickerIdVariablesGet**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdVariablesGet) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/variables | Get all variables for a specific ticker |
| [**apiExchangesExchangeIdTickersTickerIdVariablesGetWithHttpInfo**](DefaultApi.md#apiExchangesExchangeIdTickersTickerIdVariablesGetWithHttpInfo) | **GET** /api/exchanges/{exchangeId}/tickers/{tickerId}/variables | Get all variables for a specific ticker |
| [**apiExchangesGet**](DefaultApi.md#apiExchangesGet) | **GET** /api/exchanges | Get a list of all exchanges |
| [**apiExchangesGetWithHttpInfo**](DefaultApi.md#apiExchangesGetWithHttpInfo) | **GET** /api/exchanges | Get a list of all exchanges |



## apiExchangesExchangeIdSessionsGet

> List<ExchangeSession> apiExchangesExchangeIdSessionsGet(exchangeId)

Get all sessions for a specific exchange

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        try {
            List<ExchangeSession> result = apiInstance.apiExchangesExchangeIdSessionsGet(exchangeId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdSessionsGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |

### Return type

[**List&lt;ExchangeSession&gt;**](ExchangeSession.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of sessions for the exchange |  -  |
| **404** | Exchange not found |  -  |
| **500** | Internal server error |  -  |

## apiExchangesExchangeIdSessionsGetWithHttpInfo

> ApiResponse<List<ExchangeSession>> apiExchangesExchangeIdSessionsGet apiExchangesExchangeIdSessionsGetWithHttpInfo(exchangeId)

Get all sessions for a specific exchange

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.ApiResponse;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        try {
            ApiResponse<List<ExchangeSession>> response = apiInstance.apiExchangesExchangeIdSessionsGetWithHttpInfo(exchangeId);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdSessionsGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |

### Return type

ApiResponse<[**List&lt;ExchangeSession&gt;**](ExchangeSession.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of sessions for the exchange |  -  |
| **404** | Exchange not found |  -  |
| **500** | Internal server error |  -  |


## apiExchangesExchangeIdTickersGet

> List<TickerRef> apiExchangesExchangeIdTickersGet(exchangeId)

Get all tickers for a specific exchange

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        try {
            List<TickerRef> result = apiInstance.apiExchangesExchangeIdTickersGet(exchangeId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |

### Return type

[**List&lt;TickerRef&gt;**](TickerRef.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of tickers for the exchange |  -  |
| **404** | Exchange not found |  -  |
| **500** | Internal server error |  -  |

## apiExchangesExchangeIdTickersGetWithHttpInfo

> ApiResponse<List<TickerRef>> apiExchangesExchangeIdTickersGet apiExchangesExchangeIdTickersGetWithHttpInfo(exchangeId)

Get all tickers for a specific exchange

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.ApiResponse;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        try {
            ApiResponse<List<TickerRef>> response = apiInstance.apiExchangesExchangeIdTickersGetWithHttpInfo(exchangeId);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |

### Return type

ApiResponse<[**List&lt;TickerRef&gt;**](TickerRef.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of tickers for the exchange |  -  |
| **404** | Exchange not found |  -  |
| **500** | Internal server error |  -  |


## apiExchangesExchangeIdTickersTickerIdSessionsGet

> List<TickerSession> apiExchangesExchangeIdTickersTickerIdSessionsGet(exchangeId, tickerId)

Get all sessions for a specific ticker

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        try {
            List<TickerSession> result = apiInstance.apiExchangesExchangeIdTickersTickerIdSessionsGet(exchangeId, tickerId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdSessionsGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |

### Return type

[**List&lt;TickerSession&gt;**](TickerSession.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of sessions for the ticker |  -  |
| **404** | Ticker or Exchange not found |  -  |
| **500** | Internal server error |  -  |

## apiExchangesExchangeIdTickersTickerIdSessionsGetWithHttpInfo

> ApiResponse<List<TickerSession>> apiExchangesExchangeIdTickersTickerIdSessionsGet apiExchangesExchangeIdTickersTickerIdSessionsGetWithHttpInfo(exchangeId, tickerId)

Get all sessions for a specific ticker

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.ApiResponse;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        try {
            ApiResponse<List<TickerSession>> response = apiInstance.apiExchangesExchangeIdTickersTickerIdSessionsGetWithHttpInfo(exchangeId, tickerId);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdSessionsGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |

### Return type

ApiResponse<[**List&lt;TickerSession&gt;**](TickerSession.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of sessions for the ticker |  -  |
| **404** | Ticker or Exchange not found |  -  |
| **500** | Internal server error |  -  |


## apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet

> List<TickerStat> apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(exchangeId, tickerId, sessionId)

Get all stats for a specific ticker session

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        Integer sessionId = 56; // Integer | Numeric identifier of the ticker session
        try {
            List<TickerStat> result = apiInstance.apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet(exchangeId, tickerId, sessionId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |
| **sessionId** | **Integer**| Numeric identifier of the ticker session | |

### Return type

[**List&lt;TickerStat&gt;**](TickerStat.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of stats for the ticker session |  -  |
| **404** | Ticker, Exchange, or Session not found |  -  |
| **500** | Internal server error |  -  |

## apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGetWithHttpInfo

> ApiResponse<List<TickerStat>> apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGetWithHttpInfo(exchangeId, tickerId, sessionId)

Get all stats for a specific ticker session

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.ApiResponse;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        Integer sessionId = 56; // Integer | Numeric identifier of the ticker session
        try {
            ApiResponse<List<TickerStat>> response = apiInstance.apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGetWithHttpInfo(exchangeId, tickerId, sessionId);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdSessionsSessionIdStatsGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |
| **sessionId** | **Integer**| Numeric identifier of the ticker session | |

### Return type

ApiResponse<[**List&lt;TickerStat&gt;**](TickerStat.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of stats for the ticker session |  -  |
| **404** | Ticker, Exchange, or Session not found |  -  |
| **500** | Internal server error |  -  |


## apiExchangesExchangeIdTickersTickerIdStatsStatIdGet

> TickerStat apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(exchangeId, tickerId, statId)

Get a specific stat for a specific exchange ticker

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        Integer statId = 56; // Integer | Numeric identifier of the stat
        try {
            TickerStat result = apiInstance.apiExchangesExchangeIdTickersTickerIdStatsStatIdGet(exchangeId, tickerId, statId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdStatsStatIdGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |
| **statId** | **Integer**| Numeric identifier of the stat | |

### Return type

[**TickerStat**](TickerStat.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Details of the specific stat for the exchange ticker |  -  |
| **404** | Ticker, Exchange, or Stat not found |  -  |
| **500** | Internal server error |  -  |

## apiExchangesExchangeIdTickersTickerIdStatsStatIdGetWithHttpInfo

> ApiResponse<TickerStat> apiExchangesExchangeIdTickersTickerIdStatsStatIdGet apiExchangesExchangeIdTickersTickerIdStatsStatIdGetWithHttpInfo(exchangeId, tickerId, statId)

Get a specific stat for a specific exchange ticker

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.ApiResponse;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        Integer statId = 56; // Integer | Numeric identifier of the stat
        try {
            ApiResponse<TickerStat> response = apiInstance.apiExchangesExchangeIdTickersTickerIdStatsStatIdGetWithHttpInfo(exchangeId, tickerId, statId);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdStatsStatIdGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |
| **statId** | **Integer**| Numeric identifier of the stat | |

### Return type

ApiResponse<[**TickerStat**](TickerStat.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Details of the specific stat for the exchange ticker |  -  |
| **404** | Ticker, Exchange, or Stat not found |  -  |
| **500** | Internal server error |  -  |


## apiExchangesExchangeIdTickersTickerIdVariablesGet

> List<TickerVariable> apiExchangesExchangeIdTickersTickerIdVariablesGet(exchangeId, tickerId)

Get all variables for a specific ticker

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        try {
            List<TickerVariable> result = apiInstance.apiExchangesExchangeIdTickersTickerIdVariablesGet(exchangeId, tickerId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdVariablesGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |

### Return type

[**List&lt;TickerVariable&gt;**](TickerVariable.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of variables for the ticker |  -  |
| **404** | Ticker or Exchange not found |  -  |
| **500** | Internal server error |  -  |

## apiExchangesExchangeIdTickersTickerIdVariablesGetWithHttpInfo

> ApiResponse<List<TickerVariable>> apiExchangesExchangeIdTickersTickerIdVariablesGet apiExchangesExchangeIdTickersTickerIdVariablesGetWithHttpInfo(exchangeId, tickerId)

Get all variables for a specific ticker

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.ApiResponse;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        Integer exchangeId = 56; // Integer | Numeric identifier of the exchange
        Integer tickerId = 56; // Integer | Numeric identifier of the ticker
        try {
            ApiResponse<List<TickerVariable>> response = apiInstance.apiExchangesExchangeIdTickersTickerIdVariablesGetWithHttpInfo(exchangeId, tickerId);
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesExchangeIdTickersTickerIdVariablesGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **exchangeId** | **Integer**| Numeric identifier of the exchange | |
| **tickerId** | **Integer**| Numeric identifier of the ticker | |

### Return type

ApiResponse<[**List&lt;TickerVariable&gt;**](TickerVariable.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of variables for the ticker |  -  |
| **404** | Ticker or Exchange not found |  -  |
| **500** | Internal server error |  -  |


## apiExchangesGet

> List<ExchangeRef> apiExchangesGet()

Get a list of all exchanges

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            List<ExchangeRef> result = apiInstance.apiExchangesGet();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;ExchangeRef&gt;**](ExchangeRef.md)


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of exchanges |  -  |

## apiExchangesGetWithHttpInfo

> ApiResponse<List<ExchangeRef>> apiExchangesGet apiExchangesGetWithHttpInfo()

Get a list of all exchanges

### Example

```java
// Import classes:
import com.dunkware.street.ApiClient;
import com.dunkware.street.ApiException;
import com.dunkware.street.ApiResponse;
import com.dunkware.street.Configuration;
import com.dunkware.street.models.*;
import com.dunkware.street.api.DefaultApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://api.dunkware.com/v1");

        DefaultApi apiInstance = new DefaultApi(defaultClient);
        try {
            ApiResponse<List<ExchangeRef>> response = apiInstance.apiExchangesGetWithHttpInfo();
            System.out.println("Status code: " + response.getStatusCode());
            System.out.println("Response headers: " + response.getHeaders());
            System.out.println("Response body: " + response.getData());
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#apiExchangesGet");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Response headers: " + e.getResponseHeaders());
            System.err.println("Reason: " + e.getResponseBody());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

ApiResponse<[**List&lt;ExchangeRef&gt;**](ExchangeRef.md)>


### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | A list of exchanges |  -  |

