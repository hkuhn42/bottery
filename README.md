# Bottery
A Java based framework for chatbots [![Build Status](https://travis-ci.org/hkuhn42/bottery.svg?branch=master)](https://travis-ci.org/hkuhn42/bottery)

The goal of this framework is to provide an api for writing vendor neutral conversational (chat and voice) systems and bots. 
Support for massaging  frameworks and intent recognition are provided by plugins. The goal is to provide implementations for all 
major frameworks and apis to allow writing cross vendor and platform neutral bots. 

This project still in alpha state so expect api changes.

## License
Bottery is licensed under the [Apache License v2](LICENSE)


## Features
- basic framework supports pluggable "cross vendor" bots
- i18n support with rincl
- text template support with mustache
- console connector for testing
- microsoft bot framework connector
- telegram connector
- discord connector
- gitter connector
- Bot2Bot Connector for unit tests
- notifiers to trigger activities (e.g. by time or after another thread finished)
- microsoft luis intent recognizer
- dialogflow recognizer
- deeplearning4j recognizers

See the [support matrix](https://github.com/hkuhn42/bottery/tree/master/docs/SupportMatrix.md) for more details 

There is also a basic [changelog](https://github.com/hkuhn42/bottery/tree/master/docs/Changelog.md) and a list of [planned changes and features](https://github.com/hkuhn42/bottery/tree/master/docs/Planned.md) for more details

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

There are more examples in a seperate subproject bottery.examples


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

## Motivation

I looked into chatbots and ai for quite some time (among other reasons for some ideas i had for openhab see sylvani) and 
i realized that whatever api or framework i used,  i always ended up writing the evaluation business logic (turn my hue 
on and off) again. So i started to create an abstraction layer.   

## History
The original implementation was part of an effort to bring voice and text assistants to openhab and eclipse smarthome.
Some of the concepts for voice made it into the eclipse smarthome core and the rest eventually evolved into this framework.

## Name
The name is a combination of the words bot, battery and potter, make of it what you want :)

