# bottery recognizer based on microsoft luis

A recognizer which uses the microsoft [luis](https://www.luis.ai) api to determine the intent of a text

## Setup

1. register at [luis](https://www.luis.ai). You need a microsoft account for this.
2. create an app
3. follow the [tutorial](https://docs.microsoft.com/en-us/azure/cognitive-services/luis/luis-quickstart-primary-and-secondary-data)
4. Get the apiId and subscriptionKey
5. Follow the example below or lookup the complete one int the examplesproject

## Example

add the appId and subscriptionKey as properties in the Bot.properties

e.g. for the example

luis.appId =xxxxxxxxxxxxxxxxxxxx
luis.subscriptionKey = xxxxxxxxxxxxxxxxxx

     // setup bot   
    MyBot bot = new MyBot();
    // register LuisRecognizer  with bot
    bot.register(new LuisRecognizer());
    // register bot with ConsoleConnector
    new ConsoleConnector().register(bot);
    
See the hello luis example in the examples project for more details