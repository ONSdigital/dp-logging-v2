# dp-logging-v2

## Examples

Initialize the logger:
```java
    // Create a new Logback logger instance
    Logger logger = LoggerFactory.getLogger("com.github.onsdigital.logging.v2.example");

    // Initialize the log lib passing in the logger and the EventSerializer impl you wish to use.
    LoggerConfig.init(logger, new JacksonEventSerializer(true));
```

Logging an event using the out of the box `SimpleEvent` logger
```
    new SimpleEvent("hello.world")
            .httpMethod("GET")
            .httpPath("/hello")
            .log("request received");
```

Outputs:
```
{
  "created" : 1548236505866,
  "namespace" : "hello.world",
  "http" : {
    "path" : "/hello",
    "method" : "GET"
  },
  "event" : "request received"
}
```

Extending `BaseEvent` to add custom fields & objects to the log event:
```java
 class ComplexEvent extends BaseEvent<ComplexEvent> {

        // specify a namespace
        public ComplexEvent(String namespace) {
            super(namespace);
        }

        // give the nested object a namespace so it will not clash with other apps.
        @JsonProperty("hello.world.data")
        private Map<String, Object> data = new HashMap<>();


        // add set methods to add your required fields
        public ComplexEvent user(String name) {
            this.data.put("name", name);
            return this;
        }

        public ComplexEvent collectionID(String id) {
            this.data.put("collectionID", id);
            return this;
        }
    }
```
Logging an event using the customised event type:
```java
    new ComplexEvent("hello.world")
            .httpMethod("GET")
            .httpPath("/hello")
            .user(req.params(":name"))
            .collectionID(req.params(":collectionID"))
            .log("request received");
```
Outputs:
```json
{
  "created" : 1548236618285,
  "namespace" : "hello.world",
  "http" : {
    "path" : "/hello",
    "method" : "GET"
  },
  "event" : "request received",
  "hello.world.data" : {
    "name" : "dave",
    "collectionID" : "666"
  }
}
```