# bottery connector for atlassian stride

A connector for the [stride](https://stride.com) using the rest api

## Getting started    

Before beeing able to use this api, you need to register an application and optain the client secret and appId

# Long Version
Follow their developer [documentation](https://developer.atlassian.com/cloud/stride/getting-started/).

# Short tack

1. Register a stride account [here](https://stride.com)
2. Register an application [here](https://developer.atlassian.com/apps/create)
3. Obtain the client secret  and appId and cloudId
4. Setup a local server or run ngrok
5. fill in Bot.properties (see below)
6. Run your bot

!!FOR PRODUCTION: DO NOT FORGET TO ENCRYPT YOUR SECRET AND APPID!!

## Example

 So a basic Hello World example would look like:
 ```java
 public class HelloStride {

    public static void main(String[] args) {
 
        UniversalBot bot = new UniversalBot();
        bot.setWelcomeDialog(new Utterance() {
            @Override
            public IModel<String> getText(IActivity request, ISession session) {
                return new Model<String>("Hello World");
            }
        });
        
        StrideConnector connector = new StrideConnector();
        connector.init(bot.getBotConfig());
        connector.register(bot);
    }
}
```
with Bot.properties

```
# client if from atlassian developer account
stride.clientId = xxxxxxxxxxxxxxxx
# client secret from atlassian developer account
stride.clientSecret = xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
# cloudId of the atlassian cloud used
stride.cloudId = xxxxxxxxxxxxxxxxxxxxxx
# the url under which the local server is accesible from the internet (e.g. a ngrok url)
stride.publicUrl = xxxxxxxxxxxxxxxxxxxx
```

## Features

Currently only direct and mention text messages are supported but extensions are planned.
 
