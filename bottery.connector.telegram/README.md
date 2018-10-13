# Bottery connectors for telegram

A connector for the [Telegram Messengers](https://telegram.org) [Bot Framework](https://core.telegram.org/bots) 
implemented with [pengards java api](https://github.com/pengrad/java-telegram-bot-api#creating-your-bot) 
 
## Getting started    

Before beeing able to use this api, you need to obtain an api key from telegram. This is done by using the BotFather 
bot. There is a very good explanation with the documentation of their [Bot Framework](https://core.telegram.org/bots).          

## Example

 So a basic Hello World example would look like:
 ```java
 public class HelloTelegram {

    public static void main(String[] args) {
 
        UniversalBot bot = new UniversalBot();
        bot.setWelcomeDialog(new Utterance() {
            @Override
            public IModel<String> getText(IActivity request, ISession session) {
                return new Model<String>("Hello World");
            }
        });
        
        TelegramBotConnector connector = new TelegramBotConnector();
        connector.register(bot);
    }
}
```
with Bot.properties

```
telegram.key = xxxxxxxxxxxxxxxxxxxx
```

where xxxxxxxxxxxxxxxxxxxx is the key you got from the BotFather

## Features

Currently only basic (text and basic attachments) features are supported but extensions are planned.

## Advanced

You can use multiple bot accounts for the  same bot with the connector  names (constuctor) matching the api key.
The api key must be configured with the bot setting property <connector-name>.key
 e.g.
 otherTelegramAccount.key = xxxxx if otherTelegramAccount is the connector name (set with constructor)          
