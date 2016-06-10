# PatternGame
A simple game design based on OO Design Patterns

While going through "Head First: Design Patterns", I wanted to practice the different patterns. It seemed that a character creator was a good platform in order to try this out.

This list shows examples where different design patterns are used. This list does not show everywhere I used a pattern.

- Strategy Pattern - "com/cooper/creator/model/CharacterBase" class - All characters can attack and defend. However, the weapon and armor used to perform these actions can change at run time.
 
- Decorator Pattern - "com/cooper/creator/model/{WeaponDecorator|ArmorDecorator}" class - Weapons (such as "Dagger" and "Great Sword") can be augmented, changing both the base stats and names ("sword" + "swift" + "of the bear" = "swift sword of the bear"). Multiple augments (including redundant augments) can be placed on a single weapon/armor. The decorators add/change functionality of the base object, and share the same interface; therefore the decorator patterns works best.
 
- Singleton Pattern - "com/cooper/game/pool/ActivePool" class - The ActivePool class holds references to all active characters and rooms, and is delegated with the task of loading/unloading characters and rooms, and routing commands to characters and rooms (it does violate SRP, which will be dealt with later). As a single instance holds all references, and there should never be a seperate pool, this class is a great candidate for the singleton pattern. (at this time, it is made a singleton through being a bean that's passed around; it does make use of the standard design of a static constructor to enforce a singular instance) 
 
- Builder Pattern - "com/cooper/creator/builder/CharacterBuilder" class - The character builder takes a composite charater, which includes composite weapons, armor, and (coon to be) carryables, and uses the various components to build a complete character.
 
- Factory Pattern - "com/cooper/creator/builder/CarryableBuilderFactory" class - With this factory, different builders are called upon based upon what arguments are fed in.

- Template Pattern - "com/cooper/creator/builder/CarryableBuilderTemplate" class - The construction of Carryables, which can have decorators, follows a deneral logic path, with the exception of a few points. Those few points are abstracted out, and implemented in the concrete classes: WeaponBuilder and ArmorBuilder

- Abstract Factory and the Factory Pattern - "com/cooper/creator/factory/AbstractCarryableFactory<T extends Carryable>" abstract factory - There is a factory builder which returns a factory for either weapon or armor, as these two are constructed in a very similar manner.

- State Pattern - "com/cooper/game/interactive/DirtInteractive" class (and classes in the "dirt" folder - This object represents a piece of soil that can be planted on. The block has 4 states, which have different responses to the Interactive methods. The states have different transition conditions, including timed events and player interaction.

- Command Pattern - "com/cooper/game/interactive/Interactive" interface - This interface enforces that all interactive blocks hold to a simple, standard pattern for making commands. In this way, very complicated systems could be kept to a very simple interface.

 This list has unused patterns (which were shown in the book), along with potential ideas of how to make use of them.
 
- Observer Pattern - Once web pages are able to establish streams with the API, the observer pattern will be used to keep maps up to date. Each stream will have a thread that is an observer on its corresponding observable "Room".
 
- Facade and Proxy Patterns - If ever the code consumes another REST API, a RestConsumer class and a facade can make it appears as entities exists locally.
 
- Adapter Pattern - This may be usefull for Characters, as a different interface is needed if they are fighting, or training, or talking, etc. 

<b> About the Game itself </b>

In order to avoid feature-creep, and to avoid over zealous paralisis, I decided to use a method where I would have a single feature to work on. If, while I was working on that feature, I cam up with another idea or thought of a way to refactor things, I would create a new bullet point in a document. The doucment was split into three sections: completed, active, and future.

https://docs.google.com/document/d/1H9YTUnVtJisoRmlrCE6QdXmPY1DN3dTfzWFvPX2taCc/edit?usp=sharing

I quickly realized I had inadvertently started scrumming my work. So, I decided to start making use of GitHub's ticketing system. All features are now tracked in there.

To play the game remotely, you can go to http://JamesCooper.privatedns.org:8081. In this early a state of the game, I don't feel it necessary to purchase a domain (though I will if enough people are testing it for me). So, I am using freedns.org for the subdomain. At the same time, I'm having trouble getting gradle to run under sudo, and AWS' EC2 prevents port 80 from being used by non sudo processes. The eventual goal will prbably be to coninue using this port for the API, but move the static pages into node.js (like my previous site).

<b>How to Run the game locally</b>

If you want to run the game locally, you'll have to make a few changes. First is the easy change: in order to load in characters, the lines at the bottom of src/main/java/com/cooper/Application.java should be uncommented (you should recomment them after the first time the game is run). Second is the hard change: in src/main/resources/application.properties is a line that starts with "spring.data.mongodb.uri". You'll have to replace the value of this field with the destination of your own mongodb. I use mongolab, but you can also use a local installation of mongodb if you prefer. As this process is complicated, I feel doing a google search will serve you better in setting this up then any way i could explain it.

<b>Current Feature</b>

Currently the game consists of two parts. The first (original) is a character builder. Here you can build DnD style characters weapons and armor. The second is the actual game. Here you load a character into various maps, which you cna freely explore, and with which you can interact.
