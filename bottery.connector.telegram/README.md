# bottery connector for telegram

A connector for telegram implemented with 
 
    https://github.com/pengrad/java-telegram-bot-api#creating-your-bot
          
The api key must be configured with the bot setting property <connector-name>.key
 e.g.
 telegram.key = xxxxx if telegram is the connector name (set with constructor)          

## Example

 So a basic Hello World example would look like:
 ```java
 public class HelloBot extends UniversalBot {

    public HelloBot(IConnector connector) {
        super(connector);

        setWelcomeDialog(new Utterance() {
            @Override
            public IModel<String> getText(IActivity request, ISession session) {
                return new Model<String>("Hello World");
            }
        });
    }

    public static void main(String[] args) {
        new HelloBot(new TelegramConnector("telegram"));
    }
}
```
with Bot.properties

```
telegram.key = xxxxxxxxxxxxxxxxxxxx
```