# Bottery
A Java based framework for chatbots

The goal of this framework is to provide a api for writing vendor neutral chat (and voice) bots. Support for massaging 
frameworks and intent recognition are provided by plugins. The goal is to provide implementations for all major frameworks
and apis to allow writing cross vendor and platform neutral bots. 
Currently the projects tries to provide basic plumbing like i18n, message storage and 
configuration as well as a framework for simple scripted bots.   

This is still in alpha state so expect api changes

## Changelog


# 0.6.1
- New LUIS recognizer
- Refactoring of notifiers
- Basic  notifier based on executor framework

# 0.6
- Added connector registry and notifications
- Added connector registry to access connectors by channel
- Added notifiers to trigger activities (e.g. by time etc)
- New discord connector


## Features
- basic framework supports pluggable "cross vendor" bots
- i18n support with rincl
- text template support with mustache
- console connector for testing
- microsoft bot framework connector
- telegram connector
- discord connector
- Bot2Bot Connector for in unit tests
- notifiers to trigger activities (e.g. by time or after another thread finished)
- microsoft luis intent recognizer


## Getting started

There is a number of examples in the examples package however a very basic echo bot on the console takes no more than:

```java
    // create a UniversalBot
    UniversalBot bot = new UniversalBot();
    // set the welcome dialog 
    bot.setWelcomeDialog(new Utterance() {
            // use the text of the incoming activity as the answer for the response
            public IModel<String> getText(IActivity request, ISession session) {
                return new Model<String>(request.getText());
            }
        });
    }
    // connect the bot to the console
    new ConsoleConnector().register(bot);
}
```

## Core Concepts
The basic concepts of the framework are 
- Bot which acts as the application and controller
- Session which holds all user related information
- Connectors which handle receiving and sending of "messages"
- Activities which represent messages, status updates etc.
- Recognizers which extract the intent from the text of activities
- Dialogs which are parts of a conversation and are responsible for interpreting the users intent and creating answers
- Notifiers which are used to create Activities based on Triggers like a time or the completion of an async process 

All components except the session are designed to be stateless so instances can be resused among a number of chat sessions. All data related to the individual chat must be stored in the session.


Bottery borrows some of its concepts from the apache wicket web framework. 

## History
The original implementation was part of an effort to bring voice and text assistants to openhab and eclipse smarthome.
Some of the concepts for voice made it into the eclipse smarthome core and the rest eventually evolved into this framework.

## Name
The name is a combination of the words bot, battery and potter, make of it what you want :)
