# dp-logging-v2

## Examples

```java
public class API {

    public static void main(String[] args) {
        // Create a new Logback logger instance
        Logger logger = LoggerFactory.getLogger("com.github.onsdigital.logging.v2.example");

        // Initialize the log lib passing in the logger and the EventSerializer impl you wish to use.
        LoggerConfig.init(logger, new JacksonEventSerializer(true));

        // Create a simple Spark API route
        get("/hello", (req, res) -> {

            // Simple event is a ready to use log event with the mandatory fields
            new SimpleEvent("hello.world")
                    .httpMethod("GET")
                    .httpPath("/hello")
                    .log("request received");

            return "Hello World!";
        });

        // Create a slightly more complex Spark API route
        get("/hello/:name/:collectionID", (req, res) -> {

            // ComplexEvent is an example of how to extend the BaseEvent to add additional fields to your log messages.
            new ComplexEvent("hello.world")
                    .httpMethod("GET")
                    .httpPath("/hello")
                    .user(req.params(":name"))
                    .collectionID(req.params(":collectionID"))
                    .log("request received");

            return "Hello " + req.params(":name");
        });
    }

    // Extend the BaseEvent class
    static class ComplexEvent extends BaseEvent<ComplexEvent> {

        // specify a namespace
        public ComplexEvent(String namespace) {
            super(namespace);
        }

        // give the nested of a namespace so it will not clash with other apps.
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
}
```