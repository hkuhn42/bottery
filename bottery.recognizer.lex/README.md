# bottery recognizer based on amazon lex

A recognizer which uses the amazon [lex](https://aws.amazon.com/de/lex) api to determine the intent of a text

## Setup

1. register at [luis](https://aws.amazon.com/de/lex). You need an amazon account for this.
2. create an app
3. follow the [getting started](https://docs.aws.amazon.com/lex/latest/dg/what-is.html)
4. Get the required details
5. Follow the example below or lookup the complete one int the examplesproject

## Example

add the region, alias, name secretKey and accessKeyId as properties in the Bot.properties

e.g. for the example

lex.aws.region      = eu-west-1
lex.aws.secretKey   = xxxxxxxxxxxxxxxx
lex.aws.accessKeyId = xxxxxxxxxxxxxxxx
lex.bot.alias       = FlowerOne
lex.bot.name        = OrderFlowers

     // setup bot   
    MyBot bot = new MyBot();
    // register LuisRecognizer  with bot
    bot.register(new LexRecognizer());
    // register bot with ConsoleConnector
    new ConsoleConnector().register(bot);
    
See the hello lex example in the examples project for more details