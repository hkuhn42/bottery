# deeplearning4j based recognizers

Recognizers based on [deeplearning4j](https://deeplearning4j.org/) 

This package is more of a toolkit than a ready to use recognizer. It is primarily suited for use cases where you need 
full control over the recognition process (e.g. due to special needs or because you are using confidential data to train
the recognizers.

It offers a couple of base classes and utils as well as a default implementation of a questions and answers based system.

# Usage (QNA)

1. Prepare two files containing the questions and matching answers. The questions and answers are linked by line index in the file so the first question in the questions file matches the first answer in the answers file and so on.
(If you have these files on a website, take a look at QNADownloader)
Put them in under data/questions.txt and data/answers.txt
    
2. Train your model with these files using the QNATrainer. 
(There is a very good introduction to the concepts from Willem Meints on youtube. He also has an example with github. Thanks for that)

3. Put the trained model files (classifier, vocabulary and answers) into a folder and configure these files 

Bot.properties

dl4j.file.answers = data/answers.txt
dl4j.file.vocabulary = model/vocabulary.bin
dl4j.file.classifier = model/classifier.bin
 


## Sample
```java
// we use a universal bot
Universal bot = new Universal();
// and register our recognizer
bot.register(new QNARecognizer());
// we use the RecognizerSuggestionResponder which takes the suggested responses from the recognizer or the default anser
bot.setWelcomeDialog(new RecognizerSuggestionResponder("I am sorry, i do not know the answer to this"));
// register the bot with the ConsoleConnector
new ConsoleConnector().register(handler);
```